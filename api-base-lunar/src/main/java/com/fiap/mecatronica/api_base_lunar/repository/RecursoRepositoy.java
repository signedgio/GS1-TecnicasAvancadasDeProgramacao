package com.fiap.mecatronica.api_base_lunar.repository;

import com.fiap.mecatronica.api_base_lunar.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // avisa o spring que essa interface cuida do acesso aos dados
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    // JpaRepository eh quem traz prontos os métodos de salvar, buscar e deletar
}