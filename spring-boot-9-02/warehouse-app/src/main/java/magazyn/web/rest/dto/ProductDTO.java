package magazyn.web.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import magazyn.model.Producer;
@Data
public class ProductDTO {
    private int id;
    private String name;
    private String skuCode;
    private Producer brand;
    private String category;
}
