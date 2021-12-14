package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import livraria.Livro;
import livraria.Pedido;

public class DAOPedido {
    
    private Conection cdb = new Conection();
    private Pedido pedido = new Pedido();
    private Livro livro = new Livro();
    private DAOAutor daoautor = new DAOAutor();
    private DAOPublisher daopublisher = new DAOPublisher();
    
    public void insert(Pedido pedido) throws SQLException{ 

        // Faz a conexão com o banco
        cdb.conectar();

       // Faz a insercao da ordem de compra 
        String query0 = "INSERT INTO livraria.order (fk_iduser, nome) "
               + "VALUES (?,?)";
        PreparedStatement prep0 = cdb.getConection().prepareStatement(
                           query0,Statement.RETURN_GENERATED_KEYS);    
        
        // Pega o id do usuário e insere na consulta
        prep0.setInt(1, pedido.getUsuario().getId());
        // Insere a descrição da venda na consulta
        prep0.setString(2, "venda grande");
        // Executa a consulta
        prep0.execute();

            // Persiste a ordem de venda no banco
            cdb.getConection().commit();

        // Pega o id da ordem de venda
        ResultSet idOrdemVenda=prep0.getGeneratedKeys();
        idOrdemVenda.next();
        int idOV=idOrdemVenda.getInt("id");

       // A segunda parte é a insercao dos livros

       // Prepara a insercao em order_itens
       String query1 = "INSERT INTO livraria.order_itens (fk_idorder, fk_idproduto, qtde) "
               + "VALUES (?,?,?)";
       PreparedStatement prep1 = cdb.getConection().prepareStatement(
                            query1,Statement.RETURN_GENERATED_KEYS);
       
       // Prepara a atualização da lista de produtos        
       String query2 = "UPDATE livraria.livro" +
                        " SET qnt = ? WHERE id = ?";
       PreparedStatement prep2 = cdb.getConection().prepareStatement(
                            query2);


       // Prepara uma consulta por ID que será realizada para cada item da lista
       String queryLivro= "Select * from livraria.livro " +
                        "where id = ?";
       PreparedStatement prepLivro = cdb.getConection().prepareStatement(
                                queryLivro);

        // Percorre a lista de livros dentro de Pedido
        for(Livro l: pedido.getListaLivros()){

           // Faz a consulta no banco para obter a quantidade do produto
           prepLivro.setInt(1, l.getId());
           ResultSet produtoDoBanco = prepLivro.executeQuery();
           produtoDoBanco.next();
           int qtdeDeLivroNoBanco = produtoDoBanco.getInt("qnt");

            // Adiciona o produto vinculando no orderitens
            prep1.setInt(1, idOV);
            prep1.setInt(2, l.getId());
            prep1.setInt(3, l.getQnt());
            prep1.execute();

            // Atualiza a tabela de produtos, decrementando o estoque.
            prep2.setInt(1, qtdeDeLivroNoBanco - l.getQnt());
            prep2.setInt(2, l.getId());
            prep2.execute();

        }

        cdb.getConection().commit();
        cdb.close();
        
    }
    
}
