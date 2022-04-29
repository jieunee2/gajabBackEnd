package com.gajob.service.portfolio;

import com.gajob.dto.portfolio.CoverLetterItemDto;
import com.gajob.dto.portfolio.CoverLetterItemResponseDto;
import com.gajob.entity.portfolio.CoverLetter;
import com.gajob.entity.portfolio.CoverLetterItem;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.portfolio.CoverLetterItemRepository;
import com.gajob.repository.portfolio.CoverLetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CoverLetterItemServiceImpl implements CoverLetterItemService {

    private final CoverLetterRepository coverLetterRepository;
    private final CoverLetterItemRepository coverLetterItemRepository;

    // 사용자가 등록한 문항을 DB에 저장
    @Transactional
    public CoverLetterItemResponseDto save(Long coverLetterId, CoverLetterItemDto coverLetterItemDto) {
        CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
                .orElseThrow(() -> new CustomException(ErrorCode.COVER_LETTER_ID_NOT_EXIST));

        return new CoverLetterItemResponseDto(coverLetterItemRepository.save(
                coverLetterItemDto.toEntity(coverLetter)));
    }

    // 문항 질문 수정
    @Transactional
    public CoverLetterItemResponseDto update(Long coverLetterId, Long itemId,
                                             CoverLetterItemDto coverLetterItemDto) {
        CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
                .orElseThrow(() -> new CustomException(ErrorCode.COVER_LETTER_ID_NOT_EXIST));

        CoverLetterItem coverLetterItem = coverLetterItemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ErrorCode.ITEM_ID_NOT_EXIST));

        coverLetterItem.update(coverLetterItemDto.getQuestion(), coverLetterItemDto.getAnswer());

        CoverLetterItemResponseDto coverLetterItemResponseDto = new CoverLetterItemResponseDto(coverLetterItem);

        return coverLetterItemResponseDto;
    }

    // 문항 삭제
    @Transactional
    public String delete(Long itemId) {
        CoverLetterItem coverLetterItem = coverLetterItemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ErrorCode.ITEM_ID_NOT_EXIST));

        coverLetterItemRepository.delete(coverLetterItem);

        return "item-delete";
    }

}
