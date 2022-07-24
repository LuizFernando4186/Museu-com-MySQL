package com.museu;


public class ColecaoDAO {
    
private String nome;
private String endereco;
private String pessoaContato;
private String descricao;
private String tipo;
private int numID_Objeto_Arte_Emprestados;



    //////////////////////////////////////////////
    //              GETs e SETs                 //
    //////////////////////////////////////////////

    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPessoaContato() {
        return pessoaContato;
    }
    
    public void setPessoaContato(String pessoaContato) {
        this.pessoaContato = pessoaContato;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumID_Objeto_Arte_Emprestados() {
        return numID_Objeto_Arte_Emprestados;
    }
    
    public void setNumID_Objeto_Arte_Emprestados(int numID_Objeto_Arte_Emprestados) {
        this.numID_Objeto_Arte_Emprestados = numID_Objeto_Arte_Emprestados;
    }

}
