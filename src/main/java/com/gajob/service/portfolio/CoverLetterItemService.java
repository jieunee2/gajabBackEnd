package com.gajob.service.portfolio;

import com.gajob.dto.portfolio.CoverLetterItemDto;
import com.gajob.dto.portfolio.CoverLetterItemResponseDto;

public interface CoverLetterItemService {

    CoverLetterItemResponseDto save(Long coverLetterId, CoverLetterItemDto coverLetterItemDto); // 문항 저장

    CoverLetterItemResponseDto update(Long coverLetterId, Long itemId,
                                      CoverLetterItemDto coverLetterItemDto);   // 문항 수정

    String delete(Long itemId); // 문항 삭제

}
