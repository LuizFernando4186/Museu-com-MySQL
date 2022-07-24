package com.museu;


public class EmprestadosDAO {

private int numID_Objeto_Arte;
private String dataDevolucao;
private String dataEmprestimo;



    //////////////////////////////////////////////
    //              GETs e SETs                 //
    //////////////////////////////////////////////

    public int getNumID_Objeto_Arte() {
        return numID_Objeto_Arte;
    }
    
    public void setNumID_Objeto_Arte(int numID_Objeto_Arte) {
        this.numID_Objeto_Arte = numID_Objeto_Arte;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }
    
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }


}
