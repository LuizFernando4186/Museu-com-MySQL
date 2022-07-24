package com.museu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class GerenciamentoDeConsultas {  

//Criar uma conexao com o BD
Connection conn = null; 
PreparedStatement pstm = null;
RGraficos g1 = new RGraficos();


    
//Liste os objetos de arte comprados por tipo e por classe (emprestado ou proprio);
public void listaObjetosPorTipoClasse(int opcao){

if(opcao == 7){

for(opcao = 1; opcao < 7; opcao++){ 
   listaSQL(opcao);
}
} else {
   listaSQL(opcao);
} 


}

//Liste as colecoes com o maior numero de emprestimos por mes e por ano
public void listagemDeEmprestimoPorMesPorAno(int mes, int ano){

    //Pega a quantidade de dias dos meses, pq o sql nao imprime se nao tiver o valor exato
    Calendar cal = Calendar.getInstance();	
    cal.set(Calendar.MONTH,(mes-1));    

    ResultSet rs = null;
    String primeiro = ano+"-"+mes+"-00";
    String segundo = ano+"-"+mes+"-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    try {

    StringBuilder sql = new StringBuilder("select maiorNumeroColecaoEmprestimo("+"'"+primeiro.toString()+"',"+"'"+segundo.toString()+"') as resultado;");
    
     //Primeiro faco a conexao com o BD
     conn = Conexao.conectaBD();

     //preparando para executar a query
     pstm = (PreparedStatement) conn.prepareStatement(sql.toString()); 

     rs = pstm.executeQuery();

    System.out.println("\n > COLECAO COM MAIOR NUMERO DE EMPRESTIMO: "+mes+"/"+ano+"\n");
    if(rs.next()) System.out.println(rs.getString("resultado"));

    
    System.out.println("---------------------------------------------------------");

    
    
    fecharConexoesComRset(conn, pstm, rs);


    } catch (Exception e){ 
    e.printStackTrace();
    } 
    

    }


//Faca um controle (listagem) da compra de objetos de arte por mes e por ano do museu;
public void listagemDeCompraPorMesPorAno(int mes, int ano){

    //Pega a quantidade de dias dos meses, pq o sql nao imprime se nao tiver o valor exato
    Calendar cal = Calendar.getInstance();	
    cal.set(Calendar.MONTH,(mes-1));    

    ResultSet rs = null;
    String primeiro = ano+"-"+mes+"-00";
    String segundo = ano+"-"+mes+"-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    try {

    StringBuilder sql = new StringBuilder("select * from objeto_arte o ");
    sql.append("inner join permanentes p on o.numId = p.numID_Objeto_Arte ");
    sql.append("where p.dataAquisicao between " + "'"+primeiro.toString()+"'" +" and "+ "'"+segundo.toString()+"';");
    

     //Primeiro faco a conexao com o BD
     conn = Conexao.conectaBD();

     //preparando para executar a query
     pstm = (PreparedStatement) conn.prepareStatement(sql.toString()); 

     rs = pstm.executeQuery();

     System.out.println("\n > LISTAGEM DE COMPRA: "+mes+"/"+ano+"\n");
    
     while(rs.next()){

        System.out.println(
            "id: " + rs.getInt("o.numId")+ "\n" +
            "dataAquisicao: " + rs.getString("p.dataAquisicao")+ "\n" +
            "titulo: " + rs.getString("o.titulo")+ "\n" +
            "Artista: " + rs.getString("o.nome_Artista")+ "\n" +
            "custo: " + rs.getDouble("o.custo")
       );
       System.out.println("---------------------------------------------------------");

    
    }

    //Para objetos emprestados
    StringBuilder sql1 = new StringBuilder("select * from objeto_arte o ");
    sql1.append("inner join emprestados p on o.numId = p.numID_Objeto_Arte ");
    sql1.append("where p.dataEmprestimo between " + "'"+primeiro.toString()+"'" +" and "+ "'"+segundo.toString()+"';");

    pstm = (PreparedStatement) conn.prepareStatement(sql1.toString()); 

    rs = pstm.executeQuery();

    System.out.println("\n > LISTAGEM DE OBJETOS EMPRESTADOS: "+mes+"/"+ano+"\n");

    while(rs.next()){

    
       System.out.println(
           "id: " + rs.getInt("o.numId")+ "\n" +
           "dataEmprestimo: " + rs.getString("p.dataEmprestimo")+ "\n" +
           "titulo: " + rs.getString("o.titulo")+ "\n" +
           "Artista: " + rs.getString("o.nome_Artista")+ "\n" +
           "custo: " + rs.getDouble("o.custo") 
      );
      System.out.println("---------------------------------------------------------");

   
   }

    
   fecharConexoesComRset(conn, pstm, rs);


    } catch (Exception e){ 
    e.printStackTrace();
    } 


}

//Faca uma curva desses gastos;
public void curvaGastos(){

    List<Double> custos = new ArrayList<>();
    List<String> datas = new ArrayList<>();

     
     //Classe que recupera os dados do BD
     ResultSet rset = null;

     try {
        StringBuilder sql = new StringBuilder("select * from objeto_arte o ");
        sql.append("inner join permanentes p on o.numId = p.numID_Objeto_Arte ");
        sql.append("UNION ");
        sql.append("select * from objeto_arte o2 ");
        sql.append("inner join emprestados e on o2.numId = e.numID_Objeto_Arte ");
        sql.append("order by dataAquisicao;");
              
        //Primeiro faco a conexao com o BD
        conn = Conexao.conectaBD();
        pstm = (PreparedStatement) conn.prepareStatement(sql.toString());
        rset = pstm.executeQuery();
        
        while(rset.next()){ 

        custos.add(rset.getDouble("custo"));
        datas.add(rset.getString("dataAquisicao"));
      
        }

        //Converte para array, pq, no objeto do R nao usa Listas
        String[] arrayDatas = datas.toArray(new String[0]);

        double[] arrayCusto = new double[custos.size()];
        for(int i = 0; i < custos.size(); i++){
        arrayCusto[i] = custos.get(i);
        }

        g1.plotCusto(arrayDatas, arrayCusto);


        fecharConexoesComRset(conn, pstm, rset);


        } catch (Exception e){ 
            e.printStackTrace();
        } 
            
        

}

//Faca um controle (listagem) da quantidade de objetos emprestados por colecao, por mes e por ano;
public void listagemQuantEmprestados(int mes, int ano, String colecao){

    //Pega a quantidade de dias dos meses, pq o sql nao imprime se nao tiver o valor exato
    Calendar cal = Calendar.getInstance();	
    cal.set(Calendar.MONTH,(mes-1));    

    ResultSet rs = null;
    String primeiro = ano+"-"+mes+"-00";
    String segundo = ano+"-"+mes+"-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    try {

        StringBuilder sql = new StringBuilder("select count(o.numId) from objeto_arte o ");
        sql.append("inner join "+colecao+" p on p.numID_Objeto_Arte = o.numId inner join emprestados e2 ");
        sql.append("on p.numID_Objeto_Arte = e2.numID_Objeto_Arte ");
        sql.append("where e2.dataEmprestimo between " + "'"+primeiro.toString()+"'" +" and "+ "'"+segundo.toString()+"';");
        
      
         //Primeiro faco a conexao com o BD
         conn = Conexao.conectaBD();
    
         //preparando para executar a query
         pstm = (PreparedStatement) conn.prepareStatement(sql.toString()); 
    
         rs = pstm.executeQuery();
    
         System.out.println("\n > QUANTIDADE DE OBJETOS EMPRESTADOS: "+mes+"/"+ano+"\n");
         if(rs.next() == false){
    
            System.out.println("\nNESTA COLECAO, MES OU ANO NAO POSSUI REGISTROS\n");
         }

         int quantidade = rs.getInt("count(o.numId)");

         System.out.println(" > " +quantidade);
        
         fecharConexoesComRset(conn, pstm, rs);

    
        } 
        catch (Exception e){ 
        e.printStackTrace();
        }



}

//Faca uma curva com os totais por colecao e por museu;
public void curvaTotaisPorColecaoPorMuseu(){

    //Classe que recupera os dados do BD
    ResultSet rset = null;
    int[] colecao = new int[3];

    try {
       StringBuilder sql = new StringBuilder("select 'pintura', count(o.numId) from objeto_arte o inner join pintura p on p.numID_Objeto_Arte = o.numId ");
       sql.append("UNION ");
       sql.append("select 'outros', count(o.numId) from objeto_arte o inner join outros o2 on o2.numID_Objeto_Arte = o.numId ");
       sql.append("UNION ");
       sql.append("select 'escultura', count(o.numId) from objeto_arte o inner join escultura e on e.numID_Objeto_Arte = o.numId;");
             
       //Primeiro faco a conexao com o BD
       conn = Conexao.conectaBD();
       pstm = (PreparedStatement) conn.prepareStatement(sql.toString());
       rset = pstm.executeQuery();
       int i = 0;
       
       while(rset.next()){

        colecao[i] = rset.getInt("count(o.numId)");
        i++;

       }

       g1.plotColecao(colecao);


       fecharConexoesComRset(conn, pstm, rset);
     
       }

        catch (Exception e){ 
        e.printStackTrace();
       } 

   
}


public List<Objeto_ArteDAO> createSQL(StringBuilder sql){


    List<Objeto_ArteDAO> l = new ArrayList<>();
    ResultSet rs = null;

    try {

     //Primeiro faco a conexao com o BD
     conn = Conexao.conectaBD();

     //preparando para executar a query
     pstm = (PreparedStatement) conn.prepareStatement(sql.toString()); 

     rs = pstm.executeQuery();

     while(rs.next()){

     Objeto_ArteDAO o = new Objeto_ArteDAO(rs.getInt("numId"), rs.getInt("ano"), rs.getString("periodo"), rs.getString("titulo"), rs.getString("pais_CulturaDeOrigem"), rs.getString("descricao"), rs.getString("nome_Artista"), rs.getDouble("custo"));

     l.add(o);
    }
   
    fecharConexoesComRset(conn, pstm, rs);


    } catch (Exception e){ 
    e.printStackTrace();
    } 

    return l;
}

public void listaSQL(int opcao){

    switch(opcao){
        
        
        case 1: 
        StringBuilder sql = new StringBuilder("select * from objeto_arte o ");
        sql.append("inner join pintura p on p.numID_Objeto_Arte = o.numId inner join permanentes p2 on p.numID_Objeto_Arte = p2.numID_Objeto_Arte;");
        List<Objeto_ArteDAO> lista = createSQL(sql);
        System.out.println("\n > PINTURAS PERMANENTES:\n");
        for(int i = 0; i < lista.size(); i++){ 
        System.out.println(
        "id: " + lista.get(i).getNumId()+ "\n" +
        "ano: " + lista.get(i).getAno()+ "\n" +
        "periodo: " + lista.get(i).getPeriodo()+ "\n" +
        "titulo: " + lista.get(i).getTitulo()+ "\n" +
        "pais: " + lista.get(i).getPais_CulturaDeOrigem()+ "\n" +
        "descricao: " + lista.get(i).getDescricao()+ "\n" +
        "Artista: " + lista.get(i).getNome_Artista()+ "\n" +
        "custo: " + lista.get(i).getCusto() 
        );
        System.out.println("---------------------------------------------------------");


        } break;

        case 2:
        StringBuilder sql1 = new StringBuilder("select * from objeto_arte o ");
        sql1.append("inner join pintura p on p.numID_Objeto_Arte = o.numId inner join emprestados e on p.numID_Objeto_Arte = e.numID_Objeto_Arte;");
        List<Objeto_ArteDAO> lista1 = createSQL(sql1);
        System.out.println("\n > PINTURAS EMPRESTADAS:\n");
        for(int i = 0; i < lista1.size(); i++){ 
        System.out.println(
        "id: " + lista1.get(i).getNumId()+ "\n" +
        "ano: " + lista1.get(i).getAno()+ "\n" +
        "periodo: " + lista1.get(i).getPeriodo()+ "\n" +
        "titulo: " + lista1.get(i).getTitulo()+ "\n" +
        "pais: " + lista1.get(i).getPais_CulturaDeOrigem()+ "\n" +
        "descricao: " + lista1.get(i).getDescricao()+ "\n" +
        "Artista: " + lista1.get(i).getNome_Artista()+ "\n" +
        "custo: " + lista1.get(i).getCusto()
        );
        System.out.println("---------------------------------------------------------");


        } break;

        case 3:
        StringBuilder sql2 = new StringBuilder("select * from objeto_arte o ");
        sql2.append("inner join escultura e on e.numID_Objeto_Arte = o.numId inner join permanentes p2 on e.numID_Objeto_Arte = p2.numID_Objeto_Arte;");
        List<Objeto_ArteDAO> lista2 = createSQL(sql2);
        System.out.println("\n > ESCULTURAS PERMANENTES:\n");
        for(int i = 0; i < lista2.size(); i++){ 
        System.out.println(
        "id: " + lista2.get(i).getNumId()+ "\n" +
        "ano: " + lista2.get(i).getAno()+ "\n" +
        "periodo: " + lista2.get(i).getPeriodo()+ "\n" +
        "titulo: " + lista2.get(i).getTitulo()+ "\n" +
        "pais: " + lista2.get(i).getPais_CulturaDeOrigem()+ "\n" +
        "descricao: " + lista2.get(i).getDescricao()+ "\n" +
        "Artista: " + lista2.get(i).getNome_Artista()+ "\n" +
        "custo: " + lista2.get(i).getCusto() 
        );
        System.out.println("---------------------------------------------------------");


        } break;

        case 4:
        StringBuilder sql3 = new StringBuilder("select * from objeto_arte o ");
        sql3.append("inner join escultura e on e.numID_Objeto_Arte = o.numId inner join emprestados e2 on e.numID_Objeto_Arte = e2.numID_Objeto_Arte;");
        List<Objeto_ArteDAO> lista3 = createSQL(sql3);
        System.out.println("\n > ESCULTURAS EMPRESTADAS:\n");
        for(int i = 0; i < lista3.size(); i++){ 
        System.out.println(
        "id: " + lista3.get(i).getNumId()+ "\n" +
        "ano: " + lista3.get(i).getAno()+ "\n" +
        "periodo: " + lista3.get(i).getPeriodo()+ "\n" +
        "titulo: " + lista3.get(i).getTitulo()+ "\n" +
        "pais: " + lista3.get(i).getPais_CulturaDeOrigem()+ "\n" +
        "descricao: " + lista3.get(i).getDescricao()+ "\n" +
        "Artista: " + lista3.get(i).getNome_Artista()+ "\n" +
        "custo: " + lista3.get(i).getCusto()
        );
        System.out.println("---------------------------------------------------------");


        } break;

        case 5:
        StringBuilder sql4 = new StringBuilder("select * from objeto_arte o ");
        sql4.append("inner join outros o2 on o2.numID_Objeto_Arte = o.numId inner join permanentes p2 on o2.numID_Objeto_Arte = p2.numID_Objeto_Arte;");
        List<Objeto_ArteDAO> lista4 = createSQL(sql4);
        System.out.println("\n > OUTROS PERMANENTES:\n");
        for(int i = 0; i < lista4.size(); i++){ 
        System.out.println(
        "id: " + lista4.get(i).getNumId()+ "\n" +
        "ano: " + lista4.get(i).getAno()+ "\n" +
        "periodo: " + lista4.get(i).getPeriodo()+ "\n" +
        "titulo: " + lista4.get(i).getTitulo()+ "\n" +
        "pais: " + lista4.get(i).getPais_CulturaDeOrigem()+ "\n" +
        "descricao: " + lista4.get(i).getDescricao()+ "\n" +
        "Artista: " + lista4.get(i).getNome_Artista()+ "\n" +
        "custo: " + lista4.get(i).getCusto()
        );
        System.out.println("---------------------------------------------------------");


        } break;

        case 6:
        StringBuilder sql5 = new StringBuilder("select * from objeto_arte o ");
        sql5.append("inner join outros o2 on o2.numID_Objeto_Arte = o.numId inner join emprestados e2 on o2.numID_Objeto_Arte = e2.numID_Objeto_Arte;");
        List<Objeto_ArteDAO> lista5 = createSQL(sql5);
        System.out.println("\n > OUTROS EMPRESTADOS:\n");
        for(int i = 0; i < lista5.size(); i++){ 
        System.out.println(
        "id: " + lista5.get(i).getNumId()+ "\n" +
        "ano: " + lista5.get(i).getAno()+ "\n" +
        "periodo: " + lista5.get(i).getPeriodo()+ "\n" +
        "titulo: " + lista5.get(i).getTitulo()+ "\n" +
        "pais: " + lista5.get(i).getPais_CulturaDeOrigem()+ "\n" +
        "descricao: " + lista5.get(i).getDescricao()+ "\n" +
        "Artista: " + lista5.get(i).getNome_Artista()+ "\n" +
        "custo: " + lista5.get(i).getCusto() 
        );
        System.out.println("---------------------------------------------------------");


        } break;



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
