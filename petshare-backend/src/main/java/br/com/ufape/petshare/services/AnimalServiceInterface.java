package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.Animal;

public interface AnimalServiceInterface {

	Animal saveAnimal(Animal animal);

	Animal findAnimalById(Long id);

	List<Animal> getAllAnimals();

	Animal updateAnimal(Long id, Animal animalDetails);

	void deleteAnimal(Long id);

	Page<Animal> findAnimalPage(PageRequest pageRequest);

}