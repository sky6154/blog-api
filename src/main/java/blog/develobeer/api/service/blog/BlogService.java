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
    @Autowired
    private BlogPostRepository bpr;

    public List getAllPost(){
        return bpr.findAll();
    }

    public BlogPost getPostById(Integer id){
        return bpr.findById(id).orElse(null);
    }

    public List<BlogPost> getPopularPost(){
        return bpr.findTop3ByOrderByHitsDesc();
    }

    public List<BlogPost> getRecentPost(){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();

        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -7);

        Timestamp start = new Timestamp(calendar.getTimeInMillis());
        Timestamp end = new Timestamp(date.getTime());

        return bpr.findTop3ByRegDateBetweenOrderByRegDateDesc(start, end);
    }
}
