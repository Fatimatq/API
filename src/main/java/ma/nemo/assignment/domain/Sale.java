package ma.nemo.assignment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Sales")
public class Sale {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long saleId;

  @ManyToOne
  @JoinColumn(name = "productId")
  private Product product;

  private Integer soldQuantity;

  private Double totalPrice;

  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime saleDate;

  @ManyToOne
  @JoinColumn(name = "userId")
  private User user;


}
