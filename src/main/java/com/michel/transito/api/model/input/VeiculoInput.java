package com.michel.transito.api.model.input;

import com.michel.transito.domain.model.Proprietario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VeiculoInput {

    @NotBlank
    @Size(max = 20)
    private String marca;


    @NotBlank
    @Size(max = 20)
    private String modelo;

    @NotNull
    @Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z][0-9]{2}")
    private String placa;

    @NotNull
    @Valid
    private ProprietarioIdInput proprietario;

}
