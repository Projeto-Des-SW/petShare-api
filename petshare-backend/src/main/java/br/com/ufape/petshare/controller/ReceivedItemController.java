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

import br.com.ufape.petshare.controller.dto.request.newdto.NewReceivedItemRequest;
import br.com.ufape.petshare.controller.dto.request.updatedto.ReceivedItemUpdateRequest;
import br.com.ufape.petshare.controller.dto.response.ReceivedItemResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.ReceivedItem;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/receiveditems")
public class ReceivedItemController {
	@Autowired
	private PetShare facade;

	@GetMapping
	public ResponseEntity<List<ReceivedItemResponse>> getAllReceivedItems() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.getAllReceivedItems().stream().map(ReceivedItemResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ReceivedItemResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ReceivedItem> list = facade.findReceivedItemPage(pageRequest);
		Page<ReceivedItemResponse> listDto = list.map(obj -> new ReceivedItemResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> createReceivedItem(@Valid @RequestBody NewReceivedItemRequest obj) {
		ReceivedItem createdObj = facade.saveReceivedItem(obj.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/cancel/{id}")
	public ResponseEntity<Void> cancelReceivedItem(@PathVariable("id") Long id) {
		facade.cancelReceivedItem(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/refuse/{id}")
	public ResponseEntity<Void> refuseReceivedItem(@PathVariable("id") Long id) {
		facade.refuseReceivedItem(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/confirm-interest/{id}")
	public ResponseEntity<Void> confirmInterestReceivedItem(@PathVariable("id") Long id) {
		facade.confirmInterestReceivedItem(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/confirm-adoption/{id}")
	public ResponseEntity<Void> confirmReceivedItem(@PathVariable("id") Long id) {
		facade.confirmReceivedItem(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/confirm-receipt/{id}")
	public ResponseEntity<Void> confirmReceiptReceivedItem(@PathVariable("id") Long id) {
		facade.confirmReceiptReceivedItem(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReceivedItemResponse> getReceivedItemById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new ReceivedItemResponse(facade.findReceivedItemById(id)));
	}

	@GetMapping("/donate/{donateId}")
	public ResponseEntity<List<ReceivedItemResponse>> getReceivedItemsByDonateId(
			@PathVariable("donateId") Long donateId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.findReceivedItemsByDonateId(donateId).stream().map(ReceivedItemResponse::new).toList());
	}

	@GetMapping("/request/{requestId}")
	public ResponseEntity<List<ReceivedItemResponse>> getReceivedItemsByRequestId(
			@PathVariable("requestId") Long requestId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.findReceivedItemsByRequestId(requestId).stream().map(ReceivedItemResponse::new).toList());
	}

	@GetMapping("donate/donor/{donorId}")
	public ResponseEntity<List<ReceivedItemResponse>> getReceivedItemsByDonateDonorId(
			@PathVariable("donorId") Long donorId) {
		return ResponseEntity.status(HttpStatus.OK).body(
				facade.findReceivedItemsByDonateDonorId(donorId).stream().map(ReceivedItemResponse::new).toList());
	}

	@GetMapping("/request/user/{userId}")
	public ResponseEntity<List<ReceivedItemResponse>> getReceivedItemsByDonorId(@PathVariable("userId") Long userId) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(facade.findReceivedItemsByRequestUserId(userId).stream().map(ReceivedItemResponse::new).toList());
	}

	@GetMapping("receiver/{receiverId}")
	public ResponseEntity<List<ReceivedItemResponse>> getReceivedItemsByReceiverId(
			@PathVariable("receiverId") Long receiverId) {
		return ResponseEntity.status(HttpStatus.OK).body(
				facade.findReceivedItemsByReceiverId(receiverId).stream().map(ReceivedItemResponse::new).toList());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReceivedItemResponse> updateReceivedItem(@PathVariable("id") Long id,
			@Valid @RequestBody ReceivedItemUpdateRequest updatedObj) {
		System.out.println(id);
		ReceivedItem obj = facade.updateReceivedItem(id, updatedObj.toEntity());
		return ResponseEntity.ok(new ReceivedItemResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReceivedItem(@PathVariable("id") Long id) {
		facade.deleteReceivedItem(id);
		return ResponseEntity.noContent().build();
	}

}
