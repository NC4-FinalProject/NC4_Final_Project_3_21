package com.bit.nc4_final_project.entity.community;

import com.bit.nc4_final_project.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "T_COM_SUBSCRIBER")
@AllArgsConstructor
@Builder
@IdClass(CommunitySubscriberId.class)
public class CommunitySubscriber {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "com_seq")
    private Community community;
}
