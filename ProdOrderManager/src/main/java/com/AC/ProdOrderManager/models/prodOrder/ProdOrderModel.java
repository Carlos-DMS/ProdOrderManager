package com.AC.ProdOrderManager.models.prodOrder;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "prod_order")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProdOrderModel {

    @Id
    private String id;
    private String customer;
    private String product;
    private Integer quantity;
    private final LocalDateTime openingDate = LocalDateTime.now();
    private LocalDate lastReviewDate;
    private LocalDate deliveryDate;
    private ProdOrderStatus status = ProdOrderStatus.SEPARANDO_MATERIAIS;

    @Transient
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ProdOrderModel(String id, String customer, String product, Integer quantity, String deliveryDate) {
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
