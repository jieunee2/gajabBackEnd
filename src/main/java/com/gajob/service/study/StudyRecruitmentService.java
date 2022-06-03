package com.gajob.service.study;

import com.gajob.dto.study.StudyRecruitmentDto;
import com.gajob.dto.study.StudyRecruitmentResponseDto;
import java.util.List;

public interface StudyRecruitmentService {

  StudyRecruitmentResponseDto support(Long postId,
      StudyRecruitmentDto studyRecruitmentDto); //스터디 모임 신청

  List<StudyRecruitmentResponseDto> getAllSupport(Long postId); //스터디 모임 신청자 전체 조회

}
