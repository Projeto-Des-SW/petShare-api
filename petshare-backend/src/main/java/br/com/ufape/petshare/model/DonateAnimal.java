package br.com.ufape.petshare.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DonateAnimal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private String status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User donor;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private Post post;

	@OneToMany(mappedBy = "donateAnimal", orphanRemoval = true)
	private List<AdoptionAnimal> adoptionAnimals;

	public DonateAnimal(Long id, LocalDate date, String status, Animal animal, User donor, Post post) {
		super();
		this.id = id;
		this.date = date;
		this.status = status;
		this.animal = animal;
		this.donor = donor;
		this.post = post;
		this.adoptionAnimals = new ArrayList<>();
	}

}