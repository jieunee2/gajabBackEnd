package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitFrameDto;
import com.gajob.dto.crawling.ExhibitFrameResponseDto;
import com.gajob.entity.crawling.ExhibitFrame;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.crawling.ExhibitFrameRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExhibitFrameServiceImpl implements ExhibitFrameService {

    private final ExhibitFrameRepository exhibitFrameRepository;
    private final UserRepository userRepository;

    // 공모전 정보를 DB에 저장
    @Transactional
    public ExhibitFrameResponseDto save(ExhibitFrameDto exhibitFrameDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return new ExhibitFrameResponseDto(exhibitFrameRepository.save(exhibitFrameDto.toEntity(user)));
    }

    // 저장한 공모전 정보 조회
    @Transactional
    public List<ExhibitFrameResponseDto> getAllExhibitFrames() {
        List<ExhibitFrame> exhibitFrames = exhibitFrameRepository.findAll();

        ArrayList<ExhibitFrameResponseDto> exhibitFrameResponseDtos = new ArrayList<ExhibitFrameResponseDto>();

        for (ExhibitFrame exhibitFrameList : exhibitFrames) {
            ExhibitFrameResponseDto exhibitFrameResponseDto = new ExhibitFrameResponseDto(exhibitFrameList);

            exhibitFrameResponseDtos.add(exhibitFrameResponseDto);
        }

        return exhibitFrameResponseDtos;
    }

    // 저장한 공모전 정보 삭제
    @Transactional
    public String delete(Long exhibitFrameId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        ExhibitFrame exhibitFrame = exhibitFrameRepository.findById(exhibitFrameId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXHIBIT_ID_NOT_EXIST));

        if (!(exhibitFrame.getUser().getEmail().equals(user.getEmail()))) {
            throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
        }

        exhibitFrameRepository.delete(exhibitFrame);

        return "exhibit-delete";
    }

}
