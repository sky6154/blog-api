package blog.develobeer.api.controller;

import blog.develobeer.api.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class BlogPostController {

    private final BlogService blogService;

    @Autowired
    public BlogPostController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = "/{postId}", method = RequestMethod.GET)
    public ResponseEntity getPostById(@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(blogService.getPostById(postId));
    }

    @RequestMapping(value = "/get/popular", method = RequestMethod.GET)
    public ResponseEntity getPopularPost() {
        return ResponseEntity.ok(blogService.getPopularPost());
    }

    @RequestMapping(value = "/get/recent", method = RequestMethod.GET)
    public ResponseEntity getRecentPost() {
        return ResponseEntity.ok(blogService.getRecentPost());
    }
}