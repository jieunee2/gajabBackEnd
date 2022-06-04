package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitFrameDto;
import com.gajob.dto.crawling.ExhibitFrameResponseDto;

import java.util.List;

public interface ExhibitFrameService {

    ExhibitFrameResponseDto save(ExhibitFrameDto exhibitFrameDto);  // 공모전 정보 저장

    List<ExhibitFrameResponseDto> getAllExhibitFrames();    // 공모전 정보 전체 조회

    String delete(Long exhibitFrameId); // 공모전 정보 삭제

}
