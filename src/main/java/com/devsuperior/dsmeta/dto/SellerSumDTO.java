package com.devsuperior.dsmeta.dto;

public class SellerSumDTO {

    private String sellerName;
    private Double total;

    public SellerSumDTO() {}

    public SellerSumDTO(String sellerName, Double total) {
        this.total = total;
        this.sellerName = sellerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
