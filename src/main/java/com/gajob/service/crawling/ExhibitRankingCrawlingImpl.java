package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitRankingDto;
import com.gajob.dto.crawling.ExhibitRankingResponseDto;
import com.gajob.entity.crawling.ExhibitRanking;
import com.gajob.enumtype.ErrorCode;
import com.gajob.exception.CustomException;
import com.gajob.repository.crawling.ExhibitRankingRepository;
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
public class ExhibitRankingCrawlingImpl implements ExhibitRankingCrawling {

    private final ExhibitRankingRepository exhibitRankingRepository;

    // 공모전 랭킹을 보여주는 캠퍼스몬 url
    private final String URL = "http://campusmon.jobkorea.co.kr/Contest/Ranking";

    // 각 페이지 별로 url 생성하기
    @Override
    public String getExhibitRankingUrl(int page) {
        return URL + "?" + "PAGE=" + page;
    }

    // 공모전 랭킹 정보들 크롤링하기
    @Override
    public List<ExhibitRanking> getExhibitRankingData(String URL) throws IOException {
        List<ExhibitRanking> exhibitRankingList = new ArrayList<>();

        // 공모전 랭킹 전체 html 코드
        Document document = Jsoup.connect(URL).get();

        // 공모전 이름 가져오기
        Elements titleElements = document.select("td.tl p.ti");
        // 공모전 순위 가져오기
        Elements rankingElements = document.select("tr td.num strong");
        // 공모전 순위상태 가져오기
        Elements rankingStateElements = document.select("tr td.num em");
        // 공모전 d-day 포함된 text 가져오기
        Elements dDayElements = document.select("tr td.day");
        // 공모전 오늘 진행상태 가져오기
        Elements stateElements = document.select("td.day span");
        // 공모전 부분 URL 가져오기
        Elements partUrlElements = document.select("td.tl p.ti a");

        for (int i = 0; i < titleElements.size(); i++) {
            // title 추출
            String title = titleElements.get(i).text();
            // ranking 추출
            String ranking = rankingElements.get(i).text();
            // rankingState 추출
            String rankingState = rankingStateElements.get(i).text();
            // d-day 포함된 text 추출
            String dDayText = dDayElements.get(i).text();
            // state 추출
            String state = stateElements.get(i).text();
            // partUrl 추출
            String partUrl = partUrlElements.get(i).getElementsByAttribute("href").attr("href");

            // 전체 url 생성
            String url = "http://campusmon.jobkorea.co.kr" + partUrl;

            // 개별 공모전 전체 html 코드
            Document exhibitDocument = Jsoup.connect(url).get();

            // 개별 공모전 정보 내 유형들 가져오기
            Elements typeElements = exhibitDocument.select("div.infoPro div.info ul.tx li strong.lbt");
            // 개별 공모전 정보 내 유형별 설명들 가져오기
            Elements textElements = exhibitDocument.select("div.infoPro div.info ul.tx li span");
            // 개별 공모전 정보 내 이미지 url 가져오기
            Elements imgUrlElements = exhibitDocument.select("div.infoPro div.info p.pht span.img img");

            // imgUrl 추출
            String imgUrl = imgUrlElements.get(0).getElementsByAttribute("src").attr("src");

            String host = "";
            String perks = "";
            String category = "";
            String target = "";
            for (int j = 0; j < typeElements.size(); j++) {
                if (typeElements.get(j).text().equals("주최"))
                    host = textElements.get(j).text();                  // host 추출

                if (typeElements.get(j).text().equals("특전"))
                    perks = textElements.get(j).text();                 // perks 추출

                if (typeElements.get(j).text().equals("응모분야"))
                    category = textElements.get(j).text();              // category 추출

                if (typeElements.get(j).text().equals("응모대상"))
                    target = textElements.get(j).text();                // target 추출
            }

            // 추출한 category를 분류하여 배열에 저장
            String[] categoryArr = category.split(",");

            // 분류한 category를 Set에 저장
            Set<String> categories = new HashSet<>();
            for (int j = 0; j < categoryArr.length; j++)
                categories.add(categoryArr[j]);

            // 추출한 target을 분류하여 배열에 저장
            String[] targetArr = target.split(",");

            // 분류한 target을 Set에 저장
            Set<String> targets = new HashSet<>();
            for (int j = 0; j < targetArr.length; j++)
                targets.add(targetArr[j]);

            // d-day가 포함된 text 내에서 d-day만 뽑아내기 위한 조건문
            String dDay;
            if (dDayText.contains("오늘마감")) dDay = dDayText.substring(0, 4);
            else dDay = dDayText.replaceAll("[\uAC00-\uD7A3]", "")
                    .replaceAll(" ", "");

            ExhibitRanking exhibitRanking = new ExhibitRanking(title, ranking, rankingState,
                    host, perks, categories, targets, dDay, state, url, imgUrl);

            exhibitRankingList.add(exhibitRanking);
        }
        return exhibitRankingList;
    }

    // 크롤링한 공모전 랭킹 정보들을 DB에 저장하기
    @Transactional
    @Override
    public ExhibitRankingDto saveExhibitRanking(ExhibitRankingDto exhibitRankingDto) throws Exception {
        List<ExhibitRanking> list = new ArrayList<>();

        for (int i = 1; i <= 2; i++) {
            String url = getExhibitRankingUrl(i);
            System.out.println("->" + url);
            List<ExhibitRanking> exhibitRankingData = getExhibitRankingData(url);

            for (ExhibitRanking exhibitRanking : exhibitRankingData) {
                exhibitRankingRepository.save(exhibitRanking);
            }
        }
        return null;
    }

    // DB에 저장한 공모전 랭킹 정보 읽어오기
    @Transactional
    @Override
    public List<ExhibitRankingResponseDto> getAllExhibitRanking() {
        List<ExhibitRankingResponseDto> exhibitRankingResponseDtos = new ArrayList<>();

        for (ExhibitRanking exhibitRanking : exhibitRankingRepository.findAll()) {
            ExhibitRankingResponseDto exhibitRankingResponseDto = new ExhibitRankingResponseDto(exhibitRanking);
            exhibitRankingResponseDtos.add(exhibitRankingResponseDto);
        }
        return exhibitRankingResponseDtos;
    }

    // 공모전 랭킹 정보 낱개 조회
    @Transactional
    public ExhibitRankingResponseDto getExhibitRanking(Long exhibitRankingId) {
        ExhibitRanking exhibitRanking = exhibitRankingRepository.findById(exhibitRankingId)
                .orElseThrow(() -> new CustomException(ErrorCode.COVER_LETTER_ID_NOT_EXIST));

        ExhibitRankingResponseDto exhibitRankingResponseDto = new ExhibitRankingResponseDto(exhibitRanking);

        return exhibitRankingResponseDto;
    }
}