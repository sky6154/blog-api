package blog.develobeer.api.service;

import blog.develobeer.api.dao.BlogPostRepo;
import blog.develobeer.api.domain.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    private BlogPostRepo blogPostRepo;
    private final int PAGE_SIZE = 5;

    @Autowired
    public BlogService(BlogPostRepo blogPostRepo){
        this.blogPostRepo = blogPostRepo;
    }

    public List getAllPost(){
        return blogPostRepo.findAll();
    }

    public BlogPost getPostById(Integer id){
        return blogPostRepo.findById(id).orElse(null);
    }

    public List<BlogPost> getPopularPost(){
        return blogPostRepo.getPopularPost();
    }

    public List<BlogPost> getRecentPost(){
        Timestamp start = Timestamp.valueOf(LocalDateTime.now());
        Timestamp end = Timestamp.valueOf(LocalDateTime.now().minusDays(7));

        return blogPostRepo.getRecentPost(start, end);
    }

    public Page<BlogPost> getPost(int page){
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        return blogPostRepo.getPost(pageRequest);
    }
}
