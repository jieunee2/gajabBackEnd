package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitScrapDto;
import com.gajob.dto.crawling.ExhibitScrapResponseDto;
import com.gajob.entity.crawling.ExhibitScrap;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.crawling.ExhibitScrapRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExhibitScrapServiceImpl implements ExhibitScrapService {

    private final ExhibitScrapRepository exhibitScrapRepository;
    private final UserRepository userRepository;

    // 공모전 스크랩 정보를 DB에 저장
    @Transactional
    public ExhibitScrapResponseDto save(ExhibitScrapDto exhibitScrapDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return new ExhibitScrapResponseDto(exhibitScrapRepository.save(exhibitScrapDto.toEntity(user)));
    }

    // 저장한 공모전 스크랩 조회
    @Transactional
    public List<ExhibitScrapResponseDto> getAllExhibitScraps() {
        List<ExhibitScrap> exhibitScraps = exhibitScrapRepository.findAll();

        ArrayList<ExhibitScrapResponseDto> exhibitScrapResponseDtos = new ArrayList<ExhibitScrapResponseDto>();

        for (ExhibitScrap exhibitScrapList : exhibitScraps) {
            ExhibitScrapResponseDto exhibitScrapResponseDto = new ExhibitScrapResponseDto(exhibitScrapList);

            exhibitScrapResponseDtos.add(exhibitScrapResponseDto);
        }

        return exhibitScrapResponseDtos;
    }

    // 저장한 공모전 스크랩 삭제
    @Transactional
    public String delete(Long exhibitScrapId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        ExhibitScrap exhibitScrap = exhibitScrapRepository.findById(exhibitScrapId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXHIBIT_ID_NOT_EXIST));

        if (!(exhibitScrap.getUser().getEmail().equals(user.getEmail()))) {
            throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
        }

        exhibitScrapRepository.delete(exhibitScrap);

        return "exhibit-scrap-delete";
    }

/*
    -공모전 스크랩 기능(크롤링한 공모전 정보를 바로 스크랩)

    private final UserRepository userRepository;
    private final ExhibitFrameRepository exhibitFrameRepository;
    private final ExhibitScrapRepository exhibitScrapRepository;

    // 공모전 스크랩 기능
    @Transactional
    public String scrap(Long exhibitFrameId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        ExhibitFrame exhibitFrame = exhibitFrameRepository.findById(exhibitFrameId).orElseThrow();

        if (isNotAlreadyScrap(user, exhibitFrame)) {
            exhibitScrapRepository.save(new ExhibitScrap(exhibitFrame, user));
            return "scrap-success";
        }
        // 이미 스크랩한 공모전일 경우 스크랩 취소
        else if (exhibitScrapRepository.findByUserAndExhibitFrame(user, exhibitFrame).get() != null) {
            exhibitScrapRepository.deleteByUserAndExhibitFrame(user, exhibitFrame);
            return "cancel-scrap";
        }
        return "scrap-success";
    }

    // 사용자가 이미 스크랩한 공모전인지 체크
    private boolean isNotAlreadyScrap(User user, ExhibitFrame exhibitFrame) {
        return exhibitScrapRepository.findByUserAndExhibitFrame(user, exhibitFrame).isEmpty();
    }

    // 공모전 스크랩 목록 조회
    @Transactional(readOnly = true)
    public List<ExhibitScrapResponseDto> getScrap() {
        return exhibitScrapRepository.findAll().stream().map(ExhibitScrapResponseDto::new).collect(
                Collectors.toList());
    }
*/

}