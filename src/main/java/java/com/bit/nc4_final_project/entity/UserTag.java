package java.com.bit.nc4_final_project.entity;

import com.bit.nc4_final_project.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "T_USER_TAG")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTag {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TagSeqGenerator"
    )
    @Column(name = "user_tag_seq")
    private Integer seq;
    @Setter
    private String content;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_seq")
    @JsonBackReference
    private User user;
}
