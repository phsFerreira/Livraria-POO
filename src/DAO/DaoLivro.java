package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import livraria.Autor;
import livraria.Livro;
import livraria.Publisher;

public class DaoLivro {
    
    private Conection conection = new Conection();

    //incluir
    public void incluir(Livro livro) throws SQLException {
        conection.conectar();

        String query = "INSERT INTO livraria.livros (titulo, autor, publisher, qnt) "
                + "VALUES (?,?,?,?)";
        PreparedStatement prep = conection.getConection().prepareStatement(
                query,
                Statement.RETURN_GENERATED_KEYS);
        prep.setString(1, livro.getTitulo());
        prep.setString(2, livro.getAutor().getName());
        prep.setString(3, livro.getPublisher().getName());
        prep.setInt(4, livro.getQnt());
        prep.execute();
        conection.getConection().commit();

        conection.close();
    }

    //alterar - a model deve estar com o id preenchido
    public void alterar(Livro livro) throws SQLException {
        conection.conectar();
        try {
            String query = "UPDATE livraria .livros "
                    + "SET titulo=?, autor=?, publisher=?, qnt=? WHERE id=?";
            PreparedStatement prep = conection.getConection().prepareStatement(query);

            prep.setInt(5, livro.getId());
            prep.setString(1, livro.getTitulo());
            prep.setString(2, livro.getAutor().getName());
            prep.setString(3, livro.getPublisher().getName());
            prep.setInt(4, livro.getQnt());
            prep.execute();

            conection.getConection().commit();
            conection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //excluir
    public void excluir(Livro livro) {
        conection.conectar();
        try {
            String query = "DELETE FROM livraria.livros "
                    + "WHERE id=?";
            PreparedStatement prep = conection.getConection().prepareStatement(query);

            prep.setInt(1, livro.getId());
            prep.execute();

            conection.getConection().commit();
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //consultar
    
    public void consultarPorId(int id, Livro livro) {
        
        conection.conectar();
        
        String query = "SELECT * FROM livraria.livro WHERE id = ?";
        
        try {
            
            PreparedStatement prep = conection.getConection().prepareStatement(query);
            prep.setInt(1, id);
            ResultSet resultado = prep.executeQuery();
            
            while (resultado.next()) {
                livro.setId(resultado.getInt("id"));
                livro.setTitulo(resultado.getString("titulo"));
                livro.setQnt(resultado.getInt("qnt"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoLivro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int consultarPorTitulo(Livro livro) {
        conection.conectar();
        int idTmp = -1;
        String query = "SELECT * from livraria.livros "
                + "WHERE titulo = ?";
        try {
            PreparedStatement prep = conection.getConection().prepareStatement(query);
            prep.setString(1, livro.getTitulo());

            ResultSet list = prep.executeQuery();

            while (list.next()) {
                idTmp = list.getInt("id");
                livro.setId(list.getInt("id"));
                livro.setTitulo(list.getString("titulo"));
                Autor autor = new Autor();
                autor.setName(list.getString("autor"));
                livro.setAutor(autor);
                Publisher publisher = new Publisher();
                publisher.setName(list.getString("publisher"));
                livro.setPublisher(publisher);
                livro.setQnt(list.getInt("qnt"));
                break;
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        return idTmp;
    }

    public List listar(String params) {
        conection.conectar();
        List<Livro> listaLivros = new ArrayList<>();
        String query = "SELECT * from livraria.livro" + params;

        try {
            PreparedStatement prep = conection.getConection().prepareStatement(query); //prepara a query
            ResultSet lista = prep.executeQuery(); //executa a query e joga o resultado na variavel

            while (lista.next()) {
                Livro livro = new Livro();
                livro.setId(lista.getInt("id"));
                livro.setTitulo(lista.getString("titulo"));
                Autor autor = new Autor();
                autor.setName(lista.getString("autor"));
                livro.setAutor(autor);
                Publisher publisher = new Publisher();
                publisher.setName(lista.getString("publisher"));
                livro.setPublisher(publisher);
                livro.setQnt(lista.getInt("qnt"));
                listaLivros.add(livro);
            }
            conection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLivros;
    }

}
