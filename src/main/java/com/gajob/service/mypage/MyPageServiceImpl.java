package com.gajob.service.mypage;

import com.gajob.dto.portfolio.CoverLetterReadDto;
import com.gajob.dto.posts.PostsReadDto;
import com.gajob.dto.study.StudyReadDto;
import com.gajob.entity.user.User;
import com.gajob.repository.portfolio.CoverLetterRepository;
import com.gajob.repository.posts.PostsRepository;
import com.gajob.repository.study.StudyRepository;
import com.gajob.repository.user.UserRepository;
import com.gajob.util.SecurityUtil;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MyPageServiceImpl implements MyPageService {

    private final UserRepository userRepository;
    private final PostsRepository postsRepository;
    private final StudyRepository studyRepository;
    private final CoverLetterRepository coverLetterRepository;

    // 사용자가 작성한 JOB담 커뮤니티 게시물 전체 조회
    @Transactional(readOnly = true)
    public List<PostsReadDto> getUserAllPosts() {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return postsRepository.findAllByUser(user).stream().map(PostsReadDto::new)
                .collect(Collectors.toList());

    }

    // 사용자가 작성한 스터디 매칭 시스템 커뮤니티 게시물 전체 조회
    @Transactional(readOnly = true)
    public List<StudyReadDto> getUserAllStudy() {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return studyRepository.findAllByUser(user).stream().map(StudyReadDto::new)
                .collect(Collectors.toList());

    }

    // 사용자가 작성한 자소서 전체 조회
    @Transactional(readOnly = true)
    public List<CoverLetterReadDto> getUserAllCoverLetters() {
        User user = userRepository.findOneWithAuthoritiesByEmail(
                SecurityUtil.getCurrentUsername().get()).get();

        return coverLetterRepository.findAllByUser(user).stream().map(CoverLetterReadDto::new)
                .collect(Collectors.toList());
    }

}
