package br.com.ufape.petshare.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.ufape.petshare.model.enums.Profile;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	private String image;

	@Column(unique = true)
	private String email;

	private String phone;

	private String status;

	private LocalDate bornDate;

	@Column(unique = true)
	private String cpf;
	private String password;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@ElementCollection(targetClass = Profile.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Set<Profile> profiles;
	
	public User(Long id, String name, String image, String email, String phone, String status, LocalDate bornDate,
			String cpf, String password, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.bornDate = bornDate;
		this.cpf = cpf;
		this.password = password;
		this.address = address;
		this.profiles = new HashSet<Profile>();
		addProfile(Profile.USER);
	}
	
	public void addProfile(Profile profile) {
		if (this.profiles == null) {
			profiles = new HashSet<>();
		}
		profiles.add(profile);
	}



	
}
