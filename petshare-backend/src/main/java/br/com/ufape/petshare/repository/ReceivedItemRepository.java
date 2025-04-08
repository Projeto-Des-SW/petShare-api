package br.com.ufape.petshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufape.petshare.model.ReceivedItem;

@Repository
public interface ReceivedItemRepository extends JpaRepository<ReceivedItem, Long> {
	List<ReceivedItem> findByDonateItem_Id(Long donateId);
	List<ReceivedItem> findByRequest_Id(Long requestId);
	
	List<ReceivedItem> findByDonateItem_Donor_Id(Long donateDonorId);
	List<ReceivedItem> findByRequest_User_Id(Long requestUserId);
	
	List<ReceivedItem> findByReceiver_Id(Long receiverId);
}