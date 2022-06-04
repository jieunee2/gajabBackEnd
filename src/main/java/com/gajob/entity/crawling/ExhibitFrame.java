package com.gajob.entity.crawling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gajob.entity.user.User;
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
public class ExhibitFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibit_id")
    private Long id;

    @Column
    private String title;   // 공모전 이름

    @Column
    private String organization;    // 공모전 주최기관

    @ElementCollection
    @CollectionTable(name = "exhibit_frame_category", joinColumns = @JoinColumn(name = "exhibit_frame_id"))
    @Column(name = "category_name")
    private Set<String> categories = new HashSet<>();   // 공모전 카테고리

    @ElementCollection
    @CollectionTable(name = "exhibit_frame_target", joinColumns = @JoinColumn(name = "exhibit_frame_id"))
    @Column(name = "target_name")
    private Set<String> targets = new HashSet<>(); // 공모전 참가대상

    @Column
    private String state;   // 공모전 진행상태

    @Column
    private String todayState;  // 공모전 오늘 진행상태

    @Column
    private String url; // 공모전 URL

    @Column
    private String imgUrl;  // 공모전 이미지 URL

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
