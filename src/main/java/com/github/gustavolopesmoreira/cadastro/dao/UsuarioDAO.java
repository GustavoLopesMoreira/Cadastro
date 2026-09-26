package com.github.gustavolopesmoreira.cadastro.dao;

import com.github.gustavolopesmoreira.cadastro.model.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // Nome do ficheiro que será criado na raiz do seu projeto
    private static final String ARQUIVO = "usuarios.txt";

    public void salvar(Usuario usuario) {
        // O parâmetro 'true' no FileWriter indica que vamos adicionar (append) ao final do ficheiro
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(usuario.getId() + ";" + usuario.getNome() + ";" + usuario.getIdade());
            bw.newLine(); // Pula para a próxima linha
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar usuário no arquivo: " + e.getMessage(), e);
        }
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        File file = new File(ARQUIVO);

        // Se o ficheiro ainda não existir, retorna a lista vazia
        if (!file.exists()) {
            return usuarios;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    // partes[0] = id, partes[1] = nome, partes[2] = idade
                    usuarios.add(new Usuario(partes[0], partes[1], Integer.parseInt(partes[2])));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler usuários do arquivo: " + e.getMessage(), e);
        }

        return usuarios;
    }

    public Usuario buscarPorId(String id) {
        for (Usuario u : listarTodos()) {
            if (u.getId().equals(id)) {
                return u;
            }
        }
        return null; // Não encontrou
    }

    public void atualizar(Usuario usuario) {
        List<Usuario> usuarios = listarTodos();
        boolean encontrou = false;

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(usuario.getId())) {
                usuarios.set(i, usuario); // Atualiza o utilizador na lista em memória
                encontrou = true;
                break;
            }
        }

        if (!encontrou) {
            throw new RuntimeException("Nenhum usuário encontrado com o id informado.");
        }

        reescreverArquivo(usuarios);
    }

    public void excluir(String id) {
        List<Usuario> usuarios = listarTodos();
        boolean removeu = usuarios.removeIf(u -> u.getId().equals(id));

        if (removeu) {
            reescreverArquivo(usuarios);
        }
    }

    // Método auxiliar para regravar todo o ficheiro quando há uma edição ou exclusão
    private void reescreverArquivo(List<Usuario> usuarios) {
        // FileWriter sem o 'true', logo ele apaga o ficheiro antigo e cria um novo do zero
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Usuario u : usuarios) {
                bw.write(u.getId() + ";" + u.getNome() + ";" + u.getIdade());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao reescrever o arquivo: " + e.getMessage(), e);
        }
    }
}