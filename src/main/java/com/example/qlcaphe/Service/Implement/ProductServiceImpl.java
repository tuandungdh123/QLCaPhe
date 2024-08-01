package com.example.qlcaphe.Service.Implement;

import com.example.qlcaphe.DTO.ProductDTO;
import com.example.qlcaphe.DTO.ProductSizeDTO;
import com.example.qlcaphe.Entity.ProductE;
import com.example.qlcaphe.Entity.ProductTypeE;
import com.example.qlcaphe.Entity.Product_SizeE;
import com.example.qlcaphe.Entity.SizeE;
import com.example.qlcaphe.Repository.ProductRepo;
import com.example.qlcaphe.Repository.ProductSizeRepo;
import com.example.qlcaphe.Repository.ProductTypeRepo;
import com.example.qlcaphe.Repository.SizesRepo;
import com.example.qlcaphe.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    final ProductRepo repo;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private SizesRepo sizeRepository;

    @Autowired
    private ProductSizeRepo productSizeRepository;
    @Autowired
    private ProductTypeRepo productTypeRepo;

    @Override
    @Transactional
    public void saveProduct(ProductDTO productDTO) {
        // 1. Lưu thông tin sản phẩm
        ProductE product = new ProductE();
        product.setNameProduct(productDTO.getNameProduct());
        product.setDescription(productDTO.getDescription());
        product.setProductImage(productDTO.getPriductImage());

        // Kiểm tra xem loại sản phẩm có tồn tại không
        ProductTypeE productType = productTypeRepo.findById(productDTO.getProductTypeId())
                .orElseThrow(() -> new RuntimeException("ProductType not found"));
        product.setProductTypeE(productType);

        ProductE savedProduct = productRepository.save(product);

        // 2. Lưu thông tin kích thước và giá
        for (ProductSizeDTO productSizeDTO : productDTO.getProductSizes()) {
            SizeE size = sizeRepository.findById(productSizeDTO.getSizeId())
                    .orElseThrow(() -> new RuntimeException("Size not found"));

            Product_SizeE productSize = new Product_SizeE();
            productSize.setProductE(savedProduct);
            productSize.setSizeE(size);
            productSize.setPrice(productSizeDTO.getPrice());

            productSizeRepository.save(productSize);
        }
    }


    @Override
    public List<ProductE> getAllProductE(){
        return repo.findAll();
    };

//
//    @Override
//    public ProductE saveProduct(ProductE product) {
//        if (product.getNameProduct() == null || product.getNameProduct().isEmpty()) {
//            throw new IllegalArgumentException("Product name cannot be null or empty");
//        }
//        return repo.save(product);
//    }
}
