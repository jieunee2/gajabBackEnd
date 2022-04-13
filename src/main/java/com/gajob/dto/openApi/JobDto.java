package com.gajob.dto.openApi;

import com.gajob.entity.openApi.Job;
import lombok.Data;

@Data
public class JobDto {

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

    public JobDto(Job job) {
        this.career = job.getCareer();
        this.employTypeCode = job.getEmployTypeCode();
        this.lastModifyDate = job.getLastModifyDate();
        this.infoSource = job.getInfoSource();
        this.title = job.getTitle();
        this.closeDate = job.getCloseDate();
        this.basicAddress = job.getBasicAddress();
        this.prefCode = job.getPrefCode();
        this.registrationDate = job.getRegistrationDate();
        this.wantedMobileInfoUrl = job.getWantedMobileInfoUrl();
        this.workType = job.getWorkType();
        this.maxSalary = job.getMaxSalary();
        this.wantedInfoUrl = job.getWantedInfoUrl();
        this.salaryType = job.getSalaryType();
        this.minEdu = job.getMinEdu();
        this.minSalary = job.getMinSalary();
        this.company = job.getCompany();
        this.streetNameAddress = job.getStreetNameAddress();
        this.jobCode = job.getJobCode();
        this.authNum = job.getAuthNum();
        this.region = job.getRegion();
        this.salary = job.getSalary();
    }
}
