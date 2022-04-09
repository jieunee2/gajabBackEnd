package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitDto;

import com.gajob.dto.crawling.ExhibitResponseDto;

import com.gajob.entity.crawling.Exhibit;
import com.gajob.repository.crawling.ExhibitRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExhibitCrawlingImpl implements ExhibitCrawling {

    //워크넷 공모전 모음 크롤링
    private final ExhibitRepository exhibitRepository;

    private final String URL = "https://www.work.go.kr/empSpt/exhibit/exhibit/exhibitPicList.do";
    private final String PAGE = "";

    @Override
    public String getExhibitUrl(int page) {
        return URL + "?" + PAGE + page;
    }

    // Jsoup을 이용하여 사이트 내 데이터 추출
    @Override
    public List<Exhibit> getExhibitData(String URL) throws IOException {
        List<Exhibit> exhibitList = new ArrayList<>();

        // 전체 html 코드
        Document document = Jsoup.connect(URL).get();

        // 공모전 이름 가져오기
        Elements titleElements = document.select("div.gallery-list p.tit");
        // 공모전 진행상태 가져오기
        Elements stateElements = document.select("div.gallery-list i.ico-state.blue");
        // 공모전 오늘의 진행상태 가져오기
        Elements todayStateElements = document.select("div.gallery-list p.d-day");
        // 공모전 URL이 포함된 html 코드 가져오기
        Elements htmlUrlElements = document.select("div.caption.a-c p.tit a");
        // 공모전 이미지 URL 가져오기
        Elements imgUrlElements = document.select("div.gallery-list p.img img");

        for (int i = 0; i < titleElements.size(); i++) {
            // titleElements 추출
            String title = titleElements.get(i).text();
            // state 추출
            String state = stateElements.get(i).text();
            // todayState 추출
            String todayState = todayStateElements.get(i).text();
            // 공모전 URL이 포함된 html 코드 추출
            String htmlUrl = htmlUrlElements.get(i).getElementsByAttribute("onClick").attr("onClick");
            // url이 포함된 html 코드 내에서 숫자 추출
            String number = htmlUrl.replaceAll("[^0-9]", "");
            // 'http://'를 제거한 공모전 url 생성
            String url = "www.work.go.kr/empSpt/exhibit/exhibit/exhibitDtl.do?contestSeq=" + number;
            // 'http://'를 포함한 공모전 url 생성
            String entireUrl = "http://" + url;
            // 공모전 이미지 URL 추출
            String imgUrl = imgUrlElements.get(i).getElementsByAttribute("src").attr("src");

            // 각각의 공모전 정보가 담긴 전체 html 코드
            Document exhibitDocument = Jsoup.connect(entireUrl).get();

            // 현재 해당 공모전 정보 가져오기
            Elements exhibitElements = exhibitDocument.select("div.cont-area ul li p");

            // organization 추출
            String organization = exhibitElements.get(3).text();
            // category 추출
            String category = exhibitElements.get(5).text();
            // target 추출
            String target = exhibitElements.get(7).text();

            Exhibit exhibit = new Exhibit(title, organization, category, target, state, todayState, url, imgUrl);
            exhibitList.add(exhibit);
        }
        return exhibitList;
    }

    // Crawling한 공모전 정보들을 DB에 저장한다.
    @Transactional
    @Override
    public ExhibitDto saveExhibit(ExhibitDto exhibitDto) throws Exception {
        List<Exhibit> list = new ArrayList<>();

        //    int page = 5;

        // 반복문을 통해서 5페이지까지의 정보를 가져온다. (20페이지까지 있으나 너무 많은 데이터를 가져올 필요가 없으므로 적당량만 추출)
        for (int i = 1; i <= 5; i++) {
            String url = getExhibitUrl(i);
            System.out.println("->" + url);
            List<Exhibit> exhibitData = getExhibitData(url);
            // for 문을 통해서 getNewsData 메소드를 통해 받아온 데이터들을 newsRepository에 저장한다.
            for (Exhibit exhibit : exhibitData) {
                exhibitRepository.save(exhibit);
            }
        }
        return null;
    }

    // DB에 저장한 공모전 정보 읽어오기
    @Transactional
    @Override
    public List<ExhibitResponseDto> getExhibit() {
        List<ExhibitResponseDto> exhibitResponseDtos = new ArrayList<>();
        for (Exhibit exhibit : exhibitRepository.findAll()) {
            ExhibitResponseDto exhibitResponseDto = new ExhibitResponseDto(exhibit);
            exhibitResponseDtos.add(exhibitResponseDto);
        }
        return exhibitResponseDtos;
    }
}