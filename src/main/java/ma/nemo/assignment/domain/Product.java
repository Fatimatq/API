package ma.nemo.assignment.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
@ToString
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @Column(unique = true, nullable = false)
  private String productCode;

  @Column(nullable = false)
  private String productName;

  private String description;

  private Double unitPrice;

  private Integer quantityInStock;
  //todo you can use jpa annotation wille be automatic insert date
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime creationDate;
//todo you can use jpa annotation wille be automatic update this field
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime modificationDate;
  private Integer quantityThreshold;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ReturnProduct> returns;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Supply> supplies;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Sale> sales;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TransactionHistory> transactions;


}