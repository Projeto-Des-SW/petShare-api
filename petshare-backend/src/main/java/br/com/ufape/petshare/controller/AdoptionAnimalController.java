package br.com.ufape.petshare.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ufape.petshare.controller.dto.request.newdto.NewAdoptionAnimalRequest;
import br.com.ufape.petshare.controller.dto.response.AdoptionAnimalResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.AdoptionAnimal;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/adoptionanimals")
public class AdoptionAnimalController {
	@Autowired
	private PetShare facade;

	@GetMapping
	public ResponseEntity<List<AdoptionAnimalResponse>> getAllAdoptionAnimals() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.getAllAdoptionAnimals().stream().map(AdoptionAnimalResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<AdoptionAnimalResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<AdoptionAnimal> list = facade.findAdoptionAnimalPage(pageRequest);
		Page<AdoptionAnimalResponse> listDto = list.map(obj -> new AdoptionAnimalResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> createAdoptionAnimal(@Valid @RequestBody NewAdoptionAnimalRequest obj) {
		AdoptionAnimal createdObj = facade.saveAdoptionAnimal(obj.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/cancel/{id}")
	public ResponseEntity<Void> cancelAdoptionAnimal(@PathVariable("id") Long id) {
		facade.cancelAdoptionAnimal(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/refuse/{id}")
	public ResponseEntity<Void> refuseAdoptionAnimal(@PathVariable("id") Long id) {
		facade.refuseAdoptionAnimal(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/confirm-interest/{id}")
	public ResponseEntity<Void> confirmInterestAdoptionAnimal(@PathVariable("id") Long id) {
		facade.confirmInterestAdoptionAnimal(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/confirm-adoption/{id}")
	public ResponseEntity<Void> confirmAdoptionAnimal(@PathVariable("id") Long id) {
		facade.confirmAdoptionAnimal(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/confirm-receipt/{id}")
	public ResponseEntity<Void> confirmReceiptAdoptionAnimal(@PathVariable("id") Long id) {
		facade.confirmReceiptAdoptionAnimal(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AdoptionAnimalResponse> getAdoptionAnimalById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new AdoptionAnimalResponse(facade.findAdoptionAnimalById(id)));
	}
	
	@GetMapping("/donor/{donorId}")
	public ResponseEntity<List<AdoptionAnimalResponse>> getAdoptionAnimalByDonorId(@PathVariable("donorId") Long donorId) {
		return ResponseEntity.status(HttpStatus.OK)
		.body(facade.findAdoptionAnimalsByDonorId(donorId).stream().map(AdoptionAnimalResponse::new).toList());
	}
	
	@GetMapping("adopter/{adopterId}")
	public ResponseEntity<List<AdoptionAnimalResponse>> getAdoptionAnimalByAdopterId(@PathVariable("adopterId") Long adopterId) {
		return ResponseEntity.status(HttpStatus.OK)
		.body(facade.findAdoptionAnimalsByAdopterId(adopterId).stream().map(AdoptionAnimalResponse::new).toList());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAdoptionAnimal(@PathVariable("id") Long id) {
		facade.deleteAdoptionAnimal(id);
		return ResponseEntity.noContent().build();
	}

}
