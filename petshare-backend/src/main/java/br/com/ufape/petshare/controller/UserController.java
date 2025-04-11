package br.com.ufape.petshare.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import br.com.ufape.petshare.controller.dto.request.newdto.NewUserRequest;
import br.com.ufape.petshare.controller.dto.request.updatedto.UserUpdateRequest;
import br.com.ufape.petshare.controller.dto.response.UserResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.User;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private PetShare facade;

	private static String imageUserPrefix = "USER";

	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllUsers().stream().map(UserResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<UserResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<User> list = facade.findUserPage(pageRequest);
		Page<UserResponse> listDto = list.map(obj -> new UserResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<UserResponse> createUser(@Valid @RequestPart NewUserRequest obj, MultipartFile image) throws IOException {
		String filename = null;
		if(image!=null) {
			filename = formatFileName(imageUserPrefix, image.getOriginalFilename());
		}
		User createdObj = obj.toEntity();
		createdObj.setImage(filename);
		createdObj = facade.saveUser(createdObj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId()).toUri();
		if(image!=null)
			facade.uploadFile(image, filename);
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(facade.findUserById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id,
			@Valid @RequestBody UserUpdateRequest updatedObj) {
		User obj = facade.updateUser(id, updatedObj.toEntity());
		return ResponseEntity.ok(new UserResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		facade.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	private String formatFileName(String prefix, String originalFilename) {
		String extension = "";
		if (originalFilename != null && originalFilename.contains(".")) {
	        extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	    }
		return prefix + "-" + UUID.randomUUID() + System.currentTimeMillis() + extension.toLowerCase();
	}
}
