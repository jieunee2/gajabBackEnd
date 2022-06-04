package com.gajob.dto.openApi;

import com.gajob.entity.openApi.JobScrap;
import lombok.Data;

@Data
public class JobScrapResponseDto {

    private Long id;
    private String title;
    private String company;
    private String salaryType;
    private String salary;
    private String closeDate;
    private String career;
    private String region;
    private String scrapDate;
    private String wantedInfoUrl;

    public JobScrapResponseDto(JobScrap jobScrap) {
        this.id = jobScrap.getId();
        this.title = jobScrap.getJobFrame().getTitle();
        this.company = jobScrap.getJobFrame().getCompany();
        this.salaryType = jobScrap.getJobFrame().getSalaryType();
        this.salary = jobScrap.getJobFrame().getSalary();
        this.closeDate = jobScrap.getJobFrame().getCloseDate();
        this.career = jobScrap.getJobFrame().getCareer();
        this.region = jobScrap.getJobFrame().getRegion();
        this.scrapDate = jobScrap.getCreatedDate();
        this.wantedInfoUrl = jobScrap.getJobFrame().getWantedInfoUrl();
    }

}
