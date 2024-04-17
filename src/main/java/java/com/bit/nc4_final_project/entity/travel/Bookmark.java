package java.com.bit.nc4_final_project.entity.travel;

import com.bit.nc4_final_project.dto.travel.BookmarkDTO;
import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.dto.user.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_TRAVEL_BOOKMARK")
@SequenceGenerator(
        name = "TravelBookmarkSeqGenerator",
        sequenceName = "T_TRAVEL_BOOKMARK_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bookmark {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TravelBookmarkSeqGenerator"
    )
    @Column(name = "travel_bookmark_seq")
    private Long seq;
    @Builder.Default
    private LocalDateTime bookmarkDate = LocalDateTime.now();
    private String travelId;
    private Integer userSeq;

    public BookmarkDTO toDTO(TravelDTO travelDTO, UserDTO userDTO) {
        return BookmarkDTO.builder()
                .seq(this.seq)
                .bookmarkDate(this.bookmarkDate)
                .travel(travelDTO)
                .user(userDTO)
                .build();
    }
}