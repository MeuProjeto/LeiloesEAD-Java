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
    
     public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        //Ao repetir essa linha de codigo, aqui vais limpar os comandos anteriores cituados a listagem
          ArrayList<ProdutosDTO> listagem = new ArrayList<>();
         
        String sql = "select * from produtos where status = 'VENDIDO' ";
        
        try{
            conn = new conectaDAO().connectDB();
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                ProdutosDTO produtos = new ProdutosDTO();
                produtos.setId(resultset.getInt("id"));
                produtos.setNome(resultset.getString("nome"));
                produtos.setValor(resultset.getInt("valor"));
                produtos.setStatus(resultset.getString("status"));
                
                
                listagem.add(produtos);
            }
            
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null,"104 ClienteDAO erro ao pesquisar: "+ e);
        }
         // retorno da lista
        
        return listagem;
    }
    
    
     public boolean venderProduto(int produtoId) {
    try {
        conn = new conectaDAO().connectDB();

        // Instrução SQL para atualizar o status do produto
        String sql = "UPDATE produtos SET status = 'VENDIDO' WHERE id = ?";
        PreparedStatement query = conn.prepareStatement(sql);

        // Definir o ID do produto como parâmetro na consulta SQL
        query.setInt(1, produtoId);

        // Executar o comando de atualização
        int linhasAfetadas = query.executeUpdate();

        // Verificar se a atualização foi realizada
        if (linhasAfetadas > 0) {
            System.out.println("Produto com ID " + produtoId + " foi marcado como vendido.");
            return true;
        } else {
            System.out.println("Nenhum produto encontrado com o ID: " + produtoId);
            return false;
        }

    } catch (SQLException ex) {
        System.out.println("ERRO em ProdutosDAO: " + ex);
        return false;
    } finally {
        try {
            if (conn != null) conn.close(); // Fechar a conexão com o banco de dados
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
        
}

