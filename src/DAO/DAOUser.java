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
import livraria.Usuario;
public class DAOUser {
    
    Conection conection = new Conection();
    
    public void listarUsuarios() {
        
        conection.conectar();
        
        String query = "SELECT * FROM livraria.usuario";
        
        try {
            
            PreparedStatement prep = conection.getConection().prepareStatement(query);
            ResultSet resultado = prep.executeQuery();
            
            System.out.println("id\tusuario");
            while (resultado.next()) {
                
                System.out.print(resultado.getInt("id") + "\t");
                System.out.println(resultado.getString("nome"));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conection.close();
        
    }
    
    public void consultarPorId(int id, Usuario usuario) {
        
        conection.conectar();
        
        String query = "SELECT * FROM livraria.usuario WHERE id = ?";
        
        try {
            
            PreparedStatement prep = conection.getConection().prepareStatement(query);
            prep.setInt(1, id);
            ResultSet resultado = prep.executeQuery();
            
            while (resultado.next()) {
                usuario.setId(resultado.getInt("id"));
                usuario.setName(resultado.getString("nome"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAOUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        conection.close();
        
    }
    
}
