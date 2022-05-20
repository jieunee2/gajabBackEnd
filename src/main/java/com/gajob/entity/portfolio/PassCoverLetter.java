package com.gajob.entity.portfolio;

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
public class PassCoverLetter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName; // 기업명

    private String workType;    // 근무형태

    private String objective;   // 지원분야

    @ElementCollection
    @CollectionTable(name = "pass_cover_letter_specification", joinColumns = @JoinColumn(name = "pass_cover_letter_id"))
    @Column(name = "specification_info")
    private Set<String> specifications = new HashSet<>();   // 합격자 스펙

    private String url; // 합격 자소서 url

    private String imgUrl;  // 기업 로고 이미지 url

    public PassCoverLetter(String companyName, String workType, String objective, Set specifications, String url, String imgUrl) {
        this.companyName = companyName;
        this.workType = workType;
        this.objective = objective;
        this.specifications = specifications;
        this.url = url;
        this.imgUrl = imgUrl;
    }
}
