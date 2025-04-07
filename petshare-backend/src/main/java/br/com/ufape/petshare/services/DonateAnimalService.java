package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.DonateAnimal;
import br.com.ufape.petshare.repository.DonateAnimalRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class DonateAnimalService implements DonateAnimalServiceInterface {

	@Autowired
	private DonateAnimalRepository donateAnimalRepository;

	@Override
	@Transactional
	public DonateAnimal saveDonateAnimal(DonateAnimal donateAnimal) {
		return donateAnimalRepository.save(donateAnimal);
	}

	@Override
	public DonateAnimal findDonateAnimalById(Long id) {
		return donateAnimalRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Donate Animal record not found with ID = " + id));
	}

	@Override
	public Page<DonateAnimal> findDonateAnimalPage(PageRequest pageRequest) {
		return donateAnimalRepository.findAll(pageRequest);
	}

	@Override
	public List<DonateAnimal> getAllDonateAnimals() {
		return donateAnimalRepository.findAll();
	}

	@Override
	@Transactional
	public DonateAnimal updateDonateAnimal(Long id, DonateAnimal donateAnimalDetails) {
		if (!donateAnimalRepository.existsById(id)) {
			throw new ObjectNotFoundException("Donate Animal record not found with ID = " + id);
		}
		donateAnimalDetails.setId(id);
		return donateAnimalRepository.save(donateAnimalDetails);
	}

	@Override
	@Transactional
	public void deleteDonateAnimal(Long id) {
		if (!donateAnimalRepository.existsById(id)) {
			throw new ObjectNotFoundException("Donate Animal record not found with ID = " + id);
		}
		donateAnimalRepository.deleteById(id);
	}

	public List<DonateAnimal> getAvailableDonations() {
	    return donateAnimalRepository.findByStatus("Em aberto");
	}
}
