package com.gajob.service.study;

import com.gajob.dto.study.StudyScrapResponseDto;
import java.util.List;

public interface StudyScrapService {

  String scrap(Long postId); //스크랩 기능

  List<StudyScrapResponseDto> getScrap(); //스크랩 목록 조회
}
