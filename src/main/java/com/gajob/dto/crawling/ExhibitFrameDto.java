package com.gajob.dto.crawling;

import com.gajob.entity.crawling.ExhibitFrame;
import com.gajob.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExhibitFrameDto {

    private String title;
    private String organization;
    private Set categories;
    private Set targets;
    private String state;
    private String todayState;
    private String url;
    private String imgUrl;
    private User user;

    public ExhibitFrame toEntity(User user) {
        ExhibitFrame exhibitFrame = ExhibitFrame.builder().title(title).organization(organization).categories(categories)
                .targets(targets).state(state).todayState(todayState).url(url).imgUrl(imgUrl).user(user).build();

        return exhibitFrame;
    }
}
