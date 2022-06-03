package com.gajob.service.openApi;

import com.gajob.dto.openApi.JobFrameDto;
import com.gajob.dto.openApi.JobFrameResponseDto;

import java.util.List;

public interface JobFrameService {

    JobFrameResponseDto save(JobFrameDto jobFrameDto);  // 채용정보 저장

    List<JobFrameResponseDto> getAllJobFrames();    // 채용정보 전체 조회

    String delete(Long jobFrameId); // 채용정보 삭제

}
