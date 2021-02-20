package com.gams.storesystem.dto;

import java.io.Serializable;

public class ClientNewDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer type;

    private String publicArea; //logradouro
    private String number;
    private String complement;
    private String district; //bairro
    private String zipCode; //cep

    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    private ClientNewDto(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(String publicArea) {
        this.publicArea = publicArea;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String telephone1) {
        this.phone1 = telephone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String telephone2) {
        this.phone2 = telephone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String telephone3) {
        this.phone3 = telephone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
