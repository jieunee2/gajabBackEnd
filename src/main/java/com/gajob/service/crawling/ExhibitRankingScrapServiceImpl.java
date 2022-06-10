package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitRankingScrapDto;
import com.gajob.dto.crawling.ExhibitRankingScrapResponseDto;
import com.gajob.entity.crawling.ExhibitRankingScrap;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.crawling.ExhibitRankingScrapRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExhibitRankingScrapServiceImpl implements ExhibitRankingScrapService {

    private final ExhibitRankingScrapRepository exhibitRankingScrapRepository;
    private final UserRepository userRepository;

    // 공모전랭킹 스크랩 정보를 DB에 저장
    @Transactional
    public ExhibitRankingScrapResponseDto save(ExhibitRankingScrapDto exhibitRankingScrapDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return new ExhibitRankingScrapResponseDto(exhibitRankingScrapRepository.save(exhibitRankingScrapDto.toEntity(user)));
    }

    // 저장한 공모전랭킹 스크랩 조회
    @Transactional
    public List<ExhibitRankingScrapResponseDto> getAllExhibitRankingScraps() {
        List<ExhibitRankingScrap> exhibitRankingScraps = exhibitRankingScrapRepository.findAll();

        ArrayList<ExhibitRankingScrapResponseDto> exhibitRankingScrapResponseDtos = new ArrayList<ExhibitRankingScrapResponseDto>();

        for (ExhibitRankingScrap exhibitRankingScrapList : exhibitRankingScraps) {
            ExhibitRankingScrapResponseDto exhibitRankingScrapResponseDto = new ExhibitRankingScrapResponseDto(exhibitRankingScrapList);

            exhibitRankingScrapResponseDtos.add(exhibitRankingScrapResponseDto);
        }

        return exhibitRankingScrapResponseDtos;
    }

    // 저장한 공모전랭킹 스크랩 삭제
    @Transactional
    public String delete(Long exhibitRankingScrapId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        ExhibitRankingScrap exhibitRankingScrap = exhibitRankingScrapRepository.findById(exhibitRankingScrapId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXHIBIT_RANKING_ID_NOT_EXIST));

        if (!(exhibitRankingScrap.getUser().getEmail().equals(user.getEmail()))) {
            throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
        }

        exhibitRankingScrapRepository.delete(exhibitRankingScrap);

        return "exhibit-ranking-scrap-delete";
    }

/*
    -공모전랭킹 스크랩 기능(크롤링한 공모전랭킹 정보를 바로 스크랩)

    private final UserRepository userRepository;
    private final ExhibitRankingFrameRepository exhibitRankingFrameRepository;
    private final ExhibitRankingScrapRepository exhibitRankingScrapRepository;

    // 랭킹 공모전 스크랩 기능
    @Transactional
    public String scrap(Long exhibitRankingFrameId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        ExhibitRankingFrame exhibitRankingFrame = exhibitRankingFrameRepository.findById(exhibitRankingFrameId).orElseThrow();

        if (isNotAlreadyScrap(user, exhibitRankingFrame)) {
            exhibitRankingScrapRepository.save(new ExhibitRankingScrap(exhibitRankingFrame, user));
            return "scrap-success";
        }
        // 이미 스크랩한 랭킹 공모전일 경우 스크랩 취소
        else if (exhibitRankingScrapRepository.findByUserAndExhibitRankingFrame(user, exhibitRankingFrame).get() != null) {
            exhibitRankingScrapRepository.deleteByUserAndExhibitRankingFrame(user, exhibitRankingFrame);
            return "cancel-scrap";
        }
        return "scrap-success";
    }

    // 사용자가 이미 스크랩한 랭킹 공모전인지 체크
    private boolean isNotAlreadyScrap(User user, ExhibitRankingFrame exhibitRankingFrame) {
        return exhibitRankingScrapRepository.findByUserAndExhibitRankingFrame(user, exhibitRankingFrame).isEmpty();
    }

    // 랭킹 공모전 스크랩 목록 조회
    @Transactional(readOnly = true)
    public List<ExhibitRankingScrapResponseDto> getScrap() {
        return exhibitRankingScrapRepository.findAll().stream().map(ExhibitRankingScrapResponseDto::new).collect(
                Collectors.toList());
    }
*/

}
