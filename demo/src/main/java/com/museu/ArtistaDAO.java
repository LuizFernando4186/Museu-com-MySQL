package com.museu;


public class ArtistaDAO {
    
    private String nome;
    private String dataNascimento;
    private String periodo;
    private String dataMorte;
    private String descricao;
    private String paisDeOrigem;
    private String estiloPrincipal;


    //////////////////////////////////////////////
    //              GETs e SETs                 //
    //////////////////////////////////////////////


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(String dataMorte) {
        this.dataMorte = dataMorte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPaisDeOrigem() {
        return paisDeOrigem;
    }

    public void setPaisDeOrigem(String paisDeOrigem) {
        this.paisDeOrigem = paisDeOrigem;
    }

    public String getEstiloPrincipal() {
        return estiloPrincipal;
    }

    public void setEstiloPrincipal(String estiloPrincipal) {
        this.estiloPrincipal = estiloPrincipal;
    }





}
