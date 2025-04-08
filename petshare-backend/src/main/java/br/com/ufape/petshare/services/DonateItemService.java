package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.DonateItem;
import br.com.ufape.petshare.repository.DonateItemRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class DonateItemService implements DonateItemServiceInterface {

	@Autowired
	private DonateItemRepository donateItemRepository;

	@Override
	@Transactional
	public DonateItem saveDonateItem(DonateItem donateItem) {
		return donateItemRepository.save(donateItem);
	}

	@Override
	public DonateItem findDonateItemById(Long id) {
		return donateItemRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Donate Item record not found with ID = " + id));
	}

	@Override
	public List<DonateItem> getAllDonateItems() {
		return donateItemRepository.findAll();
	}
	
	@Override
	public List<DonateItem> getAvailableDonations() {
	    return donateItemRepository.findByStatus("Disponível");
	}

	@Override
	public Page<DonateItem> findDonateItemPage(PageRequest pageRequest) {
		return donateItemRepository.findAll(pageRequest);
	}

	@Override
	@Transactional
	public DonateItem updateDonateItem(Long id, DonateItem donateItemDetails) {
		if (!donateItemRepository.existsById(id)) {
			throw new ObjectNotFoundException("Donate Item record not found with ID = " + id);
		}
		donateItemDetails.setId(id);
		return donateItemRepository.save(donateItemDetails);
	}

	@Override
	@Transactional
	public void deleteDonateItem(Long id) {
		if (!donateItemRepository.existsById(id)) {
			throw new ObjectNotFoundException("Donate Item record not found with ID = " + id);
		}
		donateItemRepository.deleteById(id);
	}

	@Override
	public List<DonateItem> findDonateItemsByDonorId(Long donorId) {
		return donateItemRepository.findByDonorId(donorId);
	}
}
