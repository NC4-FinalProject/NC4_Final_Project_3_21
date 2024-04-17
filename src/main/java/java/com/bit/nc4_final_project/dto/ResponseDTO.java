package java.com.bit.nc4_final_project.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class ResponseDTO<T> {
    private T item;
    private List<T> items;
    private int errorCode;
    private String errorMessage;
    private int statusCode;
    private Page<T> pageItems;
}
