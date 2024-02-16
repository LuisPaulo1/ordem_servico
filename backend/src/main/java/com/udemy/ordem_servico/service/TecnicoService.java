package com.udemy.ordem_servico.service;

import com.udemy.ordem_servico.domain.Pessoa;
import com.udemy.ordem_servico.domain.Tecnico;
import com.udemy.ordem_servico.domain.dtos.TecnicoDTO;
import com.udemy.ordem_servico.repositories.TecnicoRepository;
import com.udemy.ordem_servico.service.exceptions.DataIntegratyViolationException;
import com.udemy.ordem_servico.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Tecnico.class.getName()));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public TecnicoDTO create(TecnicoDTO obj) {
        if (Objects.requireNonNull(findByCpf(obj)).getClass().equals(Tecnico.class))
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");

        Tecnico tecnico = new Tecnico(null, obj.getNome(), obj.getCpf(), obj.getTelefone());
        return new TecnicoDTO(repository.save(tecnico));
    }

    public @Valid Tecnico update(@Valid TecnicoDTO obj, Integer id) {
        Tecnico oldObj = findById(id);
        if (findByCpf(obj) != null && !Objects.equals(Objects.requireNonNull(findByCpf(obj)).getId(), id)) {
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        oldObj.setNome(obj.getNome());
        oldObj.setCpf(obj.getCpf());
        oldObj.setTelefone(obj.getTelefone());
        return repository.save(oldObj);
    }

    private Pessoa findByCpf(TecnicoDTO objDTO) {
        Pessoa obj = repository.findByCpf(objDTO.getCpf());
        if (obj != null) {
            return obj;
        }
        return null;
    }

}