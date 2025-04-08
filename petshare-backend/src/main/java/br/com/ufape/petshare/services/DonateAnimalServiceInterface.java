package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.DonateAnimal;

public interface DonateAnimalServiceInterface {

	DonateAnimal saveDonateAnimal(DonateAnimal donateAnimal);

	DonateAnimal findDonateAnimalById(Long id);

	List<DonateAnimal> getAllDonateAnimals();
	
	List<DonateAnimal> getAvailableDonations();
	
	List<DonateAnimal> findDonateAnimalsByDonorId(Long donorId);

	DonateAnimal updateDonateAnimal(Long id, DonateAnimal donateAnimalDetails);

	void deleteDonateAnimal(Long id);

	Page<DonateAnimal> findDonateAnimalPage(PageRequest pageRequest);

}