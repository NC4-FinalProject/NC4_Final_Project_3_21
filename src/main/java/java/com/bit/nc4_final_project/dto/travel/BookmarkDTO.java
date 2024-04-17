package java.com.bit.nc4_final_project.dto.travel;

import com.bit.nc4_final_project.dto.travel.TravelDTO;
import com.bit.nc4_final_project.dto.user.UserDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookmarkDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private LocalDateTime bookmarkDate;

    private TravelDTO travel;

    private UserDTO user;
}
