package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitScrapResponseDto;
import com.gajob.entity.crawling.Exhibit;
import com.gajob.entity.crawling.ExhibitScrap;
import com.gajob.entity.user.User;
import com.gajob.repository.crawling.ExhibitRepository;
import com.gajob.repository.crawling.ExhibitScrapRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ExhibitScrapServiceImpl implements ExhibitScrapService {

    private final UserRepository userRepository;
    private final ExhibitRepository exhibitRepository;
    private final ExhibitScrapRepository exhibitScrapRepository;

    // 공모전 스크랩 기능
    @Transactional
    public String scrap(Long exhibitId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        Exhibit exhibit = exhibitRepository.findById(exhibitId).orElseThrow();

        if (isNotAlreadyScrap(user, exhibit)) {
            exhibitScrapRepository.save(new ExhibitScrap(exhibit, user));
            return "scrap-success";
        }
        // 이미 스크랩한 공모전일 경우 스크랩 취소
        else if (exhibitScrapRepository.findByUserAndExhibit(user, exhibit).get() != null) {
            exhibitScrapRepository.deleteByUserAndExhibit(user, exhibit);
            return "cancel-scrap";
        }
        return "scrap-success";
    }

    // 사용자가 이미 스크랩한 공모전인지 체크
    private boolean isNotAlreadyScrap(User user, Exhibit exhibit) {
        return exhibitScrapRepository.findByUserAndExhibit(user, exhibit).isEmpty();
    }

    // 공모전 스크랩 목록 조회
    @Transactional(readOnly = true)
    public List<ExhibitScrapResponseDto> getScrap() {
        return exhibitScrapRepository.findAll().stream().map(ExhibitScrapResponseDto::new).collect(
                Collectors.toList());
    }

}