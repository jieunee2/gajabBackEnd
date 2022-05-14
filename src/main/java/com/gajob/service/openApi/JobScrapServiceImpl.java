package com.gajob.service.openApi;

import com.gajob.dto.openApi.JobScrapResponseDto;
import com.gajob.entity.openApi.Job;
import com.gajob.entity.openApi.JobScrap;
import com.gajob.entity.user.User;
import com.gajob.repository.openApi.JobRepository;
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
    private final JobRepository jobRepository;
    private final JobScrapRepository jobScrapRepository;

    // 채용정보 스크랩 기능
    @Transactional
    public String scrap(Long jobId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        Job job = jobRepository.findById(jobId).orElseThrow();

        if (isNotAlreadyScrap(user, job)) {
            jobScrapRepository.save(new JobScrap(job, user));
            return "scrap-success";
        }
        // 이미 스크랩한 채용정보일 경우 스크랩 취소
        else if (jobScrapRepository.findByUserAndJob(user, job).get() != null) {
            jobScrapRepository.deleteByUserAndJob(user, job);
            return "cancel-scrap";
        }
        return "scrap-success";
    }

    // 사용자가 이미 스크랩한 채용정보인지 체크
    private boolean isNotAlreadyScrap(User user, Job job) {
        return jobScrapRepository.findByUserAndJob(user, job).isEmpty();
    }

    // 채용정보 스크랩 목록 조회
    @Transactional(readOnly = true)
    public List<JobScrapResponseDto> getScrap() {
        return jobScrapRepository.findAll().stream().map(JobScrapResponseDto::new).collect(
                Collectors.toList());
    }

}
