package livraria;

import livraria.Publisher;

public class Livro {
    private String Titulo;
    Autor autor;
    Publisher publisher;
    private  int Id;
    private int Qnt;

    public int getQnt() {
        return Qnt;
    }

    public void setQnt(int Qnt) {
        this.Qnt = Qnt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
}
