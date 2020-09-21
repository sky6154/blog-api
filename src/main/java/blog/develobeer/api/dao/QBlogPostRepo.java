package blog.develobeer.api.dao;

import blog.develobeer.api.domain.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface QBlogPostRepo {
    Page<BlogPost> getAll(Pageable pageable);
    List<BlogPost> getRecentPost(Timestamp start, Timestamp end);
    List<BlogPost> getPopularPost();
    Page<BlogPost> getPostList(int boardId, Pageable pageable);
    BlogPost getPostById(int postId);
}
