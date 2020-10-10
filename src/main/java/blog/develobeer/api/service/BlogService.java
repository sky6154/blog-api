package blog.develobeer.api.service;

import blog.develobeer.api.dao.BlogPostRepo;
import blog.develobeer.api.domain.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class BlogService {

    private BlogPostRepo blogPostRepo;
    private static final int PAGE_SIZE = 5;

    @Autowired
    public BlogService(BlogPostRepo blogPostRepo){
        this.blogPostRepo = blogPostRepo;
    }

    public Page<BlogPost> getAllPost(int page){
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);
        return blogPostRepo.getAll(pageRequest);
    }

    public BlogPost getPostById(Integer id){
        return blogPostRepo.getPostById(id);
    }

    public List<BlogPost> getPopularPost(){
        return blogPostRepo.getPopularPost();
    }

    public List<BlogPost> getRecentPost(){
        LocalDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();

        return blogPostRepo.getRecentPost(now.minusDays(7), now);
    }

    public Page<BlogPost> getPostList(int boardId, int page){
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE);
        return blogPostRepo.getPostList(boardId, pageRequest);
    }

    @Transactional
    public long addHits(int postId){
        return blogPostRepo.addHits(postId);
    }
}
