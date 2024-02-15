package com.udemy.ordem_servico.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Tecnico extends Pessoa implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "tecnico")
    private List<OrdemServico> ordens = new ArrayList<>();

    public Tecnico() {
        super();
    }

    public Tecnico(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }
}