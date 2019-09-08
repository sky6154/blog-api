package blog.develobeer.api.service.blog;

import blog.develobeer.api.dao.blog.BlogPostRepository;
import blog.develobeer.api.domain.blog.BlogPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    private BlogPostRepository blogPostRepository;

    @Autowired
    public BlogService(BlogPostRepository blogPostRepository){
        this.blogPostRepository = blogPostRepository;
    }

    public List getAllPost(){
        return blogPostRepository.findAll();
    }

    public BlogPost getPostById(Integer id){
        return blogPostRepository.findById(id).orElse(null);
    }

    public List<BlogPost> getPopularPost(){
        return blogPostRepository.findTop3ByOrderByHitsDesc();
    }

    public List<BlogPost> getRecentPost(){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();

        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -7);

        Timestamp start = new Timestamp(calendar.getTimeInMillis());
        Timestamp end = new Timestamp(date.getTime());

        return blogPostRepository.findTop3ByRegDateBetweenOrderByRegDateDesc(start, end);
    }
}
