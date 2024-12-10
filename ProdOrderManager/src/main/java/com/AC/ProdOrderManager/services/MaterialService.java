package com.AC.ProdOrderManager.services;

import com.AC.ProdOrderManager.dtos.material.RegisterBaseMaterialRequestDTO;
import com.AC.ProdOrderManager.dtos.material.RegisterMaterialDetailRequestDTO;
import com.AC.ProdOrderManager.exceptions.InvalidDataException;
import com.AC.ProdOrderManager.exceptions.InvalidField;
import com.AC.ProdOrderManager.exceptions.material.BaseMaterialAlreadyExistsException;
import com.AC.ProdOrderManager.exceptions.material.BaseMaterialNotFoundException;
import com.AC.ProdOrderManager.models.product.ProductModel;
import com.AC.ProdOrderManager.models.product.productMaterial.MaterialDetailModel;
import com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial.BaseMaterialModel;
import com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial.UnitType;
import com.AC.ProdOrderManager.repositories.BaseMaterialRepository;
import com.AC.ProdOrderManager.repositories.MaterialDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MaterialService {
    @Autowired
    private BaseMaterialRepository baseMaterialRepository;
    @Autowired
    private MaterialDetailRepository materialDetailRepository;

    public void registerBaseMaterial(RegisterBaseMaterialRequestDTO body) throws InvalidDataException, BaseMaterialAlreadyExistsException{
        validateBaseMaterialRegister(body);

        baseMaterialRepository.save(new BaseMaterialModel(body.name(), UnitType.valueOf(body.unitType())));
    }

    public void validateBaseMaterialInput(RegisterBaseMaterialRequestDTO body) throws InvalidDataException{
        List<InvalidField> invalidFields = new ArrayList<>();

        Set<String> validUnitTypes = Arrays.stream(UnitType.values())
                .map(Enum::name)
                .collect(Collectors.toSet());

        if (body.name() == null || body.name().isBlank()) {
            invalidFields.add(new InvalidField("nome do material", "campo em branco"));
        }
        if (body.unitType() == null || body.unitType().isBlank()) {
            invalidFields.add(new InvalidField("tipo de unidade", "campo em branco"));
        }
        else if (!validUnitTypes.contains(body.unitType())) {
            invalidFields.add(new InvalidField("tipo de unidade", "tipo inexistente"));
        }

        if (!invalidFields.isEmpty()) {
            throw new InvalidDataException(invalidFields);
        }
    }

    public void validateBaseMaterialRegister(RegisterBaseMaterialRequestDTO body) throws BaseMaterialAlreadyExistsException{
        validateBaseMaterialInput(body);

        Optional<BaseMaterialModel> optBaseMaterial = baseMaterialRepository.findById(body.name());

        if (optBaseMaterial.isPresent()) {
            throw new BaseMaterialAlreadyExistsException();
        }
    }

    public List<MaterialDetailModel> convertMaterialDTOsToEntities(Set<RegisterMaterialDetailRequestDTO> materialsDTO, ProductModel product) throws BaseMaterialNotFoundException {
        List<MaterialDetailModel> materials = new ArrayList<>();
        BaseMaterialModel baseMaterial;

        for (RegisterMaterialDetailRequestDTO material : materialsDTO) {
            Optional<BaseMaterialModel> optBaseMaterial = baseMaterialRepository.findById(material.baseMaterialName());

            if (optBaseMaterial.isPresent()) {
                baseMaterial = optBaseMaterial.get();
            }
            else {
                throw new BaseMaterialNotFoundException();
            }

            materials.add(new MaterialDetailModel(
                    product,
                    baseMaterial,
                    material.quantity(),
                    material.totalAmount(),
                    material.paidAmount()));
        }
        return materials;
    }
}
