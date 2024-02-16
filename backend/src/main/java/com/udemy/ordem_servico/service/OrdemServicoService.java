package com.udemy.ordem_servico.service;

import com.udemy.ordem_servico.domain.Cliente;
import com.udemy.ordem_servico.domain.OrdemServico;
import com.udemy.ordem_servico.domain.Tecnico;
import com.udemy.ordem_servico.domain.dtos.OrdemServicoDTO;
import com.udemy.ordem_servico.domain.enuns.Status;
import com.udemy.ordem_servico.repositories.ClienteRepository;
import com.udemy.ordem_servico.repositories.OrdemServicoRepository;
import com.udemy.ordem_servico.repositories.TecnicoRepository;
import com.udemy.ordem_servico.service.exceptions.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public OrdemServico findById(Integer id) {
		Optional<OrdemServico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemServico.class.getName()));
	}

	public List<OrdemServico> findAll() {
		return repository.findAll();
	}

	public @Valid OrdemServico create(@Valid OrdemServicoDTO obj) {
		return repository.save(fromDTO(obj));
	}

	public @Valid OrdemServico update(@Valid OrdemServicoDTO obj, Integer id) {
		OrdemServico oldObj = findById(id);
		oldObj = updateData(obj, oldObj);
		return repository.save(oldObj);
	}

	private OrdemServico updateData(@Valid OrdemServicoDTO obj, OrdemServico oldObj) {
		oldObj.setPrioridade(obj.getPrioridade());
		oldObj.setObservacoes(obj.getObservacoes());
		oldObj.setTecnico(tecnicoService.findById(obj.getTecnico()));
		oldObj.setStatus(obj.getStatus());
		
		if (obj.getStatus().equals(Status.ENCERRADO)) {
			oldObj.setDataFechamento(LocalDateTime.now());
		}

		return oldObj;
	}

	private OrdemServico fromDTO(OrdemServicoDTO obj) {
		OrdemServico newObj = new OrdemServico();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		tec.getOrdens().add(newObj);
		tecnicoRepository.save(tec);

		Cliente cli = clienteService.findById(obj.getCliente());
		cli.getOrdens().add(newObj);
		clienteRepository.save(cli);

		newObj.setTecnico(tec);
		newObj.setCliente(cli);

		return newObj;
	}
}
