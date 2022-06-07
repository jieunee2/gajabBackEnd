package com.gajob.service.study;

import com.gajob.dto.study.StudyRecruitmentDto;
import com.gajob.dto.study.StudyRecruitmentResponseDto;
import com.gajob.dto.study.StudyRecruitmentUpdateDto;
import java.util.List;
import javax.mail.internet.MimeMessage;

public interface StudyRecruitmentService {

  StudyRecruitmentResponseDto support(Long postId,
      StudyRecruitmentDto studyRecruitmentDto); //스터디 모임 신청

  String supportCancel(Long postId); //스터디 모임 신청 취소

  List<StudyRecruitmentResponseDto> getAllSupport(Long postId); //스터디 모임 신청자 전체 조회

  StudyRecruitmentResponseDto setResult(Long postId,
      Long supplyId, StudyRecruitmentUpdateDto studyRecruitmentUpdateDto)
      throws Exception; //스터디 지원자들의 모집결과 설정

  void sendSimpleMessage(String email) throws Exception; //메일 내용을 생성

  MimeMessage mailSend(String email) throws Exception; //스터디 모집 결과 알림 메일 보내기


}
