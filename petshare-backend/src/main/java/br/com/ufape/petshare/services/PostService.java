package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.Post;
import br.com.ufape.petshare.repository.PostRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class PostService implements PostServiceInterface {

	@Autowired
	private PostRepository postRepository;

	@Override
	@Transactional
	public Post savePost(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post findPostById(Long id) {
		return postRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Post not found with ID = " + id));
	}

	@Override
	public Page<Post> findPostPage(PageRequest pageRequest) {
		return postRepository.findAll(pageRequest);
	}

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	@Transactional
	public Post updatePost(Long id, Post postDetails) {
		if (!postRepository.existsById(id)) {
			throw new ObjectNotFoundException("Post not found with ID = " + id);
		}
		postDetails.setId(id);
		return postRepository.save(postDetails);
	}

	@Override
	@Transactional
	public void deletePost(Long id) {
		if (!postRepository.existsById(id)) {
			throw new ObjectNotFoundException("Post not found with ID = " + id);
		}
		postRepository.deleteById(id);
	}
}
