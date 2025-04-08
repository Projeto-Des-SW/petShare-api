package br.com.ufape.petshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufape.petshare.model.AdoptionAnimal;

@Repository
public interface AdoptionAnimalRepository extends JpaRepository<AdoptionAnimal, Long> {
	List<AdoptionAnimal> findByAdopter_Id(Long adopterId);
	List<AdoptionAnimal> findByDonateAnimal_Donor_Id(Long donorId);
	
}