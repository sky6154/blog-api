package blog.develobeer.api.dao.blog;

import blog.develobeer.api.domain.blog.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    @Query(
            value = "SELECT * FROM blog_post WHERE is_delete = 0 ORDER BY hits DESC LIMIT 3",
            nativeQuery = true
    )
    List<BlogPost> findTop3ByOrderByHitsDesc();

    @Query(
            value = "SELECT * FROM blog_post WHERE is_delete = 0 AND reg_date BETWEEN :start AND :end LIMIT 3",
            nativeQuery = true
    )
    List<BlogPost> findTop3ByRegDateBetweenOrderByRegDateDesc(@Param("start") Timestamp start, @Param("end") Timestamp end);
}