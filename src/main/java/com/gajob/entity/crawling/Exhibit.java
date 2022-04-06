package com.gajob.entity.crawling;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Exhibit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;           //공모전 이름

    private String organization;    //공모전 주최기관

    private String state;           //공모전 진행상태

    private String todayState;      //공모전 오늘 진행상태

    private String url;             //공모전 URL

    private String imgUrl;          //공모전 URL

    public Exhibit(String title, String organization, String state, String todayState, String url, String imgUrl) {
        this.title = title;
        this.organization = organization;
        this.state = state;
        this.todayState = todayState;
        this.url = url;
        this.imgUrl = imgUrl;
    }
}
