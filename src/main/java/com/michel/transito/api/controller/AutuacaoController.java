package com.michel.transito.api.controller;

import com.michel.transito.api.model.AutuacaoModel;
import com.michel.transito.api.model.input.AutuacaoInput;
import com.michel.transito.assembler.AutuacaoAssembler;
import com.michel.transito.domain.model.Autuacao;
import com.michel.transito.domain.model.Veiculo;
import com.michel.transito.domain.service.RegistroAutuacaoService;
import com.michel.transito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final RegistroVeiculoService registroVeiculoService;
    private final AutuacaoAssembler autuacaoAssembler;
    private final RegistroAutuacaoService registroAutuacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoModel registrar (@PathVariable Long veiculoId, @RequestBody @Valid AutuacaoInput autuacaoInput) {

        Autuacao novaAutuacao = autuacaoAssembler.toEntity(autuacaoInput);
        Autuacao autuacaoRegistrada =
                registroAutuacaoService.registrar(veiculoId, novaAutuacao);

        return autuacaoAssembler.toModel(autuacaoRegistrada);
    }

        @GetMapping
        public List<AutuacaoModel> listar (@PathVariable Long veiculoId) {
            Veiculo veiculo = registroVeiculoService.buscar(veiculoId);
            return autuacaoAssembler.toCollectionModel(veiculo.getAutuacoes());
        }

    }


