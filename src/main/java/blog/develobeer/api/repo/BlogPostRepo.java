package blog.develobeer.api.repo;

import blog.develobeer.api.domain.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepo extends JpaRepository<BlogPost, Integer>, QBlogPostRepo {
}