### DEVELOBEER의 Back-end 코드 입니다.

application.yml은 git에서 제외되어 있으며 /src/main/resources/application.yml 에 넣어야 합니다.

```
spring:
  profiles:
    active: test
---
spring:
  profiles: test

  devtools:
    livereload:
      enabled: false

  jpa:
    hibernate:
      ddl-auto: none
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect # use InnoDB
    show-sql: true

  datasource:
    blog:
      type: com.zaxxer.hikari.HikariDataSource
      jdbc-url: #JDBC URL#
      username: #ID#
      password: #PASSWORD#
      hikari:
        maximum-pool-size: 5
        max-lifetime: 30

origin:
  hosts:
    - http://localhost:3000
    - http://localhost:3001

---
spring:
  profiles: live

  jpa:
    hibernate:
      ddl-auto: none
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect # use InnoDB
#   show-sql: true

  datasource:
    blog:
      type: com.zaxxer.hikari.HikariDataSource
      jdbc-url: #JDBC URL#
      username: #ID#
      password: #PASSWORD#
      hikari:
        maximum-pool-size: 5
        max-lifetime: 30

origin:
  hosts:
    - https://develobeer.blog
```
