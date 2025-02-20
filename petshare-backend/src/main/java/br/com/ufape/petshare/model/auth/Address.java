package br.com.ufape.petshare.model.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O número não pode estar vazio")
    private String number;

    @NotBlank(message = "A rua não pode estar vazia")
    private String street;

    @NotBlank(message = "O bairro não pode estar vazio")
    private String neighborhood;

    @NotBlank(message = "O CEP não pode estar vazio")
    @Size(min = 8, max = 9, message = "O CEP deve ter entre 8 e 9 caracteres")
    private String cep;

    @NotBlank(message = "A cidade não pode estar vazia")
    private String city;

    @NotBlank(message = "O estado não pode estar vazio")
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
