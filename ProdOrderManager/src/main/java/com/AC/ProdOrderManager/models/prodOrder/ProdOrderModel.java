package com.AC.ProdOrderManager.models.prodOrder;

import com.AC.ProdOrderManager.models.product.ProductModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "prod_order")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProdOrderModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String customer;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductModel product;
    private Integer quantity;
    private final LocalDateTime openingDate = LocalDateTime.now();
    private LocalDate lastReviewDate;
    private LocalDate deliveryDate;
    private ProdOrderStatus status = ProdOrderStatus.SEPARANDO_MATERIAIS;

    @Transient
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ProdOrderModel(String id, String customer, ProductModel product, Integer quantity, String deliveryDate) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.deliveryDate = LocalDate.parse(deliveryDate, dateFormatter);
    }

    public String getOpeningDateToString() {
        return openingDate.format(dateFormatter);
    }

    public String getLastReviewDateToString() {
        return lastReviewDate != null ? lastReviewDate.format(dateFormatter) : "";
    }

    public String getDeliveryDateToString() {
        return deliveryDate.format(dateFormatter);
    }
}
