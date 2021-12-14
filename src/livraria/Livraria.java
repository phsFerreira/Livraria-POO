package livraria;

import DAO.DAOPedido;
import DAO.DAOUser;
import DAO.DaoLivro;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Livraria {
    
    public static void main(String[] args) throws SQLException {
        
        // Geração das classes em objetos
        Scanner scan = new Scanner(System.in); // Irá receber dados do usuário
        Pedido pedido = new Pedido(); // Guardará as informações do pedido
        Usuario usuario = new Usuario(); // Criará o usuário
        
        // Receberá os itens para registrar a venda futuramente
        List<Livro> listaItens = new ArrayList<>();
        // Receberá a lista dos livros para imprimí-los na tela
        List<Livro> listaLivros = new ArrayList<>();
        
        // Criação das classes que se comunicaram com o banco de dados
        DAOUser daouser = new DAOUser(); // Fará as consultas na tabela usuario
        DaoLivro daolivro = new DaoLivro(); // Fará as consultas na tabela livro
        DAOPedido daopedido = new DAOPedido(); // Fará as consultas na tabela pedido
        
        // lista os livros para que o usuário possa selecioná-los
        listaLivros = daolivro.listar("");
        
        // Usuário seleciona sua conta
        System.out.println("Selecione seu usuário: ");
        daouser.listarUsuarios();
        
        // Guarda o id da conta
        int contaSelecionada = scan.nextInt();
        // Consulta o id selecionado e atualiza as informações do objeto
        // usuário com as informaçõe que vieram do Banco de Dados
        daouser.consultarPorId(contaSelecionada, usuario);
                
        int continuar; // controle da repetição do fluxo de seleção de livros
        do {
            
            // Gerando tabela dos livros em estoque
            for (Livro l: listaLivros) {
                System.out.print("Id: " + l.getId() + "\t");
                System.out.println("Qtd: " + l.getQnt());
                System.out.println("Titulo: " + l.getTitulo());
                System.out.println("Autor: " + l.getAutor().getName());
                System.out.println("Editora: " + l.getPublisher().getName() + "\n");
            }
            
            // Guarda o ID do livro selecionado
            System.out.println("Selecione o livro que deseja comprar: \n");
            int livroSelecionado = scan.nextInt();
            
            // Guarda a quantidade escolhida
            System.out.println("Informe quantas unidades deseja comprar: \n");
            int quantidade = scan.nextInt();
            
            // Cria o objeto livro
            Livro livro = new Livro();
            // consulta suas informações no Banco de Dados e atualiza as 
            // informações do objeto
            daolivro.consultarPorId(livroSelecionado, livro);
            // insere no objeto a quantidade selecionada pelo usuário
            livro.setQnt(quantidade);
            
            // Adiciona o objeto a lista que será enviada para o Banco na ordem
            // de compra
            listaItens.add(livro);
            
            // Controle da repetição do fluxo de venda que inicia com a listagem
            // dos títulos disponíveis
            System.out.println("Deseja comprar outro livro? \nDigite 1 para SIM\nDigite 0 para NÃO\n");
            continuar = scan.nextInt();
            
        } while (continuar != 0);
        
        // Insere a lsita de livros selecionados dentro do objeto pedido
        pedido.setListaLivros(listaItens);
        // Insere dentro do objeto pedido o usuário que fez a compra
        pedido.setUsuario(usuario);
        
        // Efetua a venda
        daopedido.insert(pedido);
    }
}