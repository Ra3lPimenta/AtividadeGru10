package model;

import java.sql.*;
import java.util.*;


public class LivroDAO  {
	public void salvar(Livro livro) {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "INSERT INTO Tabela_Pimenta_e_Andrade (titulo, autor, genero, idioma, quantidade, preco) VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setString(4, livro.getIdioma());
            stmt.setString(5, livro.getQuantidade());
            stmt.setString(6, livro.getPreco());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Livro: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	public static void atualizar(Livro livro) {
        Connection conn = null;
        PreparedStatement stmt = null; 

        String sql = "UPDATE Tabela_Pimenta_e_Andrade SET titulo = ?, autor = ?, genero = ?, idioma = ?, quantidade = ?, preco = ? WHERE id = ?";

        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setString(4, livro.getIdioma());
            stmt.setString(5, livro.getQuantidade());
            stmt.setString(6, livro.getPreco());
            stmt.setInt(7, livro.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar livro: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	public void excluir (int id) {
		Connection conn = null;
        PreparedStatement stmt = null;
        
        String sql = "DELETE FROM Tabela_Pimenta_e_Andrade WHERE id = ?";
        
        try {
            conn = Conexao.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar Livro: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
	
	 public List<Livro> listar() {
	        List<Livro> lista = new ArrayList<Livro>();

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        String sql = "SELECT id, titulo, autor, genero, idioma, genero, quantidade, preco FROM Tabela_Pimenta_e_Andrade ORDER BY id DESC";

	        try {
	            conn = Conexao.conectar();
	            stmt = conn.prepareStatement(sql);
	            rs = stmt.executeQuery();

	            while (rs.next()) {
	               Livro livro = new Livro();
	                livro.setId(rs.getInt("id"));
	                livro.setTitulo(rs.getString("titulo"));
	                livro.setAutor(rs.getString("autor"));
	                livro.setGenero(rs.getString("genero"));
	                livro.setQuantidade(rs.getString("quantidade"));
	                livro.setPreco(rs.getString("preco"));
	                lista.add(livro);
	            }

	        } catch (Exception e) {
	            throw new RuntimeException("Erro ao listar livros: " + e.getMessage());
	        } finally {
	            try {
	                if (rs != null) {
	                    rs.close();
	                }
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return lista;
	 }
}