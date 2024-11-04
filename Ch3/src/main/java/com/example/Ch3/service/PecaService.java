package com.example.Ch3.service;


import com.example.Ch3.model.Peca;
import com.example.Ch3.repository.PecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PecaService {

    private final PecaRepository pecaRepository;

    @Autowired
    public PecaService(PecaRepository pecaRepository) {
        this.pecaRepository = pecaRepository;
    }
    public List<Peca> listarTodasPecas() {
        return pecaRepository.listarPecas();
    }

    public ResponseEntity<Peca> buscarPecaPorId(int id) {
        Optional<Peca> peca = pecaRepository.buscarPorId((long) id);
        return peca.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Peca> inserirPeca(Peca peca) {
        pecaRepository.inserirPeca(peca);
        return ResponseEntity.ok(peca);
    }

    public ResponseEntity<Peca> atualizarPeca(int id, Peca peca) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> removerPeca(int id) {
        pecaRepository.removerPeca((long) id);
        return ResponseEntity.noContent().build();
    }
}
