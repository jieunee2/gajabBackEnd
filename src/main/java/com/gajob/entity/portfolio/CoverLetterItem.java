package com.gajob.entity.portfolio;

import com.gajob.entity.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class CoverLetterItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(columnDefinition = "TEXT", length = 3000, nullable = false)
    private String question;        // 자소서 문항 질문

    @Column(columnDefinition = "TEXT", length = 3000, nullable = false)
    private String answer;          // 자소서 문항 응답

    @Column(name = "created_date")
    @CreatedDate
    private String createdDate;     // 자소서 문항 작성일

    @Column(name = "modified_date")
    @LastModifiedDate
    private String modifiedDate;    // 자소서 문항 수정일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cover_letter_id")
    private CoverLetter coverLetter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // 자소서 문항 수정
    public void update(String question, String answer, String modifiedDate) {
        this.question = question;
        this.answer = answer;
        this.modifiedDate = modifiedDate;
    }

}
