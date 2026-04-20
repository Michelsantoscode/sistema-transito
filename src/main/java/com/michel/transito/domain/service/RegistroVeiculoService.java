package com.michel.transito.domain.service;

import com.michel.transito.domain.exception.EntidadeNaoEncontradaException;
import com.michel.transito.domain.exception.NegocioException;
import com.michel.transito.domain.model.Proprietario;
import com.michel.transito.domain.model.StatusVeiculo;
import com.michel.transito.domain.model.Veiculo;
import com.michel.transito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

     public Veiculo buscar (Long veiculoId) {
         return veiculoRepository.findById(veiculoId)
                 .orElseThrow(() -> new EntidadeNaoEncontradaException("Veiculo nao encontrado"));
     }


    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {

        if(novoVeiculo.getId() != null) {
            throw new NegocioException("Veiculo a ser cadastrado nao pode possuir um ID");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(novoVeiculo))
                        .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Ja existe um veiculo com essa placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());

        return veiculoRepository.save(novoVeiculo);
    }


}
