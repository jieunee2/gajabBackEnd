package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitRankingScrapResponseDto;
import com.gajob.entity.crawling.ExhibitRankingFrame;
import com.gajob.entity.crawling.ExhibitRankingScrap;
import com.gajob.entity.user.User;
import com.gajob.repository.crawling.ExhibitRankingFrameRepository;
import com.gajob.repository.crawling.ExhibitRankingScrapRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExhibitRankingScrapServiceImpl implements ExhibitRankingScrapService {

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

}
