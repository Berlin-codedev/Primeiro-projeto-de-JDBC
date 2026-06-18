package UsuarioDAO;

import connection.ConexaoMdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;


public class ClienteDAO {
    public String getNomePorId(int id) throws SQLException {
        String sql = "SELECT nome FROM CLIENTE WHERE IDCLIENTE = ?";
        try (Connection conn = ConexaoMdb.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nome");
                }
            }
        }
        return null;
    }

    public void deleteUser(int id) throws SQLException {
        String nome = getNomePorId(id);
        if (nome == null) {
            System.out.println("Não posso apagar: esse ID não existe!");
            return;
        }
        String sql = "DELETE FROM CLIENTE WHERE IDCLIENTE = ?";
        try (Connection conn = ConexaoMdb.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Usuario " + nome + " deletado com sucesso!");
        }
    }

    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT * FROM CLIENTE";
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = ConexaoMdb.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("IDCLIENTE"));
                c.setNome(rs.getString("NOME"));
                c.setSexo(rs.getString("SEXO"));
                c.setDataNasc(rs.getString("DATANASC"));
                lista.add(c);
            }
        }
        return lista;
    }

    public void inserir(Cliente c) throws SQLException {
        String sql = "INSERT INTO CLIENTE (NOME, IDADE, SEXO, DATANASC) VALUE (?,?,?,?)";
        try (Connection conn = ConexaoMdb.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getIdade());
            stmt.setString(3, c.getSexo());
            stmt.setDate(4, java.sql.Date.valueOf(c.getDataNasc()));
            stmt.executeUpdate();
        }
    }
    public void atualizar(Cliente c) throws SQLException{
        String sql = "UPDATE CLIENTE SET NOME = ?, IDADE = ?, SEXO = ?, DATANASC = ? WHERE IDCLIENTE = ?";
        try (Connection conn = ConexaoMdb.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, c.getNome());
            stmt.setInt(2, c.getIdade());
            stmt.setString(3, c.getSexo());
            stmt.setDate(4, java.sql.Date.valueOf(c.getDataNasc()));
            stmt.setInt(5, c.getId());
            stmt.executeUpdate();
            System.out.println("Cliente atualizado com sucesso!");

        }
    }
}