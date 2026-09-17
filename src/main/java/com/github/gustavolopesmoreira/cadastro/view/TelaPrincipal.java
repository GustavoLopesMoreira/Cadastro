package com.github.gustavolopesmoreira.cadastro.view;

import com.github.gustavolopesmoreira.cadastro.dao.UsuarioDAO;
import com.github.gustavolopesmoreira.cadastro.model.Usuario;

import javax.swing.JOptionPane;

public class TelaPrincipal {

    public void iniciarCadastro() {
        try {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome do usuário:");
            if (nome == null) return;

            String idadeString = JOptionPane.showInputDialog(null, "Digite a idade de " + nome + ":");
            if (idadeString == null) return;

            int idade = Integer.parseInt(idadeString);

            Usuario novoUsuario = new Usuario(nome, idade);

            UsuarioDAO dao = new UsuarioDAO();
            dao.salvar(novoUsuario);

            JOptionPane.showMessageDialog(null,
                    "Usuário criado com sucesso!\nID: " + novoUsuario.getId(),
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IllegalArgumentException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "Dados Inválidos", JOptionPane.WARNING_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao processar a idade. Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}