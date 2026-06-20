package connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class ConexaoMdb {
    private static final String URL = "jdbc:mariadb://localhost:3306/ESTUDO";
    private static final String USUARIO = "";
    private static final String SENHA = " ";
    static {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.err.println("Erro critico: Drive Mariadb não encontrado!" + e.getMessage());
            throw new RuntimeException("Falha ao carregar o Banco de dados", e);
        }
    }
    public static Connection conectar(){
        try{
            return DriverManager.getConnection(URL,USUARIO,SENHA);
        } catch (SQLException e){
            System.err.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}
