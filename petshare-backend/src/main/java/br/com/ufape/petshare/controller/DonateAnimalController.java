package br.com.ufape.petshare.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ufape.petshare.controller.dto.request.newdto.NewDonateAnimalRequest;
import br.com.ufape.petshare.controller.dto.request.updatedto.DonateAnimalUpdateRequest;
import br.com.ufape.petshare.controller.dto.response.DonateAnimalResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.DonateAnimal;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/donateanimals")
public class DonateAnimalController {
	@Autowired
	private PetShare facade;

	private static String imagePrefix = "DONATEANIMAL";

	@GetMapping
	public ResponseEntity<List<DonateAnimalResponse>> getAllDonateAnimals() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.getAllDonateAnimals().stream().map(DonateAnimalResponse::new).toList());
	}

	@GetMapping("/available")
	public ResponseEntity<List<DonateAnimalResponse>> getAvailableDonations() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.getAvailableAnimalDonations().stream().map(DonateAnimalResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<DonateAnimalResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<DonateAnimal> list = facade.findDonateAnimalPage(pageRequest);
		Page<DonateAnimalResponse> listDto = list.map(obj -> new DonateAnimalResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> createDonateAnimal(@Valid @RequestPart NewDonateAnimalRequest obj,
			@RequestPart("images") List<MultipartFile> images) throws IOException {
		List<String> filenames = new ArrayList<>();
		if (images != null) {
			images.forEach(x -> filenames.add(facade.formatFileName(imagePrefix, x.getOriginalFilename())));
		}
		DonateAnimal createdObj = obj.toEntity();
		createdObj.getPost().setImages(filenames);
		createdObj = facade.saveDonateAnimal(createdObj);
		if (images != null) {
			for (int i = 0; i < images.size(); i++) {
				facade.uploadFile(images.get(i), filenames.get(i));
			}
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DonateAnimalResponse> getDonateAnimalById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new DonateAnimalResponse(facade.findDonateAnimalById(id)));
	}

	@GetMapping("/donor/{donorId}")
	public ResponseEntity<List<DonateAnimalResponse>> getDonateAnimalsByDonorId(@PathVariable("donorId") Long donorId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.findDonateAnimalsByDonorId(donorId).stream().map(DonateAnimalResponse::new).toList());
	}

	@PutMapping("/{id}")
	public ResponseEntity<DonateAnimalResponse> updateDonateAnimal(@PathVariable("id") Long id,
			@Valid @RequestBody DonateAnimalUpdateRequest updatedObj) {
		System.out.println(id);
		DonateAnimal obj = facade.updateDonateAnimal(id, updatedObj.toEntity());
		return ResponseEntity.ok(new DonateAnimalResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDonateAnimal(@PathVariable("id") Long id) {
		facade.deleteDonateAnimal(id);
		return ResponseEntity.noContent().build();
	}

}
