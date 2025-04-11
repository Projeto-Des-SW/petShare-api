package br.com.ufape.petshare.model;

import java.time.LocalDate;

import br.com.ufape.petshare.model.enums.ReceivedItemStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ReceivedItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate date;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReceivedItemStatus status;
	
	
	private Double quantity;

	@ManyToOne
	@JoinColumn(name = "request_id")
	private Request request;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "donate_item_id")
	private DonateItem donateItem;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User receiver;
}