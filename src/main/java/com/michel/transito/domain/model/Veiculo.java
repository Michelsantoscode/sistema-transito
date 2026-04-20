package com.michel.transito.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.michel.transito.domain.exception.NegocioException;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity

@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private Proprietario proprietario;


    private String marca;
    private String modelo;
    private String placa;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status;

    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<>();

    public Autuacao adicionarAutuacao (Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }

    public void apreender () {
        if (estaApreendido()) {
            throw new NegocioException("Veiculo ja se encontra apreendido");
        }
        setStatus(StatusVeiculo.APRENDIDO);
        setDataApreensao(OffsetDateTime.now());
    }


    public boolean estaApreendido () {
        return StatusVeiculo.APRENDIDO.equals(getStatus());
    }

    public void removerApreensao () {
        if (naoEstaApreendido()) {
            throw new NegocioException("Veiculo nao esta aprendido");
        }

        setStatus(StatusVeiculo.REGULAR);
        setDataApreensao(null);

    }

    public boolean naoEstaApreendido() {
        return !estaApreendido();
    }







}
