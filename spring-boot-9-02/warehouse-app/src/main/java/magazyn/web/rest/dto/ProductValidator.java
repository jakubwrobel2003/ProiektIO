package magazyn.web.rest.dto;

import lombok.RequiredArgsConstructor;
import magazyn.model.Producer;
import magazyn.model.Product;
import magazyn.service.ProductService;
import magazyn.web.rest.dto.ProductDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class ProductValidator implements Validator {

    private final ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        // Określamy, że walidator obsługuje klasę ProductDTO
        return ProductDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO dto = (ProductDTO) target;

        // Sprawdzanie duplikatu SKU
        if (dto.getSkuCode() != null) {
            Product existing = productService.getProductbySkuCode(dto.getSkuCode());
            if (existing != null) {
                // To sprawi, że errors.hasErrors() w kontrolerze będzie TRUE
                errors.rejectValue("skuCode", "product.sku.duplicate", "Ten kod SKU już istnieje!");
            }
        }
    }
}