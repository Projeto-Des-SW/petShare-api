package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.DonateItem;

public interface DonateItemServiceInterface {

	DonateItem saveDonateItem(DonateItem donateItem);

	DonateItem findDonateItemById(Long id);

	List<DonateItem> getAllDonateItems();

	DonateItem updateDonateItem(Long id, DonateItem donateItemDetails);

	void deleteDonateItem(Long id);

	Page<DonateItem> findDonateItemPage(PageRequest pageRequest);

}