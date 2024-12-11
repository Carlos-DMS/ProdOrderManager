package com.AC.ProdOrderManager.services;

import com.AC.ProdOrderManager.dtos.product.RegisterProductRequestDTO;
import com.AC.ProdOrderManager.exceptions.InvalidDataException;
import com.AC.ProdOrderManager.exceptions.InvalidField;
import com.AC.ProdOrderManager.exceptions.material.BaseMaterialNotFoundException;
import com.AC.ProdOrderManager.models.product.ProductModel;
import com.AC.ProdOrderManager.models.product.ProductType;
import com.AC.ProdOrderManager.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MaterialService materialService;

    @Transactional
    public void registerProduct(RegisterProductRequestDTO body) throws BaseMaterialNotFoundException {
        validateRegister(body);

        ProductModel product = convertDTOToEntity(body);

        productRepository.save(product);
    }

    public void validateRegister(RegisterProductRequestDTO body) {
        List<InvalidField> invalidFields = new ArrayList<>();

        Set<String> validProductTypes = Arrays.stream(ProductType.values())
                .map(Enum::name)
                .collect(Collectors.toSet());

        if (body.name() == null || body.name().isBlank()) {
            invalidFields.add(new InvalidField("nome do material", "campo em branco"));
        }
        if (body.productType() == null || body.productType().isBlank()) {
            invalidFields.add(new InvalidField("tipo de produto", "campo em branco"));
        }
        else if (!validProductTypes.contains(body.productType())) {
            invalidFields.add(new InvalidField("tipo de produto", "tipo inexistente"));
        }

        if (!invalidFields.isEmpty()) {
            throw new InvalidDataException(invalidFields);
        }
    }

    public ProductModel convertDTOToEntity(RegisterProductRequestDTO body) {
        ProductModel product;

        if (body.id() != null) {
            product = new ProductModel(
                    body.id(),
                    body.name(),
                    ProductType.valueOf(body.productType())
            );
        }
        else {
            product = new ProductModel(
                    generateNextId(ProductType.valueOf(body.productType())),
                    body.name(),
                    ProductType.valueOf(body.productType())
            );
        }

        if (!body.productMaterials().isEmpty()) {
            product.getProductMaterials().addAll(
                    materialService.convertMaterialDTOsToEntities(body.productMaterials(), product)
            );
        }
        return product;
    }

    public Optional<ProductModel> findProductByIdentifier(String identifier) {
        return productRepository.findByIdentifier(identifier);
    }

    private String generateNextId(ProductType productType) {
        Optional<String> lastId = productRepository.findLastIdForInitialIdNumbers(productType.getIdPrefix());
        int lastSequenceNumber = 0;

        if (lastId.isPresent()) {
            String idWithoutPrefix = lastId.get().substring(String.valueOf(productType.getIdPrefix()).length());
            lastSequenceNumber = Integer.parseInt(idWithoutPrefix);
        }

        int nextSequenceNumber = lastSequenceNumber + 1;

        return String.format("%d%04d", productType.getIdPrefix(), nextSequenceNumber);
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }
}
