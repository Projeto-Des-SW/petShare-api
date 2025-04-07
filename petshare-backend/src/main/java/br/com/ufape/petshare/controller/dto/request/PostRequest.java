package br.com.ufape.petshare.controller.dto.request;

import java.util.List;

import br.com.ufape.petshare.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

	private String text;
	private String type;
	private List<String> images;

	public Post toEntity() {
		return new Post(null, text, type, images);
	}
}
