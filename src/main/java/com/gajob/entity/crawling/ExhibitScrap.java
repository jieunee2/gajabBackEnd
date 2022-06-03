package com.gajob.entity.crawling;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gajob.entity.basetime.TimeEntity;
import com.gajob.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class ExhibitScrap extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exhibit_id")
    private Exhibit exhibit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"exhibit"})
    private User user;

    public ExhibitScrap(Exhibit exhibit, User user) {
        this.exhibit = exhibit;
        this.user = user;
    }

}
