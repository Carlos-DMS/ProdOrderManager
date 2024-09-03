package com.AC.ProdOrderManager.models.prodOrder;

import com.AC.ProdOrderManager.repositories.ProdOrderRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "prod_order")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class ProdOrderModel {
    @Autowired
    ProdOrderRepository prodOrderRepository;

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
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @PrePersist
    private void onCreate() {
        this.id = generateNextId();
    }

    public ProdOrderModel(String customer, String product, Integer quantity, String deliveryDate) {
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.deliveryDate = LocalDate.parse(deliveryDate, dateFormatter);
    }

    private String generateNextId() {
        String currentYear = String.valueOf(Year.now().getValue()).substring(2);
        String lastId = prodOrderRepository.findLastIdForYear(currentYear);
        int lastSequenceNumber = 0;

        if (lastId != null) {
            lastSequenceNumber = Integer.parseInt(lastId.substring(0, 4));
        }

        int nextSequenceNumber = lastSequenceNumber + 1;

        return String.format("%04d", nextSequenceNumber) + "-" + currentYear;
    }
}
