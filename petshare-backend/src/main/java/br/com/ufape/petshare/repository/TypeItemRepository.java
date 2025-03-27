package br.com.ufape.petshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufape.petshare.model.TypeItem;

@Repository
public interface TypeItemRepository extends JpaRepository<TypeItem, Long> {
}