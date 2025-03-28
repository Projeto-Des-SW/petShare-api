package br.com.ufape.petshare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.petshare.controller.dto.request.newdto.NewAnimalRequest;
import br.com.ufape.petshare.controller.dto.request.updatedto.AnimalUpdateRequest;
import br.com.ufape.petshare.controller.dto.response.AnimalResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.Animal;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animals")
public class AnimalController {
	@Autowired
	private PetShare facade;


	@GetMapping
	public ResponseEntity<List<AnimalResponse>> getAllAnimals() {
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllAnimals().stream().map(AnimalResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<AnimalResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Animal> list = facade.findAnimalPage(pageRequest);
		Page<AnimalResponse> listDto = list.map(obj -> new AnimalResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<AnimalResponse> createAnimal(@Valid @RequestBody NewAnimalRequest obj) {
		Animal createdObj = facade.saveAnimal(obj.toEntity());
		return ResponseEntity.status(HttpStatus.CREATED).body(new AnimalResponse(createdObj));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new AnimalResponse(facade.findAnimalById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable("id") Long id, @Valid @RequestBody AnimalUpdateRequest updatedObj) {
		System.out.println(id);
		Animal obj = facade.updateAnimal(id, updatedObj.toEntity());
		return ResponseEntity.ok(new AnimalResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAnimal(@PathVariable("id") Long id) {
		facade.deleteAnimal(id);
		return ResponseEntity.noContent().build();
	}

}
