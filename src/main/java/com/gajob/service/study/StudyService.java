package com.gajob.service.study;

import com.gajob.dto.study.StudyDto;
import com.gajob.dto.study.StudyReadDto;
import com.gajob.dto.study.StudyResponseDto;
import java.util.List;

public interface StudyService {

  StudyResponseDto save(StudyDto studyDto); //게시물 저장

  StudyReadDto getPosts(Long postId); //게시물 낱개 조회 및 조회수 증가

  List<StudyReadDto> getAllPosts(); //게시물 전체 조회

  StudyReadDto update(Long postId, StudyDto studyDto); //게시물 수정

  String delete(Long postId); //게시물 삭제

}
