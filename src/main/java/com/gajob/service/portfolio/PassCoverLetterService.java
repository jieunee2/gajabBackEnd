package com.gajob.service.portfolio;

import com.gajob.dto.portfolio.PassCoverLetterDto;
import com.gajob.dto.portfolio.PassCoverLetterResponseDto;
import com.gajob.entity.portfolio.PassCoverLetter;

import java.io.IOException;
import java.util.List;

public interface PassCoverLetterService {

    String getPassCoverLetterUrl(int page); // 각 페이지 별로 url 추출

    List<PassCoverLetter> getPassCoverLetterData(String URL) throws IOException;    // 사이트 내 데이터 추출

    PassCoverLetterDto savePassCoverLetter(PassCoverLetterDto passCoverLetterDto) throws Exception; // 크롤링한 합격자소서 정보들을 DB에 저장

    List<PassCoverLetterResponseDto> getPassCoverLetter();  // 저장한 합격자소서 정보들 읽어오기
}
