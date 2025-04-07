package br.com.ufape.petshare.controller.dto.response;

import java.util.List;

import br.com.ufape.petshare.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	private Long id;
	private String text;
    private String type;
    private List<String> images;

	public PostResponse(Post obj) {
		this.id = obj.getId();
		this.text = obj.getText();
		this.type = obj.getType();
		this.images = obj.getImages();
	}

}
