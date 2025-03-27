package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.TypeItem;
import br.com.ufape.petshare.repository.TypeItemRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException;

@Service
public class TypeItemService implements TypeItemServiceInterface {

	@Autowired
	private TypeItemRepository typeItemRepository;

	@Override
	@Transactional
	public TypeItem saveTypeItem(TypeItem typeItem) {
		return typeItemRepository.save(typeItem);
	}

	@Override
	public TypeItem findTypeItemById(Long id) {
		return typeItemRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("TypeItem not found with ID = " + id));
	}

	@Override
	public Page<TypeItem> findTypeItemPage(PageRequest pageRequest) {
		return typeItemRepository.findAll(pageRequest);
	}

	@Override
	public List<TypeItem> getAllTypeItems() {
		return typeItemRepository.findAll();
	}

	@Override
	@Transactional
	public TypeItem updateTypeItem(Long id, TypeItem typeItemDetails) {
		if (!typeItemRepository.existsById(id)) {
			throw new ObjectNotFoundException("TypeItem not found with ID = " + id);
		}
		typeItemDetails.setId(id);
		return typeItemRepository.save(typeItemDetails);
	}

	@Override
	@Transactional
	public void deleteTypeItem(Long id) {
		if (!typeItemRepository.existsById(id)) {
			throw new ObjectNotFoundException("TypeItem not found with ID = " + id);
		}
		typeItemRepository.deleteById(id);
	}
}
