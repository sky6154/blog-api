package blog.develobeer.api.controller.blog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = {"develobeer.blog", "https://develobeer.blog"})
@RequestMapping("/")
public class BlogController {

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "HELLO WORLD !";
    }
}