package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.Item;
import br.com.ufape.petshare.repository.ItemRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class ItemService implements ItemServiceInterface {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	@Transactional
	public Item saveItem(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public Item findItemById(Long id) {
		return itemRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Item not found with ID = " + id));
	}

	@Override
	public Page<Item> findItemPage(PageRequest pageRequest) {
		return itemRepository.findAll(pageRequest);
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@Override
	@Transactional
	public Item updateItem(Long id, Item itemDetails) {
		if (!itemRepository.existsById(id)) {
			throw new ObjectNotFoundException("Item not found with ID = " + id);
		}
		itemDetails.setId(id);
		return itemRepository.save(itemDetails);
	}

	@Override
	@Transactional
	public void deleteItem(Long id) {
		if (!itemRepository.existsById(id)) {
			throw new ObjectNotFoundException("Item not found with ID = " + id);
		}
		itemRepository.deleteById(id);
	}
}
