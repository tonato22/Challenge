package com.example.Ch3.controler;


import com.example.Ch3.model.Peca;
import com.example.Ch3.service.PecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://oztech.vercel.app")

@RestController
@RequestMapping("/pecas")
public class PecaController {

    @Autowired
    private PecaService pecaService;

    @GetMapping
    public List<Peca> listarPecas() {
        return pecaService.listarTodasPecas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Peca> buscarPeca(@PathVariable int id) {
        return pecaService.buscarPecaPorId(id);
    }

    @PostMapping
    public ResponseEntity<Peca> inserirPeca(@RequestBody Peca peca) {
        return pecaService.inserirPeca(peca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Peca> atualizarPeca(@PathVariable int id, @RequestBody Peca peca) {
        return pecaService.atualizarPeca(id, peca);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPeca(@PathVariable int id) {
        return pecaService.removerPeca(id);
    }
}
