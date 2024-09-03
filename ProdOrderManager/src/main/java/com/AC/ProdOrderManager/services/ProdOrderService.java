package com.AC.ProdOrderManager.services;

import com.AC.ProdOrderManager.dtos.prodOrder.OrderRegisterRequestDTO;
import com.AC.ProdOrderManager.exceptions.InvalidDataException;
import com.AC.ProdOrderManager.exceptions.InvalidDateFormatException;
import com.AC.ProdOrderManager.exceptions.InvalidField;
import com.AC.ProdOrderManager.models.prodOrder.ProdOrderModel;
import com.AC.ProdOrderManager.repositories.ProdOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProdOrderService {
    @Autowired
    ProdOrderRepository prodOrderRepository;

    public void register(OrderRegisterRequestDTO body) throws InvalidDataException, InvalidDateFormatException {
        prodOrderRepository.save(validateRegister(body));
    }

    private ProdOrderModel validateRegister(OrderRegisterRequestDTO body) throws InvalidDataException, InvalidDateFormatException {
        List<InvalidField> invalidFields = new ArrayList<>();

        if (body.costumer() == null || body.costumer().isBlank()) {
            invalidFields.add(new InvalidField("cliente", "campo em branco"));
        }

        if (body.product() == null || body.product().isBlank()) {
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

        try {
            return new ProdOrderModel(body.costumer(), body.product(), body.quantity(), body.deliveryDate());
        }
        catch (DateTimeParseException exception) {
            throw new InvalidDateFormatException();
        }
    }
}
