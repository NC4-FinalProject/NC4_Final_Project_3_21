package java.com.bit.nc4_final_project.entity;


import com.bit.nc4_final_project.dto.review.ReviewDTO;
import com.bit.nc4_final_project.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_COM_REVIEW")
@SequenceGenerator(
        name = "ReviewSeqGenerator",
        sequenceName = "T_REVIEW_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ReviewSeqGenerator"
    )
    @Column(name = "com_review_seq")
    private Integer seq;
    private String title;
    private String content;
    private String writer;
    private int rating;
    private LocalDateTime regDate;

    @Setter
    private String travalId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @Transient
    private String searchCondition;
    @Transient
    private String searchKeyword;

    public ReviewDTO toDTO() {
        return ReviewDTO.builder()
                .seq(this.seq)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .rating(this.rating)
                .regDate(LocalDateTime.parse(this.regDate.toString()))
//                .travel(travelDTO)
                .build();
    }
}
