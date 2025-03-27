package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.Item;

public interface ItemServiceInterface {

	Item saveItem(Item item);

	Item findItemById(Long id);

	List<Item> getAllItems();

	Item updateItem(Long id, Item itemDetails);

	void deleteItem(Long id);

	Page<Item> findItemPage(PageRequest pageRequest);

}