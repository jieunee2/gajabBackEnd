package com.gajob.dto.openApi;

import com.gajob.entity.openApi.JobFrame;
import lombok.Data;

@Data
public class JobFrameResponseDto {

    private Long id;
    private String career;
    private Long employTypeCode;
    private Long lastModifyDate;
    private String infoSource;
    private String title;
    private String closeDate;
    private String basicAddress;
    private String prefCode;
    private String registrationDate;
    private String wantedMobileInfoUrl;
    private String workType;
    private Long maxSalary;
    private String wantedInfoUrl;
    private String salaryType;
    private String minEdu;
    private Long minSalary;
    private String company;
    private Long streetNameAddress;
    private Long jobCode;
    private String authNum;
    private String region;
    private String salary;

    public JobFrameResponseDto(JobFrame jobFrame) {
        this.id = jobFrame.getId();
        this.career = jobFrame.getCareer();
        this.employTypeCode = jobFrame.getEmployTypeCode();
        this.lastModifyDate = jobFrame.getLastModifyDate();
        this.infoSource = jobFrame.getInfoSource();
        this.title = jobFrame.getTitle();
        this.closeDate = jobFrame.getCloseDate();
        this.basicAddress = jobFrame.getBasicAddress();
        this.prefCode = jobFrame.getPrefCode();
        this.registrationDate = jobFrame.getRegistrationDate();
        this.wantedMobileInfoUrl = jobFrame.getWantedMobileInfoUrl();
        this.workType = jobFrame.getWorkType();
        this.maxSalary = jobFrame.getMaxSalary();
        this.wantedInfoUrl = jobFrame.getWantedInfoUrl();
        this.salaryType = jobFrame.getSalaryType();
        this.minEdu = jobFrame.getMinEdu();
        this.minSalary = jobFrame.getMinSalary();
        this.company = jobFrame.getCompany();
        this.streetNameAddress = jobFrame.getStreetNameAddress();
        this.jobCode = jobFrame.getJobCode();
        this.authNum = jobFrame.getAuthNum();
        this.region = jobFrame.getRegion();
        this.salary = jobFrame.getSalary();
    }
}
