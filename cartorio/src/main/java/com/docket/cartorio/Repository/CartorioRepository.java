package com.docket.cartorio.Repository;

import com.docket.cartorio.Model.Cartorio;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartorioRepository extends CrudRepository<Cartorio, Long> {

    List<Cartorio> findByNome(String nome);
} 