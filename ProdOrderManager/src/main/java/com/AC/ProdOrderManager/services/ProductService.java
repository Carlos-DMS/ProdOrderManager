package com.AC.ProdOrderManager.services;

import com.AC.ProdOrderManager.dtos.product.RegisterProductRequestDTO;
import com.AC.ProdOrderManager.models.product.ProductModel;
import com.AC.ProdOrderManager.models.product.ProductType;
import com.AC.ProdOrderManager.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MaterialService materialService;

    public void registerProduct(RegisterProductRequestDTO body) throws Exception{
        ProductModel product;

        //adicionar posteriormente um validate

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
        productRepository.save(product);
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
