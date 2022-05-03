package com.gajob.controller.posts;

import com.gajob.service.posts.PostsLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/community")
@CrossOrigin(origins = "http://localhost:3000/")
public class PostsLikesController {

  private final PostsLikesService postsLikesService;

  @PostMapping("/likes/{postId}")
  public String likes(@PathVariable Long postId) {
    return postsLikesService.likes(postId);
  }


}
