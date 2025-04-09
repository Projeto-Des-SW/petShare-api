package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.AdoptionAnimal;

public interface AdoptionAnimalServiceInterface {

	AdoptionAnimal saveAdoptionAnimal(AdoptionAnimal adoptionAnimal);

	AdoptionAnimal findAdoptionAnimalById(Long id);

	List<AdoptionAnimal> getAllAdoptionAnimals();
	
	List<AdoptionAnimal> findAdoptionAnimalsByAdopterId(Long adopterId);
	
	List<AdoptionAnimal> findAdoptionAnimalsByDonorId(Long donateId);
	
	List<AdoptionAnimal> findAdoptionAnimalsByDonateId(Long donorId);

	AdoptionAnimal updateAdoptionAnimal(Long id, AdoptionAnimal adoptionAnimalDetails);

	void deleteAdoptionAnimal(Long id);

	Page<AdoptionAnimal> findAdoptionAnimalPage(PageRequest pageRequest);

}