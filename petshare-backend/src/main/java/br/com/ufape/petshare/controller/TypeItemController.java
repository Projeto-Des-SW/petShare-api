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

import br.com.ufape.petshare.controller.dto.request.TypeItemRequest;
import br.com.ufape.petshare.controller.dto.response.TypeItemResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.TypeItem;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/TypeItems")
@Hidden
public class TypeItemController {
	@Autowired
	private PetShare facade;

	@GetMapping
	public ResponseEntity<List<TypeItemResponse>> getAllTypeItems() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.getAllTypeItems().stream().map(TypeItemResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<TypeItemResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<TypeItem> list = facade.findTypeItemPage(pageRequest);
		Page<TypeItemResponse> listDto = list.map(obj -> new TypeItemResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> createTypeItem(@Valid @RequestBody TypeItemRequest obj) {
		TypeItem createdObj = facade.saveTypeItem(obj.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TypeItemResponse> getTypeItemById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new TypeItemResponse(facade.findTypeItemById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TypeItemResponse> updateTypeItem(@PathVariable("id") Long id,
			@Valid @RequestBody TypeItemRequest updatedObj) {
		System.out.println(id);
		TypeItem obj = facade.updateTypeItem(id, updatedObj.toEntity());
		return ResponseEntity.ok(new TypeItemResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTypeItem(@PathVariable("id") Long id) {
		facade.deleteTypeItem(id);
		return ResponseEntity.noContent().build();
	}

}
