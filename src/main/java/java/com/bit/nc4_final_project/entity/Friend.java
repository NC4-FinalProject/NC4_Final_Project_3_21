package java.com.bit.nc4_final_project.entity;


import com.bit.nc4_final_project.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_FRIEND")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Friend {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "FriendSeqGenerator"
    )
    @Column(name = "friend_seq")
    private Integer seq;
    private Integer fromUserId;
    private LocalDateTime agreeTime;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

}
