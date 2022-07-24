package com.museu;

import java.sql.*;
import java.util.*;

class GerenciamentoCRUD {

//Usados em todos os metodos
Connection conn = null; 
PreparedStatement pstm = null;




/*
 * DAO: OBJETO DE ACESSO A DADOS
 * 
 * CRUD
 * C: CREATE - INSERT
 * R: READ - SELECT 
 * U: UPDATE - UPDATE
 * D: DELETE - DELETE
 */





//////////////////////////////////////////////
//                   Artista                //
//////////////////////////////////////////////


public void InsertArtista(ArtistaDAO artista){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Artista(nome, dataNascimento, periodo, dataMorte, descricao, paisDeOrigem, estiloPrincipal) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        //preparando para executar a query
        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setString(1, artista.getNome());
        ((PreparedStatement) pstm).setString(2, artista.getDataNascimento());
        ((PreparedStatement) pstm).setString(3, artista.getPeriodo());
        ((PreparedStatement) pstm).setString(4, artista.getDataMorte());
        ((PreparedStatement) pstm).setString(5, artista.getDescricao());
        ((PreparedStatement) pstm).setString(6, artista.getPaisDeOrigem());
        ((PreparedStatement) pstm).setString(7, artista.getEstiloPrincipal());

        //Executar a query
        pstm.execute();

    
    } catch (Exception e) { 
        e.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<ArtistaDAO> SelectArtistas(){
     String sql = "SELECT * FROM Artista";

     List<ArtistaDAO> artistas = new ArrayList<ArtistaDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
        ArtistaDAO artista = new ArtistaDAO();

        
        artista.setNome(rset.getString("nome"));
        artista.setDataNascimento(rset.getString("dataNascimento"));
        artista.setPeriodo(rset.getString("periodo"));
        artista.setDataMorte(rset.getString("dataMorte"));
        artista.setDescricao(rset.getString("descricao"));
        artista.setPaisDeOrigem(rset.getString("paisDeOrigem"));
        artista.setEstiloPrincipal(rset.getString("estiloPrincipal"));

        artistas.add(artista);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return artistas;

   }

   
   public void updateArtista(ArtistaDAO artista){
       String sql = "UPDATE Artista SET dataNascimento = ?, periodo = ?, dataMorte = ?, descricao = ?, paisDeOrigem = ?, estiloPrincipal = ? WHERE nome = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setString(1, artista.getDataNascimento());
        ((PreparedStatement) pstm).setString(2, artista.getPeriodo());
        ((PreparedStatement) pstm).setString(3, artista.getDataMorte());
        ((PreparedStatement) pstm).setString(4, artista.getDescricao());
        ((PreparedStatement) pstm).setString(5, artista.getPaisDeOrigem());
        ((PreparedStatement) pstm).setString(6, artista.getEstiloPrincipal());


        
        //Nome que deseja alterar
        ((PreparedStatement) pstm).setString(7, artista.getNome());

        pstm.execute();


       } catch (Exception e){ 
         e.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deleteArtista(String nome){ 

    String sql = "DELETE FROM Artista WHERE nome = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setString(1,nome);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}




//////////////////////////////////////////////
//              Escultura                   //
//////////////////////////////////////////////

public void InsertEscultura(EsculturaDAO escultura){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Escultura(numID_Objeto_Arte,estilo,peso,altura,material) VALUES (?, ?, ?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();


        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setInt(1, escultura.getNumID_Objeto_Arte());
        ((PreparedStatement) pstm).setString(2, escultura.getEstilo());
        ((PreparedStatement) pstm).setInt(3, escultura.getPeso());
        ((PreparedStatement) pstm).setDouble(4, escultura.getAltura());
        ((PreparedStatement) pstm).setString(5, escultura.getMaterial());

        //Executar a query
        pstm.execute();

    

    } catch (Exception e) { 
        e.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<EsculturaDAO> SelectEsculturas(){
     String sql = "SELECT * FROM Escultura";

     List<EsculturaDAO> esculturas = new ArrayList<EsculturaDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
        EsculturaDAO escultura = new EsculturaDAO();

        //Recuperar o numID_Objeto_Arte
        escultura.setNumID_Objeto_Arte(rset.getInt("numID_Objeto_Arte"));
        //Recuperar o estilo
        escultura.setEstilo(rset.getString("estilo"));
        //Recuperar o peso
        escultura.setPeso(rset.getInt("peso"));
        //Recuperar o altura
        escultura.setAltura(rset.getDouble("altura"));
        //Recuperar o material
        escultura.setMaterial(rset.getString("material"));
      
       



        esculturas.add(escultura);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return esculturas;

   }

   
   public void updateEscultura(EsculturaDAO escultura){
       String sql = "UPDATE Escultura SET estilo = ?, peso = ?, altura = ?, material = ?  WHERE numID_Objeto_Arte = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setString(1, escultura.getEstilo());
        ((PreparedStatement) pstm).setInt(2, escultura.getPeso());
        ((PreparedStatement) pstm).setDouble(3, escultura.getAltura());
        ((PreparedStatement) pstm).setString(4, escultura.getMaterial());

        //id que deseja alterar
        ((PreparedStatement) pstm).setInt(5, escultura.getNumID_Objeto_Arte());

        pstm.execute();


       } catch (Exception e){ 
         e.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deleteEscultura(int id){ 

    String sql = "DELETE FROM escultura WHERE numID_Objeto_Arte = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setInt(1,id);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}


//////////////////////////////////////////////
//              Exposicao                   //
//////////////////////////////////////////////

public void InsertExposicao(ExposicaoDAO exposicao){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Exposicao(nome, dataInicio, dataFim, numID_Objeto_Arte) VALUES (?, ?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();


        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setString(1, exposicao.getNome());
        ((PreparedStatement) pstm).setString(2, exposicao.getDataInicio());
        ((PreparedStatement) pstm).setString(3, exposicao.getDataFim());
        ((PreparedStatement) pstm).setInt(4, exposicao.getNumID_Objeto_Arte());
    


        //Executar a query
        pstm.execute();

    
    //System.out.println("Historico Inserido com Sucesso!");

    } catch (Exception e) { 
        e.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<ExposicaoDAO> SelectExposicao(){
     String sql = "SELECT * FROM Exposicao";

     List<ExposicaoDAO> exposicoes = new ArrayList<ExposicaoDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
        ExposicaoDAO exp = new ExposicaoDAO();

        //Recuperar o nome
        exp.setNome(rset.getString("nome"));
        //Recuperar o dataInicio
        exp.setDataInicio(rset.getString("dataInicio"));
        //Recuperar o dataFim
        exp.setDataFim(rset.getString("dataFim"));
        //Recuperar o numID_Objeto_Arte
        exp.setNumID_Objeto_Arte(rset.getInt("numID_Objeto_Arte"));
       



        exposicoes.add(exp);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return exposicoes;

   }

   
   public void updateExposicao(ExposicaoDAO exposicao){
       String sql = "UPDATE Exposicao SET dataInicio = ?, dataFim = ?, numID_Objeto_Arte = ? WHERE nome = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setString(1, exposicao.getDataInicio());
        ((PreparedStatement) pstm).setString(2, exposicao.getDataFim());
        ((PreparedStatement) pstm).setInt(3, exposicao.getNumID_Objeto_Arte());

       

        
        //nome que deseja alterar
        ((PreparedStatement) pstm).setString(4, exposicao.getNome());


        pstm.execute();


       } catch (Exception e){ 
         e.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deleteExposicao(String nome){ 

    String sql = "DELETE FROM Exposicao WHERE nome = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setString(1,nome);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}



//////////////////////////////////////////////
//               Emprestados                //
//////////////////////////////////////////////

public void InsertEmprestados(EmprestadosDAO e){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Emprestados(numID_Objeto_Arte,dataDevolucao,dataEmprestimo) VALUES (?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setInt(1, e.getNumID_Objeto_Arte());
        ((PreparedStatement) pstm).setString(2, e.getDataDevolucao());
        ((PreparedStatement) pstm).setString(3, e.getDataEmprestimo());

        //Executar a query
        pstm.execute();

    
    } catch (Exception erro) { 
        erro.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<EmprestadosDAO> SelectEmprestados(){
     String sql = "SELECT * FROM Emprestados";

     List<EmprestadosDAO> emp = new ArrayList<EmprestadosDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
            EmprestadosDAO e = new EmprestadosDAO();

        //Recuperar o numID
        e.setNumID_Objeto_Arte(rset.getInt("numID_Objeto_Arte"));
        //Recuperar o dataDevolucao
        e.setDataDevolucao(rset.getString("dataDevolucao"));
        //Recuperar o dataEmprestimo
        e.setDataEmprestimo(rset.getString("dataEmprestimo"));

        emp.add(e);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return emp;

   }

   
   public void updateEmprestados(EmprestadosDAO e){
       String sql = "UPDATE Emprestados SET dataDevolucao = ?, dataEmprestimo = ? WHERE numID_Objeto_Arte = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setString(1, e.getDataDevolucao());
        ((PreparedStatement) pstm).setString(2, e.getDataEmprestimo());
        
        //id que deseja alterar
        ((PreparedStatement) pstm).setInt(3, e.getNumID_Objeto_Arte());

        pstm.execute();


       } catch (Exception erro){ 
         erro.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deleteEmprestados (int id){ 

    String sql = "DELETE FROM Emprestados  WHERE numID_Objeto_Arte = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setInt(1,id);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}



//////////////////////////////////////////////
//              Colecao                     //
//////////////////////////////////////////////

public void InsertColecao(ColecaoDAO c){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Colecao(nome, endereco, pessoaContato, descricao, tipo, numID_Objeto_Arte_Emprestados) VALUES (?, ?, ?, ?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();


        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setString(1, c.getNome());
        ((PreparedStatement) pstm).setString(2, c.getEndereco());
        ((PreparedStatement) pstm).setString(3, c.getPessoaContato());
        ((PreparedStatement) pstm).setString(4, c.getDescricao());
        ((PreparedStatement) pstm).setString(5, c.getTipo());
        ((PreparedStatement) pstm).setInt(6, c.getNumID_Objeto_Arte_Emprestados());

        //Executar a query
        pstm.execute();

    
    } catch (Exception e) { 
        e.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<ColecaoDAO> SelectColecao(){
     String sql = "SELECT * FROM Colecao";

     List<ColecaoDAO> c = new ArrayList<ColecaoDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
        ColecaoDAO e = new ColecaoDAO();

        //Recuperar o nome
        e.setNome(rset.getString("nome"));
        //Recuperar o endereco
        e.setEndereco(rset.getString("endereco"));
        //Recuperar o pessoaContato
        e.setPessoaContato(rset.getString("pessoaContato"));
        //Recuperar o descricao
        e.setDescricao(rset.getString("descricao"));
        //Recuperar o tipo
        e.setTipo(rset.getString("tipo"));
        //Recuperar o numID
        e.setNumID_Objeto_Arte_Emprestados(rset.getInt("numID_Objeto_Arte_Emprestados"));

        c.add(e);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return c;

   }

   
   public void updateColecao(ColecaoDAO c){
       String sql = "UPDATE Colecao SET endereco = ?, pessoaContato = ?, descricao = ?, tipo = ?, numID_Objeto_Arte_Emprestados = ? WHERE nome = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setString(1, c.getEndereco());
        ((PreparedStatement) pstm).setString(2, c.getPessoaContato());
        ((PreparedStatement) pstm).setString(3, c.getDescricao());
        ((PreparedStatement) pstm).setString(4, c.getTipo());
        ((PreparedStatement) pstm).setInt(5, c.getNumID_Objeto_Arte_Emprestados());
        
        //codigo que deseja alterar
        ((PreparedStatement) pstm).setString(6, c.getNome());

        pstm.execute();


       } catch (Exception e){ 
         e.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deleteColecao(String nome){ 

    String sql = "DELETE FROM Colecao WHERE nome = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setString(1,nome);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}


//////////////////////////////////////////////
//                 Pintura                  //
//////////////////////////////////////////////

public void InsertPintura(PinturaDAO pintura){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Pintura(numID_Objeto_Arte, tipoTinta, suporte, estilo) VALUES (?, ?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setInt(1, pintura.getNumID_Objeto_Arte());
        ((PreparedStatement) pstm).setString(2, pintura.getTipoTinta());
        ((PreparedStatement) pstm).setString(3, pintura.getSuporte());
        ((PreparedStatement) pstm).setString(4, pintura.getEstilo());

        //Executar a query
        pstm.execute();

    
    } catch (Exception e) { 
        e.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<PinturaDAO> SelectPinturas(){
     String sql = "SELECT * FROM Pintura";

     List<PinturaDAO> pinturas = new ArrayList<PinturaDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
        PinturaDAO p = new PinturaDAO();

        //Recuperar o nome
        p.setNumID_Objeto_Arte(rset.getInt("numID_Objeto_Arte"));
        //Recuperar o tipoTinta
        p.setTipoTinta(rset.getString("tipoTinta"));
        //Recuperar o suporte
        p.setSuporte(rset.getString("suporte"));
        //Recuperar o estilo
        p.setEstilo(rset.getString("estilo"));
       

        pinturas.add(p);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return pinturas;

   }

   
   public void updatePintura(PinturaDAO pintura){
       String sql = "UPDATE Pintura SET tipoTinta = ?, suporte = ?, estilo = ? WHERE numID_Objeto_Arte = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setString(1, pintura.getTipoTinta());
        ((PreparedStatement) pstm).setString(2, pintura.getSuporte());
        ((PreparedStatement) pstm).setString(3, pintura.getEstilo());

        //idDoenca que deseja alterar
        ((PreparedStatement) pstm).setInt(4, pintura.getNumID_Objeto_Arte());
        

        pstm.execute();


       } catch (Exception e){ 
         e.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deletePintura(int id){ 

    String sql = "DELETE FROM Pintura WHERE numID_Objeto_Arte = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setInt(1,id);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}


//////////////////////////////////////////////
//               Objeto_Arte                //
//////////////////////////////////////////////

public void InsertObjetoArte(Objeto_ArteDAO obj){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Objeto_Arte(numId, ano, titulo, pais_CulturaDeOrigem, descricao, nome_Artista, custo,periodo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();


        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setInt(1, obj.getNumId());
        ((PreparedStatement) pstm).setInt(2, obj.getAno());
        ((PreparedStatement) pstm).setString(3, obj.getTitulo());
        ((PreparedStatement) pstm).setString(4, obj.getPais_CulturaDeOrigem());
        ((PreparedStatement) pstm).setString(5, obj.getDescricao());
        ((PreparedStatement) pstm).setString(6, obj.getNome_Artista());
        ((PreparedStatement) pstm).setDouble(7, obj.getCusto());
        ((PreparedStatement) pstm).setString(8, obj.getPeriodo());

        //Executar a query
        pstm.execute();

    
    } catch (Exception e) { 
        e.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<Objeto_ArteDAO> SelectObjeto_Arte(){
     String sql = "SELECT * FROM Objeto_Arte";

     List<Objeto_ArteDAO> objetos = new ArrayList<Objeto_ArteDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
            Objeto_ArteDAO o = new Objeto_ArteDAO();

        //Recuperar o numId
        o.setNumId(rset.getInt("numId"));
        //Recuperar o ano
        o.setAno(rset.getInt("ano"));
        //Recuperar o periodo
        o.setPeriodo(rset.getString("periodo"));
        //Recuperar o titulo
        o.setTitulo(rset.getString("titulo"));
        //Recuperar o pais_CulturaDeOrigem
        o.setPais_CulturaDeOrigem(rset.getString("pais_CulturaDeOrigem"));
        //Recuperar o descricao
        o.setDescricao(rset.getString("descricao"));
        //Recuperar o nome_Artista
        o.setNome_Artista(rset.getString("nome_Artista"));
        //Recuperar o custo
        o.setCusto(rset.getDouble("custo"));
     
      

        objetos.add(o);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return objetos;

   }

   
   public void updateObjeto_Arte(Objeto_ArteDAO obj){
       String sql = "UPDATE Objeto_Arte SET ano = ?, titulo = ?, pais_CulturaDeOrigem = ?, descricao = ?, nome_Artista = ? periodo = ? WHERE numId = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setInt(1, obj.getAno());
        ((PreparedStatement) pstm).setString(2, obj.getTitulo());
        ((PreparedStatement) pstm).setString(3, obj.getPais_CulturaDeOrigem());
        ((PreparedStatement) pstm).setString(4, obj.getDescricao());
        ((PreparedStatement) pstm).setString(5, obj.getNome_Artista());
        ((PreparedStatement) pstm).setDouble(6, obj.getCusto());
        ((PreparedStatement) pstm).setString(7, obj.getPeriodo());


        
        //idConsulta que deseja alterar
        ((PreparedStatement) pstm).setInt(8, obj.getNumId());

        pstm.execute();


       } catch (Exception e){ 
         e.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deleteObjeto_Arte(int id){ 

    String sql = "DELETE FROM Objeto_Arte WHERE numId = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setInt(1,id);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}



//////////////////////////////////////////////
//                Outros                    //
//////////////////////////////////////////////
public void InsertOutros(OutrosDAO outros){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Outros(numID_Objeto_Arte, estilo, tipo) VALUES (?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setInt(1, outros.getNumID_Objeto_Arte());
        ((PreparedStatement) pstm).setString(2, outros.getEstilo());
        ((PreparedStatement) pstm).setString(3, outros.getTipo());

        //Executar a query
        pstm.execute();

    
    } catch (Exception e) { 
        e.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<OutrosDAO> SelectOutros(){
     String sql = "SELECT * FROM Outros";

     List<OutrosDAO> agendas = new ArrayList<OutrosDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
        OutrosDAO a = new OutrosDAO();

        //Recuperar o numId
        a.setNumID_Objeto_Arte(rset.getInt("numID_Objeto_Arte"));
        //Recuperar o estilo
        a.setEstilo(rset.getString("estilo"));
        //Recuperar o tipo
        a.setTipo(rset.getString("tipo"));


        agendas.add(a);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return agendas;

   }

   
   public void updateOutros(OutrosDAO outros){
       String sql = "UPDATE Outros SET estilo = ?, tipo = ? WHERE numID_Objeto_Arte = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setString(1, outros.getEstilo());
        ((PreparedStatement) pstm).setString(2, outros.getTipo());
       
        
        //idAgenda que deseja alterar
        ((PreparedStatement) pstm).setInt(3, outros.getNumID_Objeto_Arte());

        pstm.execute();


       } catch (Exception e){ 
         e.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deleteOutros(int id){ 

    String sql = "DELETE FROM Outros WHERE numID_Objeto_Arte = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setInt(1,id);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}

//////////////////////////////////////////////
//                Permanentes               //
//////////////////////////////////////////////
public void InsertPermanentes(PermanentesDAO per){ 

    //Para inserir e usado o INSERT INTO, o ? separa cada um
    String sql = "INSERT INTO Permanentes(numID_Objeto_Arte, dataAquisicao, emExposicao) VALUES (?, ?, ?)";

    try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql); 

        //Adicionar os valores que sao esperados pela query
        ((PreparedStatement) pstm).setInt(1, per.getNumID_Objeto_Arte());
        ((PreparedStatement) pstm).setString(2, per.getDataAquisicao());
        ((PreparedStatement) pstm).setString(3, per.getEmExposicao());

        //Executar a query
        pstm.execute();

    
    } catch (Exception e) { 
        e.printStackTrace();
    } finally {

        fecharConexoes(conn, pstm);
    }


  }

  public List<PermanentesDAO> SelectPermanentes(){
     String sql = "SELECT * FROM Permanentes";

     List<PermanentesDAO> permanentes = new ArrayList<PermanentesDAO>();
     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();

        pstm = (PreparedStatement) conn.prepareStatement(sql);

        rset = pstm.executeQuery();
        
        //Enquanto estiver dados ele percorre e recupera as inf.
        //Alimentar a listagem para imprimir
        while(rset.next()){ 
        PermanentesDAO p = new PermanentesDAO();

        //Recuperar o numId
        p.setNumID_Objeto_Arte(rset.getInt("numID_Objeto_Arte"));
        //Recuperar o dataAquisicao
        p.setDataAquisicao(rset.getString("dataAquisicao"));
        //Recuperar o emExposicao
        p.setEmExposicao(rset.getString("emExposicao"));
     

        permanentes.add(p);
        }

        } catch (Exception e){ 
            e.printStackTrace();
        } finally {
            fecharConexoesComRset(conn, pstm, rset);
        }

        return permanentes;

   }

   
   public void updatePermanentes(PermanentesDAO per){
       String sql = "UPDATE Permanentes SET dataAquisicao = ?, emExposicao = ? WHERE numID_Objeto_Arte = ?";

       try { 
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        //Adicionar os valores para atualizar
        ((PreparedStatement) pstm).setString(1, per.getDataAquisicao());
        ((PreparedStatement) pstm).setString(2, per.getEmExposicao());
        
        //id que deseja alterar
        ((PreparedStatement) pstm).setInt(3, per.getNumID_Objeto_Arte());

        pstm.execute();


       } catch (Exception e){ 
         e.printStackTrace();
       } finally {

        fecharConexoes(conn, pstm);
    }

}

public void deletePermanentes(int id){ 

    String sql = "DELETE FROM Permanentes WHERE numID_Objeto_Arte = ?"; 

    try{
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql);

        pstm.setInt(1,id);

        pstm.execute();

    } catch (Exception e){ 
        e.printStackTrace();
      } finally {

      fecharConexoes(conn, pstm);
   }

}




//////////////////////////////////////////////
//              METODOS AUXILIARES          //
//////////////////////////////////////////////


public void fecharConexoes(Connection conn, PreparedStatement pstm){
 //Fechar as conexoes
 try { 

    if(pstm != null){
        pstm.close();
    }
    if(conn != null){ 
        conn.close();
    }

} catch (Exception e){
    e.printStackTrace();
}
}

public void fecharConexoesComRset(Connection conn, PreparedStatement pstm, ResultSet rset){
    //Fechar as conexoes
    try { 

        if(rset != null){
            rset.close();
        }
        if(pstm != null){
            pstm.close();
        }
        if(conn != null){ 
            conn.close();
        }

    } catch (Exception e){
        e.printStackTrace();
    }
   }




}
