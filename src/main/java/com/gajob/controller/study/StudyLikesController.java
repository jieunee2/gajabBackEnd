package com.gajob.controller.study;

import com.gajob.service.study.StudyLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class StudyLikesController {

  private final StudyLikesService studyLikesService;

  @PostMapping("/likes/{postId}")
  public String likes(@PathVariable Long postId) {
    return studyLikesService.likes(postId);
  }

}
