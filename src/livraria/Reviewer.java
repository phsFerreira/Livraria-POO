package livraria;


import livraria.Usuario;

public class Reviewer {
    private Usuario usuario;
    private Review review;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }    
}
