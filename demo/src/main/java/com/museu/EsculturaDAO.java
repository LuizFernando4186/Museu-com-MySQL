package com.museu;


public class EsculturaDAO {

private int numID_Objeto_Arte;
private String estilo;
private int peso;
private double altura;
private String material;


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

    public int getPeso() {
        return peso;
    }
    
    public void setPeso(int peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }
    
    public void setAltura(double altura) {
        this.altura = altura;
    }

    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }




}
