package livraria;

import java.util.List;
import livraria.Usuario;

public class Pedido {
    private List<Livro> listaLivros;
    Livro livro;
    InfoDeEntrega infoDeEntrega;
    InfoDeConta infoDeConta;
    Usuario usuario;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public InfoDeEntrega getInfoDeEntrega() {
        return infoDeEntrega;
    }

    public void setInfoDeEntrega(InfoDeEntrega infoDeEntrega) {
        this.infoDeEntrega = infoDeEntrega;
    }

    public InfoDeConta getInfoDeConta() {
        return infoDeConta;
    }

    public void setInfoDeConta(InfoDeConta infoDeConta) {
        this.infoDeConta = infoDeConta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }   

    public List<Livro> getListaLivros() {
        return listaLivros;
    }

    public void setListaLivros(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }
    
}
