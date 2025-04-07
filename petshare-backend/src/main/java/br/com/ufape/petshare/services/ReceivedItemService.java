package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.ReceivedItem;
import br.com.ufape.petshare.repository.ReceivedItemRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class ReceivedItemService implements ReceivedItemServiceInterface {

	@Autowired
	private ReceivedItemRepository receivedItemRepository;

	@Override
	@Transactional
	public ReceivedItem saveReceivedItem(ReceivedItem receivedItem) {
		return receivedItemRepository.save(receivedItem);
	}

	@Override
	public ReceivedItem findReceivedItemById(Long id) {
		return receivedItemRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Received Item record not found with ID = " + id));
	}

	@Override
	public List<ReceivedItem> getAllReceivedItems() {
		return receivedItemRepository.findAll();
	}

	@Override
	public Page<ReceivedItem> findReceivedItemPage(PageRequest pageRequest) {
		return receivedItemRepository.findAll(pageRequest);
	}

	@Override
	@Transactional
	public ReceivedItem updateReceivedItem(Long id, ReceivedItem receivedItemDetails) {
		if (!receivedItemRepository.existsById(id)) {
			throw new ObjectNotFoundException("Received Item record not found with ID = " + id);
		}
		receivedItemDetails.setId(id);
		return receivedItemRepository.save(receivedItemDetails);
	}

	@Override
	@Transactional
	public void deleteReceivedItem(Long id) {
		if (!receivedItemRepository.existsById(id)) {
			throw new ObjectNotFoundException("Received Item record not found with ID = " + id);
		}
		receivedItemRepository.deleteById(id);
	}
}
