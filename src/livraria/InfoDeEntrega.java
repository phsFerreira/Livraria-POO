package livraria;

import livraria.Shipper;

public class InfoDeEntrega {
    
    String endereço;
    Shipper shipper;

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }
    
}
