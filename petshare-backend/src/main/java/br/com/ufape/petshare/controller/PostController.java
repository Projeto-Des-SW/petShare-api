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

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.controller.dto.response.PostResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.Post;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PetShare facade;

	@GetMapping
	public ResponseEntity<List<PostResponse>> getAllPosts() {
		return ResponseEntity.status(HttpStatus.OK).body(facade.getAllPosts().stream().map(PostResponse::new).toList());
	}

	@GetMapping("/page")
	public ResponseEntity<Page<PostResponse>> findPage(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "24") Integer linesPerPage,
			@RequestParam(defaultValue = "nome") String orderBy, @RequestParam(defaultValue = "ASC") String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Post> list = facade.findPostPage(pageRequest);
		Page<PostResponse> listDto = list.map(obj -> new PostResponse(obj));
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<Void> createPost(@Valid @RequestBody PostRequest obj) {
		Post createdObj = facade.savePost(obj.toEntity());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new PostResponse(facade.findPostById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PostResponse> updatePost(@PathVariable("id") Long id,
			@Valid @RequestBody PostRequest updatedObj) {
		System.out.println(id);
		Post obj = facade.updatePost(id, updatedObj.toEntity());
		return ResponseEntity.ok(new PostResponse(obj));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
		facade.deletePost(id);
		return ResponseEntity.noContent().build();
	}

}
