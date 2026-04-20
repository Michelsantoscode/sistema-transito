package com.michel.transito.domain.service;

import com.michel.transito.domain.exception.NegocioException;
import com.michel.transito.domain.model.Proprietario;
import com.michel.transito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public Proprietario buscar (Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .orElseThrow(() -> new NegocioException("Proprietario nao encontrado"));

    }

    @Transactional
    public Proprietario salvar (Proprietario proprietario) {
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(p -> !p.equals(proprietario))
                .isPresent();

        if (emailEmUso) {
            throw new NegocioException("Ja existe um proprietario com esse email");
        }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir (Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }

}
