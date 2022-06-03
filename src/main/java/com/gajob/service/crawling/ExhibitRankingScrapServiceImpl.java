package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitRankingScrapResponseDto;
import com.gajob.entity.crawling.ExhibitRanking;
import com.gajob.entity.crawling.ExhibitRankingScrap;
import com.gajob.entity.user.User;
import com.gajob.repository.crawling.ExhibitRankingRepository;
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
    private final ExhibitRankingRepository exhibitRankingRepository;
    private final ExhibitRankingScrapRepository exhibitRankingScrapRepository;

    // 랭킹 공모전 스크랩 기능
    @Transactional
    public String scrap(Long exhibitRankingId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        ExhibitRanking exhibitRanking = exhibitRankingRepository.findById(exhibitRankingId).orElseThrow();

        if (isNotAlreadyScrap(user, exhibitRanking)) {
            exhibitRankingScrapRepository.save(new ExhibitRankingScrap(exhibitRanking, user));
            return "scrap-success";
        }
        // 이미 스크랩한 랭킹 공모전일 경우 스크랩 취소
        else if (exhibitRankingScrapRepository.findByUserAndExhibitRanking(user, exhibitRanking).get() != null) {
            exhibitRankingScrapRepository.deleteByUserAndExhibitRanking(user, exhibitRanking);
            return "cancel-scrap";
        }
        return "scrap-success";
    }

    // 사용자가 이미 스크랩한 랭킹 공모전인지 체크
    private boolean isNotAlreadyScrap(User user, ExhibitRanking exhibitRanking) {
        return exhibitRankingScrapRepository.findByUserAndExhibitRanking(user, exhibitRanking).isEmpty();
    }

    // 랭킹 공모전 스크랩 목록 조회
    @Transactional(readOnly = true)
    public List<ExhibitRankingScrapResponseDto> getScrap() {
        return exhibitRankingScrapRepository.findAll().stream().map(ExhibitRankingScrapResponseDto::new).collect(
                Collectors.toList());
    }

}
