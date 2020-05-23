package blog.develobeer.api.dao;

import blog.develobeer.api.domain.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface QBlogPostRepo {
    List<BlogPost> getRecentPost(Timestamp start, Timestamp end);
    List<BlogPost> getPopularPost();
    Page<BlogPost> getPost(Pageable pageable);
}
