package com.gajob.entity.crawling;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    private String title;                              // 공모전 이름

    private String organization;                       // 공모전 주최기관

    @ElementCollection
    @CollectionTable(name = "exhibit_category", joinColumns = @JoinColumn(name = "exhibit_id"))
    @Column(name = "category_name")
    private Set<String> categories = new HashSet<>();   // 공모전 카테고리

    @ElementCollection
    @CollectionTable(name = "exhibit_target", joinColumns = @JoinColumn(name = "exhibit_id"))
    @Column(name = "target_name")
    private Set<String> targets = new HashSet<>();      // 공모전 참가대상

    private String state;                               // 공모전 진행상태

    private String todayState;                          // 공모전 오늘 진행상태

    private String url;                                 // 공모전 URL

    private String imgUrl;                              // 공모전 이미지 URL

    public Exhibit(String title, String organization, Set categories, Set targets, String state, String todayState, String url, String imgUrl) {
        this.title = title;
        this.organization = organization;
        this.categories = categories;
        this.targets = targets;
        this.state = state;
        this.todayState = todayState;
        this.url = url;
        this.imgUrl = imgUrl;
    }
}

