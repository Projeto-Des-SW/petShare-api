package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.AdoptionAnimal;
import br.com.ufape.petshare.repository.AdoptionAnimalRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class AdoptionAnimalService implements AdoptionAnimalServiceInterface {

	@Autowired
	private AdoptionAnimalRepository adoptionAnimalRepository;

	@Override
	@Transactional
	public AdoptionAnimal saveAdoptionAnimal(AdoptionAnimal adoptionAnimal) {
		return adoptionAnimalRepository.save(adoptionAnimal);
	}

	@Override
	public AdoptionAnimal findAdoptionAnimalById(Long id) {
		return adoptionAnimalRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Adoption Animal record not found with ID = " + id));
	}

	@Override
	public Page<AdoptionAnimal> findAdoptionAnimalPage(PageRequest pageRequest) {
		return adoptionAnimalRepository.findAll(pageRequest);
	}

	@Override
	public List<AdoptionAnimal> getAllAdoptionAnimals() {
		return adoptionAnimalRepository.findAll();
	}

	@Override
	@Transactional
	public AdoptionAnimal updateAdoptionAnimal(Long id, AdoptionAnimal adoptionAnimalDetails) {
		if (!adoptionAnimalRepository.existsById(id)) {
			throw new ObjectNotFoundException("Adoption Animal record not found with ID = " + id);
		}
		adoptionAnimalDetails.setId(id);
		return adoptionAnimalRepository.save(adoptionAnimalDetails);
	}

	@Override
	@Transactional
	public void deleteAdoptionAnimal(Long id) {
		if (!adoptionAnimalRepository.existsById(id)) {
			throw new ObjectNotFoundException("Adoption Animal record not found with ID = " + id);
		}
		adoptionAnimalRepository.deleteById(id);
	}
}
