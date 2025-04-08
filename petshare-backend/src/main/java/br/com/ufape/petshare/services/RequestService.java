package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.Request;
import br.com.ufape.petshare.repository.RequestRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService implements RequestServiceInterface {

	@Autowired
	private RequestRepository requestRepository;

	@Override
	@Transactional
	public Request saveRequest(Request request) {
		return requestRepository.save(request);
	}

	@Override
	public Request findRequestById(Long id) {
		return requestRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Request not found with ID = " + id));
	}
	
	@Override
	public List<Request> findRequestByUserId(Long userId) {
		return requestRepository.findRequestsByUserId(userId);
	}

	@Override
	public Page<Request> findRequestPage(PageRequest pageRequest) {
		return requestRepository.findAll(pageRequest);
	}

	@Override
	public List<Request> getAllRequests() {
		return requestRepository.findAll();
	}

	@Override
	@Transactional
	public Request updateRequest(Long id, Request requestDetails) {
		if (!requestRepository.existsById(id)) {
			throw new ObjectNotFoundException("Request not found with ID = " + id);
		}
		requestDetails.setId(id);
		return requestRepository.save(requestDetails);
	}

	@Override
	@Transactional
	public void deleteRequest(Long id) {
		if (!requestRepository.existsById(id)) {
			throw new ObjectNotFoundException("Request not found with ID = " + id);
		}
		requestRepository.deleteById(id);
	}
}
