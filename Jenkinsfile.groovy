import hudson.*
import hudson.model.*
import jenkins.*
import jenkins.model.*

node {
    def NAME="blog-api"
    def DOCKER_REPO="hub.develobeer.blog"

    try {
        stage('Checkout') {
            checkout scm
        }

        def shortRevision = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
        println("short revision :" + shortRevision)

        stage('Copy application.yml') {
            if (!fileExists('./src/main/resources')) {
                sh "mkdir ./src/main/resources"
            }

            sh "cp -rf /var/backend_config/application.yml ./src/main/resources/"
        }

        stage('Build Gradle') {
            sh "chmod +x gradlew"
            sh "./gradlew build"
        }

        switch (params.JOB) {
            case "build&deploy":

                stage("docker build with tag"){
                    sh "docker build . -t ${DOCKER_REPO}/${NAME}:${shortRevision}"
                }

                stage("docker login & image push") {
                    sh "docker login hub.develobeer.blog -u ${params.DOCKER_REPO_USER} -p ${params.DOCKER_REPO_PASS}"
                    sh "docker push ${DOCKER_REPO}/${NAME}:${shortRevision}"
                }

//                stage('docker-compose build & save image') {
//                    sh "docker-compose build"
//                    sh "docker save -o blog-api.tar blog-api:latest"
//                }

                if ("${env.CURRENT_BACK_ENV}" == "blue") {
                    stage('deploy swarm manager') {
                        deployManager("GreenB1", shortRevision)
                    }

                    stage('overwrite env') {
                        overwriteEnv("green")
                    }

                    stage('overwrite nginx conf') {
                        sh "docker cp /var/deploy_env_conf/green_back.conf myNginx:/etc/nginx/conf.d/target_back.conf"
                    }

                    stage('reload nginx') {
                        sh "docker kill -s HUP myNginx"
                    }
                } else { // green
                    stage('deploy swarm manager') {
                        deployManager("BlueB1", shortRevision)
                    }

                    stage('overwrite env') {
                        overwriteEnv("blue")
                    }

                    stage('overwrite nginx conf') {
                        sh "docker cp /var/deploy_env_conf/blue_back.conf myNginx:/etc/nginx/conf.d/target_back.conf"
                    }

                    stage('reload nginx') {
                        sh "docker kill -s HUP myNginx"
                    }
                }
                break
        }
    }
    catch (err) {
        currentBuild.result = 'FAILED'
        println(err.getMessage())
        throw err
    }
}


def deployManager(configName, shortRevision) {
    sshPublisher(publishers: [
            sshPublisherDesc(
                    configName: configName,
                    transfers: [
                            sshTransfer(sourceFiles: 'docker-compose.yml, deploy-manager.sh',
                                    execCommand: "cd /root && \
                                  chmod 744 ./deploy-manager.sh && \
                                  ./deploy-manager.sh ${shortRevision}")
                    ],
            )
    ])
}

def overwriteEnv(activeEnv) {
    Jenkins instance = Jenkins.getInstance()
    def globalNodeProperties = instance.getGlobalNodeProperties()
    def envVarsNodePropertyList = globalNodeProperties.getAll(hudson.slaves.EnvironmentVariablesNodeProperty.class)
    def newEnvVarsNodeProperty = null
    def envVars = null

    if (envVarsNodePropertyList == null || envVarsNodePropertyList.size() == 0) {
        newEnvVarsNodeProperty = new hudson.slaves.EnvironmentVariablesNodeProperty();
        globalNodeProperties.add(newEnvVarsNodeProperty)
        envVars = newEnvVarsNodeProperty.getEnvVars()
    } else {
        envVars = envVarsNodePropertyList.get(0).getEnvVars()
    }

    envVars.put("CURRENT_BACK_ENV", activeEnv)

    instance.save()
}