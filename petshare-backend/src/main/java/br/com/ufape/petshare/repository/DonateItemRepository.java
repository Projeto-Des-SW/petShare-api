package br.com.ufape.petshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufape.petshare.model.DonateItem;
import br.com.ufape.petshare.model.enums.ItemDonationStatus;

@Repository
public interface DonateItemRepository extends JpaRepository<DonateItem, Long> {

	List<DonateItem> findByDonorId(Long donorId);

	List<DonateItem> findByStatus(ItemDonationStatus string);
}