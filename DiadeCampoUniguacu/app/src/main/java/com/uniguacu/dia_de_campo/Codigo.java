package com.uniguacu.dia_de_campo;

public class Codigo {
   private String idCod;
    private String cod;

    public Codigo(String idCod, String cod) {
        this.idCod = idCod;
        this.cod = cod;
    }

    public Codigo() {
    }

    public String getIdCod() {
        return idCod;
    }

    public void setIdCod(String  idCod) {
        this.idCod = idCod;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

}

