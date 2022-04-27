package com.gajob.entity.portfolio;

import com.gajob.entity.basetime.TimeEntity;
import com.gajob.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class CoverLetter extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cover_letter_id")
    private Long id;

    @Column(nullable = false)
    private String title;                       // 자소서 제목

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "coverLetter", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<CoverLetterItem> CoverLetterItemList;

    // 자소서 수정
    public void update(String title) {
        this.title = title;
    }

}
