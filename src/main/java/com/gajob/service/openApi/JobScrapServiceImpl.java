package com.gajob.service.openApi;

import com.gajob.dto.openApi.JobScrapResponseDto;
import com.gajob.entity.openApi.JobFrame;
import com.gajob.entity.openApi.JobScrap;
import com.gajob.entity.user.User;
import com.gajob.repository.openApi.JobFrameRepository;
import com.gajob.repository.openApi.JobScrapRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JobScrapServiceImpl implements JobScrapService {

    private final UserRepository userRepository;
    private final JobFrameRepository jobFrameRepository;
    private final JobScrapRepository jobScrapRepository;

    // 채용정보 스크랩 기능
    @Transactional
    public String scrap(Long jobFrameId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        JobFrame jobFrame = jobFrameRepository.findById(jobFrameId).orElseThrow();

        if (isNotAlreadyScrap(user, jobFrame)) {
            jobScrapRepository.save(new JobScrap(jobFrame, user));
            return "scrap-success";
        }
        // 이미 스크랩한 채용정보일 경우 스크랩 취소
        else if (jobScrapRepository.findByUserAndJobFrame(user, jobFrame).get() != null) {
            jobScrapRepository.deleteByUserAndJobFrame(user, jobFrame);
            return "cancel-scrap";
        }
        return "scrap-success";
    }

    // 사용자가 이미 스크랩한 채용정보인지 체크
    private boolean isNotAlreadyScrap(User user, JobFrame jobFrame) {
        return jobScrapRepository.findByUserAndJobFrame(user, jobFrame).isEmpty();
    }

    // 채용정보 스크랩 목록 조회
    @Transactional(readOnly = true)
    public List<JobScrapResponseDto> getScrap() {
        return jobScrapRepository.findAll().stream().map(JobScrapResponseDto::new).collect(
                Collectors.toList());
    }

}
