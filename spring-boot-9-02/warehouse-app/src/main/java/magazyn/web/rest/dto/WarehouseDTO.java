package magazyn.web.rest.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class WarehouseDTO {
    private int id;
    private String name;
    private String location;
}
