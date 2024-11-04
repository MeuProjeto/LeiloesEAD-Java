package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?useTimezone=true&serverTimezone=UTC","root", "R@ul1901");
            System.out.println("conexao sucesso");
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "100 Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
