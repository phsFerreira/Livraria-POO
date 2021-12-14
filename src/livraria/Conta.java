package livraria;

public class Conta {
    private String email;
    private int id;
    
    private long tempId;
    private String tempSenha;

    public boolean verificaSenha(){
        if(senha.equals (tempSenha)){
            return true;
        }
        return false;
    }
    public boolean validaLogin (long id, String senha){
        tempId = id;
        tempSenha = senha;
        return (validaLogin() && verificaSenha() ) ;
        
    }
    public boolean validaLogin(){
        if(id == tempId){
            return true;
        }
        return false;
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    private String senha;
    
}
