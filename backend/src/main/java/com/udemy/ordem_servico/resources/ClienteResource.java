package com.udemy.ordem_servico.resources;

import com.udemy.ordem_servico.domain.dtos.ClienteDTO;
import com.udemy.ordem_servico.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new ClienteDTO(service.findById(id)));
	}

	@GetMapping()
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<ClienteDTO> listDTO = service.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO obj) {
		obj = new ClienteDTO(service.create(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO obj, @PathVariable Integer id) {
		obj = new ClienteDTO(service.update(obj, id));
		return ResponseEntity.ok().body(obj);
	}
	
}
