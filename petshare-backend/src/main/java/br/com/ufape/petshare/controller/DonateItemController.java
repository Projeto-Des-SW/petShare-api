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

import br.com.ufape.petshare.controller.dto.request.newdto.NewDonateItemRequest;
import br.com.ufape.petshare.controller.dto.request.updatedto.DonateItemUpdateRequest;
import br.com.ufape.petshare.controller.dto.response.DonateItemResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.DonateItem;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/donateitems")
public class DonateItemController {
	@Autowired
	private PetShare facade;

	@GetMapping
	public ResponseEntity<List<DonateItemResponse>> getAllDonateItems() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.getAllDonateItems().stream().map(DonateItemResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<DonateItemResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<DonateItem> list = facade.findDonateItemPage(pageRequest);
		Page<DonateItemResponse> listDto = list.map(obj -> new DonateItemResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> createDonateItem(@Valid @RequestBody NewDonateItemRequest obj) {
		DonateItem createdObj = facade.saveDonateItem(obj.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<DonateItemResponse> getDonateItemById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new DonateItemResponse(facade.findDonateItemById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<DonateItemResponse> updateDonateItem(@PathVariable("id") Long id,
			@Valid @RequestBody DonateItemUpdateRequest updatedObj) {
		System.out.println(id);
		DonateItem obj = facade.updateDonateItem(id, updatedObj.toEntity());
		return ResponseEntity.ok(new DonateItemResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDonateItem(@PathVariable("id") Long id) {
		facade.deleteDonateItem(id);
		return ResponseEntity.noContent().build();
	}

}
