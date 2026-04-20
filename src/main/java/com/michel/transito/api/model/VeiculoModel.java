package com.michel.transito.api.model;

import com.michel.transito.domain.model.ProprietarioResumoModel;
import com.michel.transito.domain.model.StatusVeiculo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class VeiculoModel {

    private long id;
    private ProprietarioResumoModel proprietario;
    private String marca;
    private String modelo;
    private String numeroPlaca;
    private StatusVeiculo status;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;

}
