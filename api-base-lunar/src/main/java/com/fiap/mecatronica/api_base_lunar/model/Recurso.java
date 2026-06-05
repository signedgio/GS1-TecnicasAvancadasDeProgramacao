package com.fiap.mecatronica.api_base_lunar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Representação da entidade no banco de dados
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome; // Ex: Água, Energia[cite: 1]
    private String tipo; // Categoria do recurso[cite: 1]
    private Double valorAtual;
    private Double limiteCritico;
    private String statusOperacional; // Status que exibirá os alertas[cite: 1, 2]

    // Construtor Vazio
    public Recurso() {}

    // Construtor Cheio
    public Recurso(Long id, String nome, String tipo, Double valorAtual, Double limiteCritico, String statusOperacional) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valorAtual = valorAtual;
        this.limiteCritico = limiteCritico;
        this.statusOperacional = statusOperacional;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Double getValorAtual() { return valorAtual; }
    public void setValorAtual(Double valorAtual) { this.valorAtual = valorAtual; }
    public Double getLimiteCritico() { return limiteCritico; }
    public void setLimiteCritico(Double limiteCritico) { this.limiteCritico = limiteCritico; }
    public String getStatusOperacional() { return statusOperacional; }
    public void setStatusOperacional(String statusOperacional) { this.statusOperacional = statusOperacional; }
}