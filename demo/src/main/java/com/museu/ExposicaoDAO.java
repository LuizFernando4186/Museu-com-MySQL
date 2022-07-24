package com.museu;


public class ExposicaoDAO {

private String nome;
private String dataInicio;
private String dataFim;
private int numID_Objeto_Arte;



    //////////////////////////////////////////////
    //              GETs e SETs                 //
    //////////////////////////////////////////////

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }
    
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getNumID_Objeto_Arte() {
        return numID_Objeto_Arte;
    }
    
    public void setNumID_Objeto_Arte(int numID_Objeto_Arte) {
        this.numID_Objeto_Arte = numID_Objeto_Arte;
    }

}
