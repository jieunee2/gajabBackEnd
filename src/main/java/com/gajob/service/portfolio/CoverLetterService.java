package com.gajob.service.portfolio;

import com.gajob.dto.portfolio.CoverLetterDto;
import com.gajob.dto.portfolio.CoverLetterReadDto;
import com.gajob.dto.portfolio.CoverLetterResponseDto;

import java.util.List;

public interface CoverLetterService {

    CoverLetterResponseDto save(CoverLetterDto coverLetterDto); // 자소서 틀 저장

    CoverLetterReadDto getCoverLetter(Long coverLetterId);  // 자소서 틀 낱개 조회

    List<CoverLetterReadDto> getAllCoverLetter();   // 자소서 전체 조회

    CoverLetterReadDto update(Long coverLetterId, CoverLetterDto coverLetterDto);   // 자소서 수정

    String delete(Long coverLetterId);  // 자소서 삭제

}
