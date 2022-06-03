package com.gajob.service.openApi;

import com.gajob.dto.openApi.JobFrameDto;
import com.gajob.dto.openApi.JobFrameResponseDto;
import com.gajob.entity.openApi.JobFrame;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.openApi.JobFrameRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JobFrameServiceImpl implements JobFrameService {

    private final JobFrameRepository jobFrameRepository;
    private final UserRepository userRepository;

    // 채용정보를 DB에 저장
    @Transactional
    public JobFrameResponseDto save(JobFrameDto jobFrameDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return new JobFrameResponseDto(jobFrameRepository.save(jobFrameDto.toEntity(user)));
    }

    // 저장한 채용정보 조회
    @Transactional
    public List<JobFrameResponseDto> getAllJobFrames() {
        List<JobFrame> jobFrames = jobFrameRepository.findAll();

        ArrayList<JobFrameResponseDto> jobFrameResponseDtos = new ArrayList<JobFrameResponseDto>();

        for (JobFrame jobFrameList : jobFrames) {
            JobFrameResponseDto jobFrameResponseDto = new JobFrameResponseDto(jobFrameList);

            jobFrameResponseDtos.add(jobFrameResponseDto);
        }

        return jobFrameResponseDtos;
    }

    // 저장한 채용정보 삭제
    @Transactional
    public String delete(Long jobFrameId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        JobFrame jobFrame = jobFrameRepository.findById(jobFrameId)
                .orElseThrow(() -> new CustomException(ErrorCode.JOB_POSTING_ID_NOT_EXIST));

        if (!(jobFrame.getUser().getEmail().equals(user.getEmail()))) {
            throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
        }

        jobFrameRepository.delete(jobFrame);

        return "job-posting-delete";
    }
}
