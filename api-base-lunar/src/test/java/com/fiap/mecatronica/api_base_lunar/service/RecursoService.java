package com.fiap.mecatronica.api_base_lunar.service;

import com.fiap.mecatronica.api_base_lunar.model.Recurso;
import com.fiap.mecatronica.api_base_lunar.repository.RecursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Avisa ao Spring que essa classe cuida das regras de negócio
public class RecursoService {

    @Autowired // Pede para o Spring injetar o repositório aqui dentro automaticamente
    private RecursoRepository repository;

    // Função para listar todos os recursos cadastrados
    public List<Recurso> listarTodos() {
        return repository.findAll();
    }

    // Função para buscar um único recurso pelo ID
    public Optional<Recurso> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Função para cadastrar/salvar um novo recurso
    public Recurso salvar(Recurso recurso) {
        // REGRA DE NEGÓCIO: Se o valor atual for menor ou igual ao limite crítico, ativa o alerta!
        if (recurso.getValorAtual() <= recurso.getLimiteCritico()) {
            recurso.setStatusOperacional("ALERTA CRÍTICO");
        } else {
            recurso.setStatusOperacional("NORMAL");
        }
        return repository.save(recurso);
    }

    // Função para atualizar um recurso que já existe
    public Recurso atualizar(Long id, Recurso recursoAtualizado) {
        return repository.findById(id).map(recurso -> {
            recurso.setNome(recursoAtualizado.getNome());
            recurso.setTipo(recursoAtualizado.getTipo());
            recurso.setValorAtual(recursoAtualizado.getValorAtual());
            recurso.setLimiteCritico(recursoAtualizado.getLimiteCritico());
            
            // Aplica a mesma regrinha de alerta na hora de atualizar os dados
            if (recursoAtualizado.getValorAtual() <= recursoAtualizado.getLimiteCritico()) {
                recurso.setStatusOperacional("ALERTA CRÍTICO");
            } else {
                recurso.setStatusOperacional("NORMAL");
            }
            return repository.save(recurso);
        }).orElseThrow(() -> new RuntimeException("Recurso não encontrado"));
    }

    // Função para deletar um recurso da base lunar
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}