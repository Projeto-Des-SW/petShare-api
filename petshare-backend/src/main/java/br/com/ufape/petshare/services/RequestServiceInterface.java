package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.Request;

public interface RequestServiceInterface {

	Request saveRequest(Request request);

	Request findRequestById(Long id);
	
	List<Request> findRequestByUserId(Long userId);

	List<Request> getAllRequests();

	Request updateRequest(Long id, Request requestDetails);

	void deleteRequest(Long id);

	Page<Request> findRequestPage(PageRequest pageRequest);

	List<Request> getOpenRequests();

}