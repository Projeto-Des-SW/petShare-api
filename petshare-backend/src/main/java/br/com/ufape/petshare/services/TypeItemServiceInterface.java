package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.TypeItem;

public interface TypeItemServiceInterface {

	TypeItem saveTypeItem(TypeItem typeItem);

	TypeItem findTypeItemById(Long id);

	List<TypeItem> getAllTypeItems();

	TypeItem updateTypeItem(Long id, TypeItem typeItemDetails);

	void deleteTypeItem(Long id);

	Page<TypeItem> findTypeItemPage(PageRequest pageRequest);

}