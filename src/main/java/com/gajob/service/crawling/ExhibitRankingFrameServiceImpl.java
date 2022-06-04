package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitRankingFrameDto;
import com.gajob.dto.crawling.ExhibitRankingFrameResponseDto;
import com.gajob.entity.crawling.ExhibitRankingFrame;
import com.gajob.entity.user.User;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.crawling.ExhibitRankingFrameRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExhibitRankingFrameServiceImpl implements ExhibitRankingFrameService {

    private final ExhibitRankingFrameRepository exhibitRankingFrameRepository;
    private final UserRepository userRepository;

    // 공모전랭킹 정보를 DB에 저장
    @Transactional
    public ExhibitRankingFrameResponseDto save(ExhibitRankingFrameDto exhibitRankingFrameDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return new ExhibitRankingFrameResponseDto(exhibitRankingFrameRepository.save(exhibitRankingFrameDto.toEntity(user)));
    }

    // 저장한 공모전랭킹 정보 조회
    @Transactional
    public List<ExhibitRankingFrameResponseDto> getAllExhibitRankingFrames() {
        List<ExhibitRankingFrame> exhibitRankingFrames = exhibitRankingFrameRepository.findAll();

        ArrayList<ExhibitRankingFrameResponseDto> exhibitRankingFrameResponseDtos = new ArrayList<ExhibitRankingFrameResponseDto>();

        for (ExhibitRankingFrame exhibitRankingFrameList : exhibitRankingFrames) {
            ExhibitRankingFrameResponseDto exhibitRankingFrameResponseDto = new ExhibitRankingFrameResponseDto(exhibitRankingFrameList);

            exhibitRankingFrameResponseDtos.add(exhibitRankingFrameResponseDto);
        }

        return exhibitRankingFrameResponseDtos;
    }

    // 저장한 공모전랭킹 정보 삭제
    @Transactional
    public String delete(Long exhibitRankingFrameId) {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        ExhibitRankingFrame exhibitRankingFrame = exhibitRankingFrameRepository.findById(exhibitRankingFrameId)
                .orElseThrow(() -> new CustomException(ErrorCode.EXHIBIT_RANKING_ID_NOT_EXIST));

        if (!(exhibitRankingFrame.getUser().getEmail().equals(user.getEmail()))) {
            throw new CustomException(ErrorCode.NO_ACCESS_RIGHTS);
        }

        exhibitRankingFrameRepository.delete(exhibitRankingFrame);

        return "exhibit-ranking-delete";
    }

}
