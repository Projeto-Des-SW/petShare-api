package br.com.ufape.petshare.model.users;

import java.time.LocalDate;

import br.com.ufape.petshare.model.auth.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "O e-mail não pode estar vazio")
    @Email(message = "Formato de e-mail inválido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "O telefone não pode estar vazio")
    @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    private String phone;

    @NotBlank(message = "O status não pode estar vazio")
    private String status;

    @NotNull(message = "A data de nascimento não pode estar vazia")
    private LocalDate bornDate;

    @NotBlank(message = "O CPF não pode estar vazio")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "A senha não pode estar vazia")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;


    public User() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
