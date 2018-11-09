package blog.develobeer.api.controller.blog;

import blog.develobeer.api.service.blog.BlogService;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/post")
public class BlogPostController {

    @Autowired
    private BlogService bs;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @RequestMapping("/getAll")
    @ResponseBody
    public String getAllPost() {
        return gson.toJson(bs.getAllPost());
    }


    @RequestMapping("/{postId}")
    @ResponseBody
    public String getPostById(@PathVariable("postId") Integer postId) {
        return gson.toJson(bs.getPostById(postId));
    }

    @RequestMapping("/getPopularPost")
    @ResponseBody
    public String getPopularPost() {
        return gson.toJson(bs.getPopularPost());
    }

    @RequestMapping("/getRecentPost")
    @ResponseBody
    public String getRecentPost() {
        return gson.toJson(bs.getRecentPost());
    }
}