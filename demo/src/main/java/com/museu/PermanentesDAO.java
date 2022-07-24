package com.museu;

public class PermanentesDAO {

private int numID_Objeto_Arte;
private String dataAquisicao;
private String emExposicao;



    //////////////////////////////////////////////
    //              GETs e SETs                 //
    //////////////////////////////////////////////

    public int getNumID_Objeto_Arte() {
        return numID_Objeto_Arte;
    }
    
    public void setNumID_Objeto_Arte(int numID_Objeto_Arte) {
        this.numID_Objeto_Arte = numID_Objeto_Arte;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }
    
    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getEmExposicao() {
        return emExposicao;
    }
    
    public void setEmExposicao(String emExposicao) {
        this.emExposicao = emExposicao;
    }




}
