package com.example.qlcaphe.DTO;

import com.example.qlcaphe.DTO.ProductSizeDTO;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {

        private String nameProduct;
        private String description;
        private String priductImage;
        private Integer productTypeId; // ID của loại sản phẩm
        private List<ProductSizeDTO> productSizes; // Danh sách kích thước và giá

        // Getters and Setters

        public String getNameProduct() {
                return nameProduct;
        }

        public void setNameProduct(String nameProduct) {
                this.nameProduct = nameProduct;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getPriductImage() {
                return priductImage;
        }

        public void setPriductImage(String priductImage) {
                this.priductImage = priductImage;
        }

        public Integer getProductTypeId() {
                return productTypeId;
        }

        public void setProductTypeId(Integer productTypeId) {
                this.productTypeId = productTypeId;
        }

        public List<ProductSizeDTO> getProductSizes() {
                return productSizes;
        }

        public void setProductSizes(List<ProductSizeDTO> productSizes) {
                this.productSizes = productSizes;
        }
}
