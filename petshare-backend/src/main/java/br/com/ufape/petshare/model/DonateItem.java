package br.com.ufape.petshare.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class DonateItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;
	private String status;
	private Double quantity;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User donor;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private Post post;
	
	@OneToMany(mappedBy = "donateItem", orphanRemoval = true)
	private List<ReceivedItem> receivedItems;

	public DonateItem(Long id, LocalDate date, String status, Double quantity, Item item, User donor, Post post) {
		super();
		this.id = id;
		this.date = date;
		this.status = status;
		this.quantity = quantity;
		this.item = item;
		this.donor = donor;
		this.post = post;
		this.receivedItems = new ArrayList<>();
	}
	
	
}