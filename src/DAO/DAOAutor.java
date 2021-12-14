package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import livraria.Autor;

public class DAOAutor {
    
    Conection conection = new Conection();
    
    public Autor consultarAutor(String nome) throws SQLException {
        
        conection.conectar();
        String query = "SELECT * FROM livraria.autor WHERE nome = ?";
        
        PreparedStatement prep = conection.getConection().prepareStatement(query);
        prep.setString(1, nome);
        
        ResultSet result = prep.executeQuery();
        
        Autor autor = new Autor();
        
        while (result.next()) {
            autor.setId(result.getInt("idAu"));
            autor.setName(result.getString("nome"));
        }
        
        return autor;
        
    }
    
}
