package com.AC.ProdOrderManager.services;

import com.AC.ProdOrderManager.dtos.prodOrder.GetOrdersResponseDTO;
import com.AC.ProdOrderManager.dtos.prodOrder.OrderRegisterRequestDTO;
import com.AC.ProdOrderManager.exceptions.InvalidDataException;
import com.AC.ProdOrderManager.exceptions.InvalidField;
import com.AC.ProdOrderManager.exceptions.prodOrder.NoMatchingOrdersException;
import com.AC.ProdOrderManager.models.prodOrder.ProdOrderModel;
import com.AC.ProdOrderManager.models.prodOrder.ProdOrderStatus;
import com.AC.ProdOrderManager.repositories.ProdOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdOrderService {
    @Autowired
    ProdOrderRepository prodOrderRepository;

    public void register(OrderRegisterRequestDTO body) throws InvalidDataException, DateTimeParseException {
        prodOrderRepository.save(validateRegister(body));
    }

    public List<GetOrdersResponseDTO> getAllOpenOrders () throws NoMatchingOrdersException{
        List<ProdOrderModel> orders = prodOrderRepository.findByStatusIn(List.of(ProdOrderStatus.SEPARANDO_MATERIAIS, ProdOrderStatus.EM_PRODUCAO));

        if (orders.isEmpty()) {
            throw new NoMatchingOrdersException("Não existem ordens em aberto.");
        }
        return convertOrdersToDTOs(orders);
    }

    public List<GetOrdersResponseDTO> getAllCompletedOrders() throws NoMatchingOrdersException{
        List<ProdOrderModel> orders = prodOrderRepository.findByStatusIn(List.of(ProdOrderStatus.FINALIZADA));

        if (orders.isEmpty()) {
            throw new NoMatchingOrdersException("Não existem ordens finalizadas.");
        }
        return convertOrdersToDTOs(orders);
    }

    private ProdOrderModel validateRegister(OrderRegisterRequestDTO body) throws InvalidDataException, DateTimeParseException {
        List<InvalidField> invalidFields = new ArrayList<>();

        if (body.customer() == null || body.customer().isBlank()) {
            invalidFields.add(new InvalidField("cliente", "campo em branco"));
        }

        if (body.product() == null) {
            invalidFields.add(new InvalidField("produto", "campo em branco"));
        }

        if (body.quantity() == null) {
            invalidFields.add(new InvalidField("quantidade", "campo em branco"));
        }
        else if (body.quantity() < 1) {
            invalidFields.add(new InvalidField("quantidade", "a quantidade não pode ser igual ou inferior a 0"));
        }

        if (body.deliveryDate() == null || body.deliveryDate().isBlank()) {
            invalidFields.add(new InvalidField("data de entrega", "campo em branco"));
        }

        if (!invalidFields.isEmpty()) {
            throw new InvalidDataException(invalidFields);
        }
        return new ProdOrderModel(generateNextId(), body.customer(), body.product(), body.quantity(), body.deliveryDate());
    }

    private List<GetOrdersResponseDTO> convertOrdersToDTOs (List<ProdOrderModel> orders) {
        List<GetOrdersResponseDTO> responseDTOS = new ArrayList<>();

        for (ProdOrderModel order : orders) {
            responseDTOS.add(new GetOrdersResponseDTO
                    (
                            order.getId(),
                            order.getCustomer(),
                            order.getProduct(),
                            order.getQuantity(),
                            order.getOpeningDateToString(),
                            order.getLastReviewDateToString(),
                            order.getDeliveryDateToString(),
                            order.getStatus().getStatusReport()
                    )
            );
        }
        return responseDTOS;
    }

    private String generateNextId() {
        String currentYear = String.valueOf(Year.now().getValue()).substring(2);
        Optional<String> lastId = prodOrderRepository.findLastIdForYear(currentYear);
        int lastSequenceNumber = 0;

        if (lastId.isPresent()) {
            lastSequenceNumber = Integer.parseInt(lastId.get().substring(0, 4));
        }

        int nextSequenceNumber = lastSequenceNumber + 1;

        return String.format("%04d", nextSequenceNumber) + "-" + currentYear;
    }
}
