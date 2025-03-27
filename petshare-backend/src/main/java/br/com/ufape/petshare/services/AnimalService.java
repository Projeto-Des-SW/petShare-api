package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.Animal;
import br.com.ufape.petshare.repository.AnimalRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class AnimalService implements AnimalServiceInterface {

	@Autowired
	private AnimalRepository animalRepository;

	@Override
	@Transactional
	public Animal saveAnimal(Animal animal) {
		return animalRepository.save(animal);
	}

	@Override
	public Animal findAnimalById(Long id) {
		return animalRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Animal not found with ID = " + id));
	}

	@Override
	public Page<Animal> findAnimalPage(PageRequest pageRequest) {
		return animalRepository.findAll(pageRequest);
	}

	@Override
	public List<Animal> getAllAnimals() {
		return animalRepository.findAll();
	}

	@Override
	@Transactional
	public Animal updateAnimal(Long id, Animal animalDetails) {
		if (!animalRepository.existsById(id)) {
			throw new ObjectNotFoundException("Animal not found with ID = " + id);
		}
		animalDetails.setId(id);
		return animalRepository.save(animalDetails);
	}

	@Override
	@Transactional
	public void deleteAnimal(Long id) {
		if (!animalRepository.existsById(id)) {
			throw new ObjectNotFoundException("Animal not found with ID = " + id);
		}
		animalRepository.deleteById(id);
	}
}
