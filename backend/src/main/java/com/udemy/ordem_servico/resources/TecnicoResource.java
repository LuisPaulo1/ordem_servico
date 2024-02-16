package com.udemy.ordem_servico.resources;

import com.udemy.ordem_servico.domain.Tecnico;
import com.udemy.ordem_servico.domain.dtos.TecnicoDTO;
import com.udemy.ordem_servico.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico tecnico = service.findById(id);
        TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnico);
        return ResponseEntity.ok().body(tecnicoDTO);
    }

}
