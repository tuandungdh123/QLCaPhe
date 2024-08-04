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
import java.util.Optional;

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
    @Autowired
    private ProductRepo productRepo;

    @Override
    @Transactional
    public void saveProduct(ProductDTO productDTO) {
        // 1. Lưu thông tin sản phẩm
        ProductE product = new ProductE();
        product.setNameProduct(productDTO.getNameProduct());
        product.setDescription(productDTO.getDescription());
        product.setPriductImage(productDTO.getPriductImage());

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

    @Override
    public Optional<ProductE> getTourEByTourId(Integer productId) {
        var result = productRepo.getTourEByProductId(productId);
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<Product_SizeE> getPriceByProductIdAndSizeId(Integer productId, Integer sizeId) {
        return productRepository.findPriceByProductIdAndSizeId(productId, sizeId);
    }




}
