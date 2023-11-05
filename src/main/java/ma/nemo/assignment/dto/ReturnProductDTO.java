package ma.nemo.assignment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnProductDTO {
    @NotBlank(message = "Product Code is required")
    @Size(min = 3, max = 10, message = "Product code length must be between 3 and 10 characters")
    private String productCode;

    @NotNull(message = "Quantity in stock is required")
    @Min(value = 1, message = "Quantity in stock must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Reason should not be null or empty")
    private String reason;
}
