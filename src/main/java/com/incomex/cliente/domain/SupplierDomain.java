package com.incomex.cliente.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierDomain {
    private int id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String region;
    private String postalCode;
}
