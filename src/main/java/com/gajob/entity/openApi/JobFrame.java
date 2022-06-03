package com.gajob.entity.openApi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gajob.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class JobFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_posting_id")
    private Long id;

    @Column
    private String career;                  // 경력

    @Column
    private Long employTypeCode;            // 고용형태코드(empTpCd)

    @Column
    private Long lastModifyDate;            // 최종수정일(smodifyDtm)

    @Column
    private String infoSource;              // 정보제공처(infoSvc)

    @Column
    private String title;                   // 채용제목

    @Column
    private String closeDate;               // 마감일자(closeDt)

    @Column
    private String basicAddress;            // 근무지 기본주소(basicAddr)

    @Column
    private String prefCode;                // 우대조건 코드(prefCd)

    @Column
    private String registrationDate;        // 등록일자(regDt)

    @Column
    private String wantedMobileInfoUrl;     // 워크넷 모바일 채용정보 URL

    @Column
    private String workType;                // 근무형태(holidayTpNm)

    @Column
    private Long maxSalary;                 // 최대임금액(maxSal)

    @Column
    private String wantedInfoUrl;           // 워크넷 채용정보 URL

    @Column
    private String salaryType;              // 임금형태(salTpNm)

    @Column
    private String minEdu;                  // 최소학력(minEdubg)

    @Column
    private Long minSalary;                 // 최소임금액(minSal)

    @Column
    private String company;                 // 회사명

    @Column
    private Long streetNameAddress;         // 근무지 도로명주소(strnmCd)

    @Column
    private Long jobCode;                   // 직종코드(jobsCd)

    @Column
    private String authNum;                 // 구인인증번호(wantedAuthNo)

    @Column
    private String region;                  // 근무지역

    @Column
    private String salary;                  // 급여(sal)

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
