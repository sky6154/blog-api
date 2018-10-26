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
    public String getAllAgency() {
        return gson.toJson(bs.getAllPost());
    }

}