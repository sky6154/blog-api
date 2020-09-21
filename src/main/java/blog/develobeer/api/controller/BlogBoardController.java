package blog.develobeer.api.controller;

import blog.develobeer.api.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BlogBoardController {

    private final BlogService blogService;

    @Autowired
    public BlogBoardController(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity getPostListById(@RequestParam Integer boardId, @RequestParam Integer page) {
        if(boardId == 0){
            return ResponseEntity.ok(blogService.getAllPost(page));
        }
        else{
            return ResponseEntity.ok(blogService.getPostList(boardId, page));
        }
    }
}