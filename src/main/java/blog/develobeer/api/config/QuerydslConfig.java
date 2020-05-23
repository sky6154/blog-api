package blog.develobeer.api.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QuerydslConfig {
    @PersistenceContext(unitName = "blog")
    private EntityManager blogEntityManager;

    @Bean(name="blogQueryFactory")
    public JPAQueryFactory gameQueryFactory(){
        return new JPAQueryFactory(blogEntityManager);
    }
}
