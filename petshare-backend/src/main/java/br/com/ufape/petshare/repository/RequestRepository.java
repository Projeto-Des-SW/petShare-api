package br.com.ufape.petshare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufape.petshare.model.Request;
import br.com.ufape.petshare.model.enums.RequestStatus;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

	List<Request> findRequestsByUserId(Long userId);

	List<Request> findByStatus(RequestStatus string);
}