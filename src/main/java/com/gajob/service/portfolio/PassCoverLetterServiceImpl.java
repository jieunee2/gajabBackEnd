package com.gajob.service.portfolio;

import com.gajob.dto.portfolio.PassCoverLetterDto;
import com.gajob.dto.portfolio.PassCoverLetterResponseDto;
import com.gajob.entity.portfolio.PassCoverLetter;
import com.gajob.repository.portfolio.PassCoverLetterRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class PassCoverLetterServiceImpl implements PassCoverLetterService {

    // 합격자소서 모음 크롤링
    private final PassCoverLetterRepository passCoverLetterRepository;

    private final String URL = "https://www.jobkorea.co.kr/starter/PassAssay?schPart=10016";

    @Override
    public String getPassCoverLetterUrl(int page) {
        return URL + "&" + "Page=" + page;
    }

    // Jsoup을 이용하여 사이트 내 데이터 추출
    @Override
    public List<PassCoverLetter> getPassCoverLetterData(String URL) throws IOException {
        List<PassCoverLetter> passCoverLetterList = new ArrayList<>();

        // 전체 html 코드
        Document document = Jsoup.connect(URL).get();

        // 기업명 가져오기
        Elements companyNameElements = document.select("div.starListsWrap.ctTarget ul.selfLists li div.txBx p.tit a span.titTx");
        // 근무형태 가져오기
        Elements workTypeElements = document.select("div.starListsWrap.ctTarget ul.selfLists li div.txBx p.tit span.linkArray span.field");
        // 지원분야 가져오기
        Elements objectiveElements = document.select("div.starListsWrap.ctTarget ul.selfLists li div.txBx p.tit span.linkArray span.field");
        // 합격자소서 일부 URL 가져오기
        Elements partUrlElements = document.select("div.starListsWrap.ctTarget ul.selfLists li a.logo");
        // 로고이미지 일부 URL 가져오기
        Elements partImgUrlElements = document.select("div.starListsWrap.ctTarget ul.selfLists li a.logo img");

        for (int i = 0; i < companyNameElements.size(); i++) {
            // 기업명 추출
            String companyName = companyNameElements.get(i).text();
            // 근무형태 추출
            String workType = workTypeElements.get(2 * i).text();
            // 지원분야 추출
            String objective = objectiveElements.get(2 * i + 1).text();
            // 합격자소서 일부 URL 추출
            String partUrl = partUrlElements.get(i).getElementsByAttribute("href").attr("href");
            // 합격자소서 전체 URL 생성
            String url = "https://www.jobkorea.co.kr" + partUrl;
            // 로고이미지 일부 URL 추출
            String partImgUrl = partImgUrlElements.get(i).getElementsByAttribute("src").attr("src");
            // 로고이미지 전체 URL 생성
            String imgUrl = "http:" + partImgUrl;

            // 합격자 스펙이 담긴 전체 html 코드
            Document specDocument = Jsoup.connect(url).get();

            // 합격자 스펙 가져오기
            Elements specElements = specDocument.select("div.selfTopBx div.selfCtWrap ul.specLists li");

            // 합격자 스펙을 Set에 저장
            Set<String> specifications = new HashSet<>();
            for (int j = 0; j < specElements.size() - 1; j++)
                specifications.add(specElements.get(j).text());

            PassCoverLetter passCoverLetter = new PassCoverLetter(companyName, workType, objective, specifications, url, imgUrl);
            passCoverLetterList.add(passCoverLetter);
        }
        return passCoverLetterList;
    }

    // Crawling한 합격자소서들을 DB에 저장한다.
    @Transactional
    @Override
    public PassCoverLetterDto savePassCoverLetter(PassCoverLetterDto passCoverLetterDto) throws Exception {
        List<PassCoverLetter> list = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            String url = getPassCoverLetterUrl(i);
            System.out.println("->" + url);
            List<PassCoverLetter> passCoverLetterData = getPassCoverLetterData(url);

            for (PassCoverLetter passCoverLetter : passCoverLetterData) {
                passCoverLetterRepository.save(passCoverLetter);
            }
        }
        return null;
    }

    //DB에 저장한 합격자소서 정보 읽어오기
    @Transactional
    @Override
    public List<PassCoverLetterResponseDto> getPassCoverLetter() {
        List<PassCoverLetterResponseDto> passCoverLetterResponseDtos = new ArrayList<>();
        for (PassCoverLetter passCoverLetter : passCoverLetterRepository.findAll()) {
            PassCoverLetterResponseDto passCoverLetterResponseDto = new PassCoverLetterResponseDto(passCoverLetter);
            passCoverLetterResponseDtos.add(passCoverLetterResponseDto);
        }
        return passCoverLetterResponseDtos;
    }
}
