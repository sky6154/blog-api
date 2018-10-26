package blog.develobeer.api.service.blog;

import blog.develobeer.api.dao.blog.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogPostRepository bpr;

    public List getAllPost(){
        return bpr.findAll();
    }

}
