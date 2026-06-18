package UsuarioDAO;
import connection.ConexaoMdb;
import model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    public String getIdPedido(int id) throws SQLException{
        String sql = "SELECT IDPEDIDO FROM PEDIDO WHERE IDPEDIDO = ?";
        try (Connection conn = ConexaoMdb.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return String.valueOf(rs.getInt("IDPEDIDO"));
                }
            }
        }
            return null;
    }
    public void inserir(Pedido p) throws SQLException{
        String sql = "INSERT INTO PEDIDO(QTDP, IDCLIENTE) VALUES(?,?)";
        try(Connection conn = ConexaoMdb.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){
            stmt.setInt(1, p.getQtdp());
            stmt.setInt(2, p.getCliente().getId());
            stmt.executeUpdate();
            System.out.println("Pedido registrado para o Cliente: " + p.getCliente().getNome());
        }
    }
    public void deletePedido(int id)throws SQLException{
        String pedido = getIdPedido(id);
        if (pedido == null){
            System.out.println("PEDIDO não encontrado!");

        return;}
        String sql = "DELETE FROM PEDIDO WHERE IDPEDIDO = ?";
        try (Connection conn = ConexaoMdb.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        System.out.println("Pedido cancelado!");
        }
    }
    public List<Pedido> todosPedidos() throws SQLException {
        String sql = "SELECT * FROM PEDIDO";
        List<Pedido> pedidoList = new ArrayList<>();
        try (Connection conn = ConexaoMdb.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Pedido p = new Pedido();
                    p.setIdPedido(rs.getInt("IDPRODUTO"));
                    p.setQtdp(rs.getInt("QTDP"));
                    pedidoList.add(p);
                }
            }
        return pedidoList;
        }
    public void atualizarPedido(Pedido p) throws SQLException{
        String sql = "UPDATE PEDIDO SET QTD = ? WHERE IDPEDIDO = ?";
        try(Connection conn = ConexaoMdb.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, p.getQtdp());
            stmt.setInt(2, p.getIdPedido());
            stmt.executeUpdate();
        }
    }
}
