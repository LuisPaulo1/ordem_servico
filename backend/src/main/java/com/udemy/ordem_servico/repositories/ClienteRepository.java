package com.udemy.ordem_servico.repositories;

import com.udemy.ordem_servico.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT obj FROM Cliente obj WHERE obj.cpf =:cpf")
    Cliente findByCpf(String cpf);
}