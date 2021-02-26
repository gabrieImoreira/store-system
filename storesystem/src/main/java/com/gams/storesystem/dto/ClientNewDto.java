package com.gams.storesystem.dto;

import com.gams.storesystem.services.validation.ClientInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientInsert
public class ClientNewDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="E-mail inválido")
    private String email;

    @NotEmpty(message="Preenchimento obrigatório")
    private String cpfOrCnpj;
    private Integer type;

    @NotEmpty(message="Preenchimento obrigatório")
    private String publicArea; //logradouro

    @NotEmpty(message="Preenchimento obrigatório")
    private String number;

    private String complement;
    private String district; //bairro

    @NotEmpty(message="Preenchimento obrigatório")
    private String zipCode; //cep

    @NotEmpty(message="Preenchimento obrigatório")
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
