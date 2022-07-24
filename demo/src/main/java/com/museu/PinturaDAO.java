package com.museu;

public class PinturaDAO {

private int numID_Objeto_Arte;
private String tipoTinta;
private String suporte;
private String estilo;


    //////////////////////////////////////////////
    //              GETs e SETs                 //
    //////////////////////////////////////////////

    public int getNumID_Objeto_Arte() {
        return numID_Objeto_Arte;
    }
    
    public void setNumID_Objeto_Arte(int numID_Objeto_Arte) {
        this.numID_Objeto_Arte = numID_Objeto_Arte;
    }

    public String getTipoTinta() {
        return tipoTinta;
    }
    
    public void setTipoTinta(String tipoTinta) {
        this.tipoTinta = tipoTinta;
    }

    public String getSuporte() {
        return suporte;
    }
    
    public void setSuporte(String suporte) {
        this.suporte = suporte;
    }

    public String getEstilo() {
        return estilo;
    }
    
    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }


    
}
