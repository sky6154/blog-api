package blog.develobeer.api.dao;

import blog.develobeer.api.domain.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface BlogPostRepo extends JpaRepository<BlogPost, Integer>, QBlogPostRepo {
}