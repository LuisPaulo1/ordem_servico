package com.udemy.ordem_servico.repositories;

import com.udemy.ordem_servico.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    @Query("SELECT obj FROM Tecnico obj WHERE obj.cpf =:cpf")
    Tecnico findByCpf(@Param("cpf") String cpf);
}