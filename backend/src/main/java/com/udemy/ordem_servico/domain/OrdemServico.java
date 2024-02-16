package com.udemy.ordem_servico.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.ordem_servico.domain.enuns.Prioridade;
import com.udemy.ordem_servico.domain.enuns.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;

    private Integer prioridade;
    private String observacoes;
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public OrdemServico() {
        this.setDataAbertura(LocalDateTime.now());
        this.setPrioridade(Prioridade.BAIXA);
        this.setStatus(Status.ABERTO);
    }

    public OrdemServico(Integer id, Prioridade prioridade, String observacoes, Status status, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.dataAbertura = LocalDateTime.now();
        this.prioridade = (prioridade == null) ? 0 : prioridade.getCodigo();
        this.observacoes = observacoes;
        this.status = (status == null) ? 0 : status.getCodigo();
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    public Prioridade getPrioridade() {
        return Prioridade.toEnum(this.prioridade);
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade.getCodigo();
    }

    public Status getStatus() {
        return Status.toEnum(status);
    }

    public void setStatus(Status status) {
        this.status = status.getCodigo();
    }
}