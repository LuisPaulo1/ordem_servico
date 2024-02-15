package com.udemy.ordem_servico;

import com.udemy.ordem_servico.domain.Cliente;
import com.udemy.ordem_servico.domain.OrdemServico;
import com.udemy.ordem_servico.domain.Tecnico;
import com.udemy.ordem_servico.domain.enuns.Prioridade;
import com.udemy.ordem_servico.domain.enuns.Status;
import com.udemy.ordem_servico.repositories.ClienteRepository;
import com.udemy.ordem_servico.repositories.OrdemServicoRepository;
import com.udemy.ordem_servico.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OrdemServicoApplication implements CommandLineRunner {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrdemServicoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tecnico t1 = new Tecnico(null, "Lucas", "673.684.300-61", "(11) 99999-9999");
        Cliente c1 = new Cliente(null, "Maria", "560.790.160-28", "(11) 99999-9999");
        OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Teste", Status.ANDAMENTO, t1, c1);
        t1.getOrdens().add(os1);
        c1.getOrdens().add(os1);
        tecnicoRepository.saveAll(List.of(t1));
        clienteRepository.saveAll(List.of(c1));
        ordemServicoRepository.saveAll(List.of(os1));
    }
}
