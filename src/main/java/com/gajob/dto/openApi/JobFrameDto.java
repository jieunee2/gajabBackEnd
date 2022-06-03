package com.gajob.dto.openApi;

import com.gajob.entity.openApi.JobFrame;
import com.gajob.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobFrameDto {

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
    private User user;

    public JobFrame toEntity(User user) {
        JobFrame jobFrame = JobFrame.builder().career(career).employTypeCode(employTypeCode).lastModifyDate(lastModifyDate)
                .infoSource(infoSource).title(title).closeDate(closeDate).basicAddress(basicAddress).prefCode(prefCode)
                .registrationDate(registrationDate).wantedMobileInfoUrl(wantedMobileInfoUrl).workType(workType).maxSalary(maxSalary)
                .wantedInfoUrl(wantedInfoUrl).salaryType(salaryType).minEdu(minEdu).minSalary(minSalary).company(company)
                .streetNameAddress(streetNameAddress).jobCode(jobCode).authNum(authNum).region(region).salary(salary).user(user).build();

        return jobFrame;
    }
}
