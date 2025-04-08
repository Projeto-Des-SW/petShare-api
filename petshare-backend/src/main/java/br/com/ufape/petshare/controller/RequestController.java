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

import br.com.ufape.petshare.controller.dto.request.newdto.NewRequestRequest;
import br.com.ufape.petshare.controller.dto.request.updatedto.RequestUpdateRequest;
import br.com.ufape.petshare.controller.dto.response.RequestResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.Request;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/requests")
public class RequestController {
	@Autowired
	private PetShare facade;

	@GetMapping
	public ResponseEntity<List<RequestResponse>> getAllRequests() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.getAllRequests().stream().map(RequestResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<RequestResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Request> list = facade.findRequestPage(pageRequest);
		Page<RequestResponse> listDto = list.map(obj -> new RequestResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> createRequest(@Valid @RequestBody NewRequestRequest obj) {
		Request createdObj = facade.saveRequest(obj.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<RequestResponse> getRequestById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new RequestResponse(facade.findRequestById(id)));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<RequestResponse>> getRequestByUserId(@PathVariable("userId") Long userId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.findRequestByUserId(userId).stream().map(RequestResponse::new).toList());
	}

	@PutMapping("/{id}")
	public ResponseEntity<RequestResponse> updateRequest(@PathVariable("id") Long id,
			@Valid @RequestBody RequestUpdateRequest updatedObj) {
		System.out.println(id);
		Request obj = facade.updateRequest(id, updatedObj.toEntity());
		return ResponseEntity.ok(new RequestResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRequest(@PathVariable("id") Long id) {
		facade.deleteRequest(id);
		return ResponseEntity.noContent().build();
	}

}
