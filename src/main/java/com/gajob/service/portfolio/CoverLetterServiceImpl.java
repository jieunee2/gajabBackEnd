package com.gajob.service.portfolio;

import com.gajob.dto.portfolio.CoverLetterDto;
import com.gajob.dto.portfolio.CoverLetterReadDto;
import com.gajob.dto.portfolio.CoverLetterResponseDto;
import com.gajob.entity.portfolio.CoverLetter;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.portfolio.CoverLetterRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CoverLetterServiceImpl implements CoverLetterService {

    private final CoverLetterRepository coverLetterRepository;
    private final UserRepository userRepository;

    // 자소서를 DB에 저장
    @Transactional
    public CoverLetterResponseDto save(CoverLetterDto coverLetterDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return new CoverLetterResponseDto(coverLetterRepository.save(coverLetterDto.toEntity(user)));
    }

    // 자소서 낱개 조회
    @Transactional
    public CoverLetterReadDto getCoverLetter(Long coverLetterId) {
        CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

        CoverLetterReadDto coverLetterReadDto = new CoverLetterReadDto(coverLetter);

        return coverLetterReadDto;
    }

    // 자소서 전체 조회
    public List<CoverLetterReadDto> getAllCoverLetter() {
        return coverLetterRepository.findAll().stream().map(CoverLetterReadDto::new).collect(Collectors.toList());
    }

    // 자소서 수정
    @Transactional
    public CoverLetterReadDto update(Long coverLetterId, CoverLetterDto coverLetterDto) {
        CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

        coverLetter.update(coverLetterDto.getTitle());

        CoverLetterReadDto coverLetterReadDto = new CoverLetterReadDto(coverLetter);

        return coverLetterReadDto;
    }

    // 자소서 삭제
    @Transactional
    public String delete(Long coverLetterId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        CoverLetter coverLetter = coverLetterRepository.findById(coverLetterId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_ID_NOT_EXIST));

        if (!(coverLetter.getUser().getEmail().equals(user.getEmail()))) {
            throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
        }

        coverLetterRepository.delete(coverLetter);

        return "coverLetter-delete";
    }

}
