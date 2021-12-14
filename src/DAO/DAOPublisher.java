package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import livraria.Publisher;

public class DAOPublisher {
    
    Conection conection = new Conection();
    
    public void consultarPorId(int id, Publisher publisher) {
        conection.conectar();
        
        String query = "SELECT * FROM livraria.usuario";
        
        try {
            
            PreparedStatement prep = conection.getConection().prepareStatement(query);
            ResultSet resultado = prep.executeQuery();
            
            publisher.setIdPu(resultado.getInt("idPu"));
            publisher.setName(resultado.getString("nome"));
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public Publisher consultarPublisher(String nome) throws SQLException {
        
        conection.conectar();
        String query = "SELECT * FROM livraria.publisher WHERE nome = ?";
        
        PreparedStatement prep = conection.getConection().prepareStatement(query);
        prep.setString(1, nome);
        
        ResultSet result = prep.executeQuery();
        
        Publisher publisher = new Publisher();
        
        while (result.next()) {
            publisher.setIdPu(result.getInt("idPu"));
            publisher.setName(result.getString("nome"));
        }
        
        return publisher;
        
    }
    
}
