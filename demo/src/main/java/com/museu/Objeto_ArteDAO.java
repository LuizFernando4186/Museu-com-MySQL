package com.museu;

public class Objeto_ArteDAO {


private int numId;
private int ano;
private String periodo;
private String titulo;
private String pais_CulturaDeOrigem;
private String descricao;
private String nome_Artista;
private double custo;

public Objeto_ArteDAO(){}

public Objeto_ArteDAO(int numId, int ano, String periodo, String titulo, String pais_CulturaDeOrigem, String descricao, String nome_Artista, double custo){
 this.numId = numId;
 this.ano = ano;
 this.periodo = periodo;
 this.titulo = titulo;
 this.pais_CulturaDeOrigem = pais_CulturaDeOrigem;
 this.descricao = descricao;
 this.nome_Artista = nome_Artista;
 this.custo = custo;
}


    //////////////////////////////////////////////
    //              GETs e SETs                 //
    //////////////////////////////////////////////

    public int getNumId() {
      return numId;
    }
    
    public void setNumId(int numId) {
      this.numId = numId;
    }

    public int getAno() {
      return ano;
    }
    
    public void setAno(int ano) {
      this.ano = ano;
    }
    public double getCusto() {
      return custo;
  }
  
  public void setCusto(double custo) {
      this.custo = custo;
  }

    public String getPeriodo() {
      return periodo;
    }
    
    public void setPeriodo(String periodo) {
      this.periodo = periodo;
    }

    public String getTitulo() {
      return titulo;
    }
    
    public void setTitulo(String titulo) {
      this.titulo = titulo;
    }

    public String getPais_CulturaDeOrigem() {
      return pais_CulturaDeOrigem;
    }
    
    public void setPais_CulturaDeOrigem(String pais_CulturaDeOrigem) {
      this.pais_CulturaDeOrigem = pais_CulturaDeOrigem;
    }

    public String getDescricao() {
      return descricao;
    }
    
    public void setDescricao(String descricao) {
      this.descricao = descricao;
    }

    public String getNome_Artista() {
      return nome_Artista;
    }
    
    public void setNome_Artista(String nome_Artista) {
      this.nome_Artista = nome_Artista;
    }


}
