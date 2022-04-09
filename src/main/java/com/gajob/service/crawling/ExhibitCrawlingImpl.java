package com.gajob.service.crawling;

import com.gajob.dto.crawling.ExhibitDto;
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

    public String getExhibitUrl(int page) {
        return URL + "?" + PAGE + page;
    }

    // Jsoup을 이용하여 사이트 내 데이터 추출
    public List<Exhibit> getExhibitData(String URL) throws IOException {
        List<Exhibit> exhibitList = new ArrayList<>();

        // 전체 html 코드
        Document document = Jsoup.connect(URL).get();

        // 공모전 이름 가져오기
        Elements titleElements = document.select("div.gallery-list p.tit");

        // 공모전 주최기관명 가져오기
        Elements organizationElements = document.select("div.gallery-list p.source");

        // 공모전 진행상태 가져오기
        Elements stateElements = document.select("div.gallery-list i.ico-state.blue");

        // 공모전 오늘의 진행상태 가져오기
        Elements todayStateElements = document.select("div.gallery-list p.d-day");

        // 공모전 URL 가져오기
        Elements urlElements = document.select("div.caption.a-c p.tit a");

        // 공모전 이미지 URL 가져오기
        Elements imgUrlElements = document.select("div.gallery-list p.img img");

        for (int i = 0; i < titleElements.size(); i++) {
            // titleElements 추출
            String title = titleElements.get(i).text();
            // organization 추출
            String organization = organizationElements.get(i).text();
            // state 추출
            String state = stateElements.get(i).text();
            // todayState 추출
            String todayState = todayStateElements.get(i).text();
            // 공모전 URL 추출
            String url = urlElements.get(i).getElementsByAttribute("onClick").attr("onClick");
            // 공모전 이미지 URL 추출
            String imgUrl = imgUrlElements.get(i).getElementsByAttribute("src").attr("src");

            // 크롤링한 url을 포함한 html 코드 내에서 숫자 축출
            String number = url.replaceAll("[^0-9]", "");

            Exhibit exhibit = new Exhibit(title, organization, state, todayState, "www.work.go.kr/empSpt/exhibit/exhibit/exhibitDtl.do?contestSeq=" +  number, imgUrl);
            exhibitList.add(exhibit);
        }
        return exhibitList;
    }

    // Crawling한 공모전 정보들을 DB에 저장한다.
    @Transactional
    public ExhibitDto exhibitSave(ExhibitDto exhibitDto) throws Exception {
        List<Exhibit> list = new ArrayList<>();

        int page = 1;

        // 반복문을 통해서 5페이지까지의 정보를 가져온다. (20페이지까지 있으나 너무 많은 데이터를 가져올 필요가 없으므로 적당량만 추출)
        for (int i = 1; i <= 5; i++) {
            String url = getExhibitUrl(i);
            List<Exhibit> exhibitData = getExhibitData(url);

            // for 문을 통해서 getNewsData 메소드를 통해 받아온 데이터들을 newsRepository에 저장한다.
            for (Exhibit exhibit : exhibitData) {
                exhibitRepository.save(exhibit);
            }
        }
        return null;
    }
}
