package com.gajob.entity.openApi;

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
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String career;                  // 경력

    private Long employTypeCode;            // 고용형태코드(empTpCd)

    private Long lastModifyDate;            // 최종수정일(smodifyDtm)

    private String infoSource;              // 정보제공처(infoSvc)

    private String title;                   // 채용제목

    private String closeDate;               // 마감일자(closeDt)

    private String basicAddress;            // 근무지 기본주소(basicAddr)

    private String prefCode;                // 우대조건 코드(prefCd)

    private String registrationDate;        // 등록일자(regDt)

    private String wantedMobileInfoUrl;     // 워크넷 모바일 채용정보 URL

    private String workType;                // 근무형태(holidayTpNm)

    private Long maxSalary;                 // 최대임금액(maxSal)

    private String wantedInfoUrl;           // 워크넷 채용정보 URL

    private String salaryType;              // 임금형태(salTpNm)

    private String minEdu;                  // 최소학력(minEdubg)

    private Long minSalary;                 // 최소임금액(minSal)

    private String company;                 // 회사명

    private Long streetNameAddress;         // 근무지 도로명주소(strnmCd)

    private Long jobCode;                  // 직종코드(jobsCd)

    private String authNum;                 // 구인인증번호(wantedAuthNo)

    private String region;                  // 근무지역

    private String salary;                  // 급여(sal)

    public Job(String career, Long employTypeCode, Long lastModifyDate, String infoSource, String title, String closeDate,
               String basicAddress, String prefCode, String registrationDate, String wantedMobileInfoUrl, String workType,
               Long maxSalary, String wantedInfoUrl, String salaryType, String minEdu, Long minSalary, String company,
               Long streetNameAddress, Long jobCode, String authNum, String region, String salary) {
        this.career = career;
        this.employTypeCode = employTypeCode;
        this.lastModifyDate = lastModifyDate;
        this.infoSource = infoSource;
        this.title = title;
        this.closeDate = closeDate;
        this.basicAddress = basicAddress;
        this.prefCode = prefCode;
        this.registrationDate = registrationDate;
        this.wantedMobileInfoUrl = wantedMobileInfoUrl;
        this.workType = workType;
        this.maxSalary = maxSalary;
        this.wantedInfoUrl = wantedInfoUrl;
        this.salaryType = salaryType;
        this.minEdu = minEdu;
        this.minSalary = minSalary;
        this.company = company;
        this.streetNameAddress = streetNameAddress;
        this.jobCode = jobCode;
        this.authNum = authNum;
        this.region = region;
        this.salary = salary;
    }
}
