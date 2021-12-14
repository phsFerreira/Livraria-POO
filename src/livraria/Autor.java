package livraria;

public class Autor {
    private int id;
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name!= null){
           this.name = name;
    }else{
            this.name = "Autor Desconhecido";
        }
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     
}
