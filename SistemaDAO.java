package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SistemaDAO {
    private static Connection con;
    
    public SistemaDAO() throws ClassNotFoundException, SQLException{
        con = MyConnection.getConnection();    
    }
    
    //Métodos do CRUD
    //INSERT (Create)
    public void insertSistema(Sistema sistema) throws SQLException{
        String sql = "INSERT INTO titulares(nome,nascimento,cpf,ra,senha)"
                + "VALUES(?,?,?,?,?,?)";
        
        PreparedStatement prep = con.prepareStatement(sql);
        
        prep.setString(1, sistema.getNome());
        prep.setString(2, sistema.getNascimento());
        prep.setString(3, sistema.getCpf());
        prep.setString(4, sistema.getRa());
        prep.setString(6, sistema.getSenha());
        
        prep.execute();
        prep.close();
    }
    
    //SELECT (Read)
    public ArrayList<Sistema> listCadastro() throws SQLException{
        //Criando lista vazia
        ArrayList<Sistema> list = new ArrayList<>();
        
        //Criando a query para o BD
        String query = "SELECT * FROM titulares";
        
        //Preparando a query para ser executada no BD
        PreparedStatement prep = con.prepareStatement(query);
        
        //Executar a query e receber o resultado com o auxílio
        //da classe ResultSet
        ResultSet result = prep.executeQuery();
        
        //Enquanto houverem registros, o código será executado
        while(result.next()) {
            //Criando um objeto vazio da classe Sistema
            Sistema cadastro = new Sistema();
            
            //Pegando os elementos do BD e preenchendo o objeto vazio
            cadastro.setIdSistema(result.getInt("id_sitema"));
            cadastro.setNome(result.getString("nome"));
            cadastro.setNascimento(result.getString("nascimento"));
            cadastro.setCpf(result.getString("cpf"));
            cadastro.setRa(result.getString("ra"));
          
            
            //Adicionando objeto cheio para a lista de Titulares
            list.add(cadastro);
        }
        
        //Retornando a lista completa
        return list;
    }
    
    
    //DELETE
    public void deleteTitular(int id) throws SQLException{
        String query = "DELETE FROM titulares WHERE id_titular = " + id;
        
        PreparedStatement prep = con.prepareStatement(query);
        
        prep.execute();
        prep.close();
    }
    
    
    //UPDATE
    public void updateTitular(Sistema ss) throws SQLException{
        //Query genérica
        String query = "UPDATE titulares SET nome = ?, "
                + "nascimento = ?, cpf = ?, ra = ?,"
                + "tipo = ? WHERE id_sistema = ?";
        
        //Preparando a query para ser executada no BD
        PreparedStatement prep = con.prepareStatement(query);
        
        //Inserir valores no lugar das interrogações
        prep.setString(1, ss.getNome());
        prep.setString(2, ss.getNascimento());
        prep.setString(3, ss.getRa());
        prep.setString(4, ss.getCpf());
        prep.setInt(6, ss.getIdSistema());
        
        //Executando query pronta
        prep.execute();
        prep.close();
    }
    
    
    //Selecionar apenas 1 registro
    public Sistema listById(int i) throws SQLException {
        //Criar um objeto vazio
        Sistema tt = new Sistema();
        
        //Criar query genérica
        String query = "SELECT * FROM titulares "
                + "WHERE id_titular = " + i;
        
        //Preparar a query para o BD
        PreparedStatement prep = con.prepareStatement(query);
        
        //Capturar o resultado da query
        ResultSet res = prep.executeQuery();
        
        //Inserir valores das colunas do resultado nos
        //atributos do objeto que desejamos retornar
        if(res.next()) {
            tt.setIdSistema(res.getInt("id_titular"));
            tt.setNome(res.getString("nome"));
            tt.setCpf(res.getString("cpf"));
            tt.setRa(res.getString("cep"));
            tt.setNascimento(res.getString("nascimento"));
            tt.setSenha(res.getString("senha"));
        }
        
        //Retornando o objeto preenchido
        return tt;
    }
    
    
    public Sistema listByName(String nome) throws SQLException {
        //Criar um objeto vazio
        Sistema tt = new Sistema();
        
        //Criar query genérica
        String query = "SELECT * FROM titulares "
                + "WHERE nome = ?";
        
        //Preparar a query para o BD
        PreparedStatement prep = con.prepareStatement(query);
        prep.setString(1, nome);
        
        //Capturar o resultado da query
        ResultSet res = prep.executeQuery();
        
        //Inserir valores das colunas do resultado nos
        //atributos do objeto que desejamos retornar
        if(res.next()) {
            tt.setIdSistema(res.getInt("id_titular"));
            tt.setNome(res.getString("nome"));
            tt.setCpf(res.getString("cpf"));
            tt.setRa(res.getString("cep"));
            tt.setNascimento(res.getString("nascimento"));
            tt.setSenha(res.getString("senha"));
        }
        
        //Retornando o objeto preenchido
        return tt;
    }
    
}//Fim da classe