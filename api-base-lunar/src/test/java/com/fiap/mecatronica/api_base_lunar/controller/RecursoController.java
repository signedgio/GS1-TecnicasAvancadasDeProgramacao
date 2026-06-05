package com.fiap.mecatronica.api_base_lunar.controller;

import com.fiap.mecatronica.api_base_lunar.model.Recurso;
import com.fiap.mecatronica.api_base_lunar.service.RecursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos") // Define a URL base: http://localhost:8080/api/recursos
@CrossOrigin(origins = "*") // Libera o CORS para que o App Mobile consiga acessar aqui sem ser bloqueado!
public class RecursoController {

    @Autowired
    private RecursoService service;

    // Rota GET: Retorna a lista de todos os recursos para o App mostrar na tela de Status
    @GetMapping
    public List<Recurso> listar() {
        return service.listarTodos();
    }

    // Rota GET por ID: Busca apenas um recurso específico
    @GetMapping("/{id}")
    public ResponseEntity<Recurso> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Rota POST: Recebe o novo recurso que você cadastrar na tela de Cadastro do celular
    @HttpPost
    public Recurso criar(@RequestBody Recurso recurso) {
        return service.salvar(recurso);
    }

    // Rota PUT: Permite atualizar os dados de um recurso/sensor existente
    @PutMapping("/{id}")
    public ResponseEntity<Recurso> atualizar(@PathVariable Long id, @RequestBody Recurso recurso) {
        try {
            return ResponseEntity.ok(service.atualizar(id, recurso));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Rota DELETE: Permite remover um recurso caso ele seja desativado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}