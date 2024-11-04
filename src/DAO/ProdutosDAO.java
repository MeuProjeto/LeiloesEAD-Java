package DAO;



import DTO.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
         try {
            conn = new conectaDAO().connectDB();

            // Instrução SQL para executar o comando de INSERT
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            PreparedStatement query = conn.prepareStatement(sql);
            
            query.setString(1, produto.getNome());
            query.setDouble(2, produto.getValor());
            query.setString(3, produto.getStatus());
            
            // Executar o comando
            query.execute();
            
        } catch (SQLException e) {
            System.out.println("ERRO produtodao: " + e);
        }
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        String sql = "select * from produtos";
        
        try{
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                
                
                listagem.add(produto);
            }
            
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"104 ClienteDAO erro ao pesquisar: "+ e);
        }
         // retorno da lista
        
        return listagem;
    }
    
    
    
        
}

