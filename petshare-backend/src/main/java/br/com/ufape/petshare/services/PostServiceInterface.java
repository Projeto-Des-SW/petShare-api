package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.Post;

public interface PostServiceInterface {
	Post savePost(Post post);

	Post findPostById(Long id);

	List<Post> getAllPosts();

	Post updatePost(Long id, Post postDetails);

	void deletePost(Long id);

	Page<Post> findPostPage(PageRequest pageRequest);
}
