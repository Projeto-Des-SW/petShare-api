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

import br.com.ufape.petshare.controller.dto.request.newdto.NewItemRequest;
import br.com.ufape.petshare.controller.dto.request.updatedto.ItemUpdateRequest;
import br.com.ufape.petshare.controller.dto.response.ItemResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.Item;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private PetShare facade;

	@GetMapping
	public ResponseEntity<List<ItemResponse>> getAllItems() {
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllItems().stream().map(ItemResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ItemResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Item> list = facade.findItemPage(pageRequest);
		Page<ItemResponse> listDto = list.map(obj -> new ItemResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> createItem(@Valid @RequestBody NewItemRequest obj) {
		Item createdObj = facade.saveItem(obj.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemResponse> getItemById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new ItemResponse(facade.findItemById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItemResponse> updateItem(@PathVariable("id") Long id,
			@Valid @RequestBody ItemUpdateRequest updatedObj) {
		System.out.println(id);
		Item obj = facade.updateItem(id, updatedObj.toEntity());
		return ResponseEntity.ok(new ItemResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItem(@PathVariable("id") Long id) {
		facade.deleteItem(id);
		return ResponseEntity.noContent().build();
	}

}
