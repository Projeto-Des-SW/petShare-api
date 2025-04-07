package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;

import br.com.ufape.petshare.model.Animal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnimalResponse {
	private Long id;
	private String name;
	private String race;
	private String status;
	private LocalDate bornDate;
	private String observations;
	private String medicalNotes;
	private String sex;
	private String size;
	private String category;

	public AnimalResponse(Animal obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.race = obj.getRace();
		this.status = obj.getStatus();
		this.bornDate = obj.getBornDate();
		this.observations = obj.getObservations();
		this.medicalNotes = obj.getMedicalNotes();
		this.sex = obj.getSex();
		this.size = obj.getSex();
		this.category = obj.getCategory();
	}

}
