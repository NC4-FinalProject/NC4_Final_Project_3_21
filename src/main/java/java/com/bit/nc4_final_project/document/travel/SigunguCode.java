package java.com.bit.nc4_final_project.document.travel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "area_code")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SigunguCode {
    private String code;
    private String name;
}
