package task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDto {
    private String name;
    private String phoneNumber;
    private String street;
    private String homeNumber;
    private Integer depId;

}
