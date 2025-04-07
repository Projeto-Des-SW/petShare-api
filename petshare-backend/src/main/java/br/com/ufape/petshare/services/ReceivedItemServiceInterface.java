package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.ReceivedItem;

public interface ReceivedItemServiceInterface {

	ReceivedItem saveReceivedItem(ReceivedItem ReceivedItem);

	ReceivedItem findReceivedItemById(Long id);

	List<ReceivedItem> getAllReceivedItems();

	ReceivedItem updateReceivedItem(Long id, ReceivedItem ReceivedItemDetails);

	void deleteReceivedItem(Long id);

	Page<ReceivedItem> findReceivedItemPage(PageRequest pageRequest);

}