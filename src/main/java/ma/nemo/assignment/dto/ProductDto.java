package ma.nemo.assignment.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

  @NotBlank(message = "Product Code is required")
  @Size(min = 3, max = 10, message = "Product code length must be between 3 and 10 characters")
  private String productCode;

  @NotBlank(message = "Product name is required")
  private String productName;

  @NotBlank(message = "description should not be null or empty")

  private String description;
  @NotNull(message = "Unit price is required")
  @DecimalMin(value = "0.0", message = "Unit price must be a positive number")
  private Double unitPrice;

  @NotNull(message = "Quantity in stock is required")
  @Min(value = 1, message = "Quantity in stock must be at least 1")
  private Integer quantityInStock;


  @NotNull(message = "Threshold quantity is required")
  @Min(value = 1, message = "Threshold quantity must be at least 1")
  private Integer quantityThreshold;

  }
