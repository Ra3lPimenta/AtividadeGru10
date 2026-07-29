package controller;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;

import model.Livro;
import model.LivroDAO;
import view.TelaCadastro;
public class ClienteController {

    private TelaCadastro tela;
    private LivroDAO LivroDAO;

    public ClienteController(TelaCadastro tela) {
        this.tela = tela;
        this.LivroDAO = new LivroDAO();
    }

    public void salvar() {
        String titulo = tela.getTxtTitulo().getText().trim();
        String autor = tela.getTxtAutor().getText().trim();
        String genero  = tela.getTxtGenero().getText().trim();
        String idioma = tela.getTxtIdioma().getText().trim();
        String quantidade = tela.getTxtQuantidade().getText().trim();
        String preco = tela.getTxtPreco().getText().trim();
        

        if (titulo.isEmpty() || 
        	autor.isEmpty() ||
        	genero.isEmpty() ||
        	idioma.isEmpty() ||
        	quantidade.isEmpty() ||
        	preco.isEmpty()){
            JOptionPane.showMessageDialog(
                tela,
                "Preencha todos os campos",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            String idTexto = tela.getTxtID().getText().trim();

            if (idTexto.isEmpty()) {
                Livro livro = new Livro(titulo, autor, genero, idioma, quantidade, preco);
                LivroDAO.salvar(livro);
                JOptionPane.showMessageDialog(tela, "Livro salvo com sucesso.");
            } else {
               Livro livro = new Livro(Integer.parseInt(idTexto), titulo, autor, genero, idioma, quantidade, preco);
                model.LivroDAO.atualizar(livro);
                JOptionPane.showMessageDialog(tela, "Livro atualizado com sucesso.");
            }

            limpar();
            carregarTabela();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                tela,
                "Erro ao salvar: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void excluir() {
        int linha = tela.getTabela_Pimenta_e_Andrade().getSelectedRow();

        if (linha == -1) {
            JOptionPane.showMessageDialog(
                tela,
                "Selecione um livro na tabela para excluir.",
                "Atenção",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
            tela,
            "Deseja realmente excluir o livro selecionado?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            int id = Integer.parseInt(tela.getTxtID().getText());
            LivroDAO.excluir(id);
            JOptionPane.showMessageDialog(tela, "Livro excluído com sucesso.");
            limpar();
            carregarTabela();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                tela,
                "Erro ao excluir: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void limpar() {
        tela.getTxtID().setText("");
        tela.getTxtTitulo().setText("");
        tela.getTxtAutor().setText("");
        tela.getTxtGenero().setText("");
        tela.getTxtIdioma().setText("");
        tela.getTxtQuantidade().setText("");
        tela.getTxtPreco().setText("");
        tela.getTabela_Pimenta_e_Andrade().clearSelection();
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) tela.getTabela_Pimenta_e_Andrade().getModel();
        modelo.setRowCount(0);

        try {
            List<Livro> livros = LivroDAO.listar();

            int i;
            for (i = 0; i < livros.size(); i++) {
                Livro c = livros.get(i);
                modelo.addRow(new Object[] {
                    c.getId(),
                    c.getTitulo(),
                    c.getAutor(),
                    c.getGenero(),
                    c.getIdioma(),
                    c.getQuantidade(),
                    c.getPreco(),
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                tela,
                "Erro ao carregar tabela: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void preencherFormulario() {
        int linha = tela.getTabela_Pimenta_e_Andrade().getSelectedRow();

        if (linha != -1) {
            tela.getTxtID().setText(tela.getTabela_Pimenta_e_Andrade().getValueAt(linha, 0).toString());
            tela.getTxtTitulo().setText(tela.getTabela_Pimenta_e_Andrade().getValueAt(linha, 1).toString());
            tela.getTxtAutor().setText(tela.getTabela_Pimenta_e_Andrade().getValueAt(linha, 2).toString());
            tela.getTxtGenero().setText(tela.getTabela_Pimenta_e_Andrade().getValueAt(linha, 3).toString());
            tela.getTxtIdioma().setText(tela.getTabela_Pimenta_e_Andrade().getValueAt(linha, 4).toString());
            tela.getTxtQuantidade().setText(tela.getTabela_Pimenta_e_Andrade().getValueAt(linha, 5).toString());
            tela.getTxtPreco().setText(tela.getTabela_Pimenta_e_Andrade().getValueAt(linha, 6).toString());
        }
    }
}