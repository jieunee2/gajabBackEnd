package com.gajob.service.portfolio;

import com.gajob.dto.portfolio.CoverLetterItemDto;
import com.gajob.dto.portfolio.CoverLetterItemResponseDto;
import com.gajob.entity.portfolio.CoverLetter;
import com.gajob.entity.portfolio.CoverLetterItem;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.portfolio.CoverLetterItemRepository;
import com.gajob.repository.portfolio.CoverLetterRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CoverLetterItemServiceImpl implements CoverLetterItemService {

    private final UserRepository userRepository;
    private final CoverLetterRepository coverLetterRepository;
    private final CoverLetterItemRepository coverLetterItemRepository;

    // 사용자가 등록한 문항을 DB에 저장
    @Transactional
    public CoverLetterItemResponseDto save(Long coverLetterId, CoverLetterItemDto coverLetterItemDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();
        CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
                .orElseThrow(() -> new CustomException(ErrorCode.COVER_LETTER_ID_NOT_EXIST));

        coverLetter.updateModifiedDate(coverLetterItemDto.getModifiedDate());   // 문항이 추가될 경우에도 전체 자소서 최종 수정일이 변경되어야함.

        return new CoverLetterItemResponseDto(coverLetterItemRepository.save(
                coverLetterItemDto.toEntity(user, coverLetter)));
    }

    // 문항 질문 수정
    @Transactional
    public CoverLetterItemResponseDto update(Long coverLetterId, Long itemId,
                                             CoverLetterItemDto coverLetterItemDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
                .orElseThrow(() -> new CustomException(ErrorCode.COVER_LETTER_ID_NOT_EXIST));

        CoverLetterItem coverLetterItem = coverLetterItemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ErrorCode.ITEM_ID_NOT_EXIST));

        if (!(coverLetterItem.getUser().getEmail().equals(user.getEmail()))) {
            throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
        }

        coverLetterItem.update(coverLetterItemDto.getQuestion(), coverLetterItemDto.getAnswer(), coverLetterItemDto.getModifiedDate());

        coverLetter.updateModifiedDate(coverLetterItemDto.getModifiedDate());   // 전체 자소서 최종 수정일 변경

        CoverLetterItemResponseDto coverLetterItemResponseDto = new CoverLetterItemResponseDto(coverLetterItem);

        return coverLetterItemResponseDto;
    }

    // 문항 삭제
    @Transactional
    public String delete(Long itemId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        CoverLetterItem coverLetterItem = coverLetterItemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ErrorCode.ITEM_ID_NOT_EXIST));

        if (!(coverLetterItem.getUser().getEmail().equals(user.getEmail()))) {
            throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
        }

        coverLetterItemRepository.delete(coverLetterItem);

        return "item-delete";
    }

}
