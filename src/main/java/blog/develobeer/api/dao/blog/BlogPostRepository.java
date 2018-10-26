package blog.develobeer.api.dao.blog;

import blog.develobeer.api.domain.blog.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
    List<BlogPost> findAll();
}