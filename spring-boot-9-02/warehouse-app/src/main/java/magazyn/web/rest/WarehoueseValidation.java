package magazyn.web.rest;
import lombok.RequiredArgsConstructor;
import magazyn.model.Warehouse;
import magazyn.service.WarehouseService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
@RequiredArgsConstructor
public class WarehoueseValidation implements Validator {

    private final WarehouseService warehouseService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(Warehouse.class);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Warehouse warehouse = (Warehouse) target;
        if (warehouse.getName() == null || warehouse.getName().isEmpty()) {
            errors.rejectValue("name", "name.empty", "Warehouse name cannot be empty");
        } else if (warehouseService.getAllWarehouses().stream()
                .anyMatch(w -> w.getName().equalsIgnoreCase(warehouse.getName()))) {
            errors.rejectValue("name", "name.duplicate", "Warehouse name must be unique");
        }
    }
}
