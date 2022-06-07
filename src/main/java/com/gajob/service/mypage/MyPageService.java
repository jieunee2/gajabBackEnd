package com.gajob.service.mypage;

import com.gajob.dto.portfolio.CoverLetterReadDto;
import com.gajob.dto.posts.PostsReadDto;
import com.gajob.dto.study.StudyReadDto;
import com.gajob.dto.study.StudyRecruitmentResponseDto;
import java.util.List;

public interface MyPageService {

  List<PostsReadDto> getUserAllPosts(); //사용자가 작성한 JOB담 커뮤니티 게시물 전체 조회

  List<StudyReadDto> getUserAllStudy(); //사용자가 작성한 스터디 매칭 시스템 커뮤니티 게시물 전체 조회

  List<CoverLetterReadDto> getUserAllCoverLetters();  //사용자가 작성한 자소서 전체 조회

  List<StudyRecruitmentResponseDto> getUserAllSupplyStudy(); //사용자가 지원한 스터디 전체 조회

}
