package com.github.gustavolopesmoreira.cadastro.model;

import java.util.Objects;
import java.util.UUID;

public class Usuario {

    private String id = UUID.randomUUID().toString();
    private String nome;
    private int idade;

    public Usuario() {}

    public Usuario(String nome, int idade) {
        setNome(nome);
        setIdade(idade);
    }

    public String getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public int getIdade() { return idade; }
    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("A idade não pode ser negativa.");
        }
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Usuario{id='" + id + "', nome='" + nome + "', idade=" + idade + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}