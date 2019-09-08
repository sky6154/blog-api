package blog.develobeer.api.controller.blog;

import blog.develobeer.api.domain.blog.BlogPost;
import blog.develobeer.api.service.blog.BlogService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"develobeer.blog", "https://develobeer.blog"})
@RequestMapping("/post")
public class BlogPostController {

    private BlogService blogService;

    @Autowired
    public BlogPostController(BlogService blogService){
        this.blogService = blogService;
    }

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @RequestMapping(value="/getAll", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getAllPost() {
        return gson.toJson(blogService.getAllPost());
    }


    @RequestMapping(value="/{postId}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getPostById(@PathVariable("postId") Integer postId) {
        return gson.toJson(blogService.getPostById(postId));
    }

    @RequestMapping(value="/getPopularPost", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getPopularPost() {
        return gson.toJson(blogService.getPopularPost());
    }

    @RequestMapping(value="/getRecentPost", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getRecentPost() {
        return gson.toJson(blogService.getRecentPost());
    }
}