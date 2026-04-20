package com.michel.transito.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProprietarioIdInput {

    @NotNull
    private Long Id;

}
