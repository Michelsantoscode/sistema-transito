package com.michel.transito.api.model.input;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AutuacaoInput {

    @NotBlank
    private String descricao;

    @Positive
    @NotNull
    private BigDecimal valorMulta;

}
