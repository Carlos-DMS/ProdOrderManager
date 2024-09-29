package com.AC.ProdOrderManager.services;

import com.AC.ProdOrderManager.dtos.material.RegisterBaseMaterialRequestDTO;
import com.AC.ProdOrderManager.dtos.material.RegisterMaterialDetailRequestDTO;
import com.AC.ProdOrderManager.models.product.ProductModel;
import com.AC.ProdOrderManager.models.product.productMaterial.MaterialDetailModel;
import com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial.BaseMaterialModel;
import com.AC.ProdOrderManager.models.product.productMaterial.baseMaterial.UnitType;
import com.AC.ProdOrderManager.repositories.BaseMaterialRepository;
import com.AC.ProdOrderManager.repositories.MaterialDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MaterialService {
    @Autowired
    private BaseMaterialRepository baseMaterialRepository;
    @Autowired
    private MaterialDetailRepository materialDetailRepository;

    public void registerBaseMaterial(RegisterBaseMaterialRequestDTO body) {
        baseMaterialRepository.save(new BaseMaterialModel(body.name(), UnitType.valueOf(body.productType())));
        //adicionar posteriormente um validate
    }

    public List<MaterialDetailModel> convertMaterialDTOsToEntities(Set<RegisterMaterialDetailRequestDTO> materialsDTO, ProductModel product) throws Exception {
        List<MaterialDetailModel> materials = new ArrayList<>();
        BaseMaterialModel baseMaterial;

        for (RegisterMaterialDetailRequestDTO material : materialsDTO) {
            Optional<BaseMaterialModel> optBaseMaterial = baseMaterialRepository.findById(material.baseMaterialName());

            if (optBaseMaterial.isPresent()) {
                baseMaterial = optBaseMaterial.get();
            }
            else {
                //trocar por exceção personalizada
                throw new Exception("deu merda");
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
