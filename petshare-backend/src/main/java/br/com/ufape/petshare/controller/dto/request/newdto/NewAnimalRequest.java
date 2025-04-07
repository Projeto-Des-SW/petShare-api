package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.model.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewAnimalRequest {

	private String name;
	private String race;
	private String status;
	private LocalDate bornDate;
	private String observations;
	private String medicalNotes;
	private String sex;
	private String size;
	private String category;

	public Animal toEntity() {
		return new Animal(null, name, race, status, bornDate, observations, medicalNotes, sex, size, category);
	}
}
