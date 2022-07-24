package com.museu;


public class OutrosDAO {

private int numID_Objeto_Arte;
private String estilo;
private String tipo;


    //////////////////////////////////////////////
    //              GETs e SETs                 //
    //////////////////////////////////////////////

    public int getNumID_Objeto_Arte() {
        return numID_Objeto_Arte;
    }
    
    public void setNumID_Objeto_Arte(int numID_Objeto_Arte) {
        this.numID_Objeto_Arte = numID_Objeto_Arte;
    }

    public String getEstilo() {
        return estilo;
    }
    
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
