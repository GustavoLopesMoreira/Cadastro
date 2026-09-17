package com.github.gustavolopesmoreira.cadastro.dao;

import com.github.gustavolopesmoreira.cadastro.model.Usuario;

public class UsuarioDAO {

    public void salvar(Usuario usuario) {
        System.out.println("Preparando para salvar no banco: " + usuario.getNome());
    }
}