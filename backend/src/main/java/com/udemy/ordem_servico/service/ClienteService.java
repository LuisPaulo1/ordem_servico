package com.udemy.ordem_servico.service;

import com.udemy.ordem_servico.domain.Cliente;
import com.udemy.ordem_servico.domain.dtos.ClienteDTO;
import com.udemy.ordem_servico.repositories.ClienteRepository;
import com.udemy.ordem_servico.service.exceptions.DataIntegratyViolationException;
import com.udemy.ordem_servico.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO obj) {
		if (Objects.requireNonNull(findByCpf(obj)).getClass().equals(Cliente.class)) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		Cliente newObj = new Cliente(null, obj.getNome(), obj.getCpf(), obj.getTelefone());
		return repository.save(newObj);
	}

	public Cliente update(ClienteDTO obj, Integer id) {
		Cliente oldObj = findById(id);
		verificarCPF(obj, id);
		oldObj.setNome(obj.getNome());
		oldObj.setCpf(obj.getCpf());
		oldObj.setTelefone(obj.getTelefone());

		return repository.save(oldObj);
	}

	private void verificarCPF(ClienteDTO obj, Integer id) {
		if (findByCpf(obj) != null && !Objects.equals(Objects.requireNonNull(findByCpf(obj)).getId(), id)) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
	}

	private Cliente findByCpf(ClienteDTO objDTO) {
		Cliente obj = repository.findByCpf(objDTO.getCpf());
		if (obj != null) {
			return obj;
		}
		return null;
	}
}
