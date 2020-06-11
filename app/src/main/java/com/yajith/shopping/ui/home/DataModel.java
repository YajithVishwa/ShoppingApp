package com.yajith.shopping.ui.home;

public class DataModel {
    String customerid, productid;

    public DataModel(String customerid, String productid) {
        this.customerid = customerid;
        this.productid = productid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }
}
