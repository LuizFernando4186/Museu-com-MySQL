package com.museu;

import java.sql.*;
import java.util.*;


public class Main {

    private static GerenciamentoDeConsultas dao = new GerenciamentoDeConsultas();
    private static GerenciamentoCRUD crud = new GerenciamentoCRUD();
    private static Scanner input = new Scanner(System.in);

    public static void main( String[] args ) throws Exception{

    //Verificar a conexao com o BD
    Connection con = Conexao.conectaBD();
  
    if(con != null){ 
      System.out.println("\nConexao obetida com sucesso com o" + " ID: " + con + "\n\n");
      con.close();//Fecha conexao
    } else { 
      System.out.println("\nConexao nao feita!\n");

    }

    
    System.out.println("---------- BEM VINDO AO SISTEMA DO MUSEU -----------");


    try{

        
    int opcao = 0;
    while(opcao != 11){
 
    exibirMenu();
    opcao = input.nextInt();
    switch(opcao){
    case 1: opc1(); continue;
    case 2: opc2(); continue;
    case 3: opc3(); continue;
    case 4: opc4(); continue;
    case 5: opc5(); continue;
    case 6: opc6(); continue;
    case 7: opc7(); continue;
    case 8: opc8(); continue;
    case 9: opc9(); continue;
    case 10: opc10(); continue;
    
    }
    }
    
    }
 
    catch(Exception e){ 
    e.printStackTrace();
    }
 
    System.out.println("\nFIMMM!!!\n");
    }




    private static void exibirMenu(){
 
    System.out.println("\n--------- GESTAO DO MUSEU ---------");
    System.out.println("(1) - INSERIR");
    System.out.println("(2) - CONSULTAR");
    System.out.println("(3) - ALTERAR");
    System.out.println("(4) - DELETAR");
    System.out.println("(5) - LISTAR OBJETOS POR TIPO E CLASSE");
    System.out.println("(6) - COLECAO COM MAIOR NUMERO DE EMPRESTIMO");
    System.out.println("(7) - LISTAR COMPRA");
    System.out.println("(8) - GRAFICO DE GASTOS");
    System.out.println("(9) - LISTAR QUANTIDADE DE OBJETOS");
    System.out.println("(10) - QUANTIDADE POR COLECAO");
    System.out.println("(11) - SAIR");
    System.out.println("\nDigite a opcao desejada: ");
    }





    private static void opc1(){
    System.out.println("(1) - ADICIONAR ARTISTA");
    System.out.println("(2) - ADICIONAR COLECAO");    
    System.out.println("(3) - ADICIONAR EXPOSICAO");
    System.out.println("(4) - ADICIONAR OBJETO DE ARTE");
    System.out.println("(5) - PARA VOLTAR AO MENU");

    int id;
    int opcao = 0;
    opcao = input.nextInt();
    while(opcao != 5){

    if(opcao == 1){
    ArtistaDAO artista = new ArtistaDAO();
    System.out.println("Digite o Nome: ");
    artista.setNome(CapturaNextLine());
    System.out.println("Digite a Data de Nascimento (ano-mes-dia): ");
    artista.setDataNascimento(CapturaNextLine());
    System.out.println("Digite o Periodo: ");
    artista.setPeriodo(CapturaNextLine());
    System.out.println("Autor Esta Morto (SIM ou NAO): ");
    String morto = CapturaNextLine();
    if(morto.equals("Sim") || morto.equals("sim") || morto.equals("SIM")){
    System.out.println("Digite a Data da Morte (ano-mes-dia): ");
    artista.setDataMorte(CapturaNextLine());
    } else {
    artista.setDataMorte(null);
    }
    
    System.out.println("Digite a Descricao: ");
    artista.setDescricao(CapturaNextLine());
    System.out.println("Digite o Pais de Origem: ");
    artista.setPaisDeOrigem(CapturaNextLine());
    System.out.println("Digite o Estilo Principal: ");
    artista.setEstiloPrincipal(CapturaNextLine());

    crud.InsertArtista(artista);
    break;
    }

    if(opcao == 2){
    ColecaoDAO colecao = new ColecaoDAO();
    System.out.println("Digite o Nome: ");
    colecao.setNome(CapturaNextLine());
    System.out.println("Digite o Endereco: ");
    colecao.setEndereco(CapturaNextLine());
    System.out.println("Digite a Pessoa de Contato: ");
    colecao.setPessoaContato(CapturaNextLine());
    System.out.println("Digite a Descricao: ");
    colecao.setDescricao(CapturaNextLine());
    System.out.println("Digite o Tipo: ");
    colecao.setTipo(CapturaNextLine());
    System.out.println("Digite o Id: ");
    colecao.setNumID_Objeto_Arte_Emprestados(input.nextInt());

    crud.InsertColecao(colecao);
    break;

    }

    if(opcao == 3){
    ExposicaoDAO exposicao = new ExposicaoDAO();
    System.out.println("Digite o Nome: ");
    exposicao.setNome(CapturaNextLine());
    System.out.println("Digite a Data de Inicio: ");
    exposicao.setDataInicio(CapturaNextLine());
    System.out.println("Digite a Data de Fim: ");
    exposicao.setDataFim(CapturaNextLine());
    System.out.println("Digite o Id: ");
    exposicao.setNumID_Objeto_Arte(input.nextInt());

    crud.InsertExposicao(exposicao);
    break;
    }

    if(opcao == 4){
    Objeto_ArteDAO objeto = new Objeto_ArteDAO();

    System.out.println("Digite o Id: ");
    id = input.nextInt();
    objeto.setNumId(id);
    System.out.println("Digite o Ano: ");
    objeto.setAno(input.nextInt());
    System.out.println("Digite o Titulo: ");
    objeto.setTitulo(CapturaNextLine());
    System.out.println("Digite o Pais de Origem: ");
    objeto.setPais_CulturaDeOrigem(CapturaNextLine());
    System.out.println("Digite o Descricao: ");
    objeto.setDescricao(CapturaNextLine());
    System.out.println("Digite o Artista: ");
    objeto.setNome_Artista(CapturaNextLine());
    System.out.println("Digite o Custo (ex: 100,57): ");
    objeto.setCusto(input.nextDouble());
    System.out.println("Digite o Periodo: ");
    objeto.setPeriodo(CapturaNextLine());

    crud.InsertObjetoArte(objeto);

    System.out.println("\n(1) - EMPRESTADO");    
    System.out.println("(2) - PERMANENTE");
    opcao = input.nextInt();
    if(opcao == 1){ 
    EmprestadosDAO emprestado = new EmprestadosDAO();

    emprestado.setNumID_Objeto_Arte(id);
    System.out.println("Digite a Data de Devolucao (ano-mes-dia): ");
    emprestado.setDataDevolucao(CapturaNextLine());
    System.out.println("Digite a Data do Emprestimo (ano-mes-dia): ");
    emprestado.setDataEmprestimo(CapturaNextLine());
    insertTipoDoObjeto(id);
    crud.InsertEmprestados(emprestado);
    }
    if(opcao == 2){
    PermanentesDAO permanentes = new PermanentesDAO();
    permanentes.setNumID_Objeto_Arte(id);
    System.out.println("Digite a Data do Aquisicao (ano-mes-dia): ");
    permanentes.setDataAquisicao(CapturaNextLine());
    System.out.println("Digite SIM OU NAO se estiver em Exposicao: ");
    permanentes.setEmExposicao(CapturaNextLine());
    insertTipoDoObjeto(id);
    crud.InsertPermanentes(permanentes);
    }

    break;

    }

    }

    }

    private static void opc2(){
      System.out.println("(1) - LISTAGEM ARTISTA");
      System.out.println("(2) - LISTAGEM COLECAO");
      System.out.println("(3) - LISTAGEM EMPRESTADOS");
      System.out.println("(4) - LISTAGEM PERMANENTES");
      System.out.println("(5) - LISTAGEM EXPOSICAO");
      System.out.println("(6) - LISTAGEM PINTURAS");
      System.out.println("(7) - LISTAGEM ESCULTURAS");
      System.out.println("(8) - LISTAGEM OUTROS");
      System.out.println("(9) - LISTAGEM DE TODOS OS OBJETOS DE ARTE");
      System.out.println("(10) - PARA VOLTAR AO MENU");

      int opcao = 0;
      opcao = input.nextInt();

      switch(opcao){
        case 1:
        for(ArtistaDAO m : crud.SelectArtistas()) {
          System.out.println(
            "Nome: " + m.getNome() + "\n" +
            "Dt Nascimento: " + m.getDataNascimento() + "\n" +
            "Periodo: " + m.getPeriodo() + "\n" +
            "Dt Morte: " + m.getDataMorte() + "\n" +
            "Descricao: " + m.getDescricao() + "\n" + 
            "Pais: " + m.getPaisDeOrigem() + "\n" +
            "Estilo: " + m.getEstiloPrincipal() 
            );
            System.out.println("---------------------------------------------------------");
        }
        
        break;

        case 2:
        for(ColecaoDAO m : crud.SelectColecao()) {
          System.out.println(
            "Nome: " + m.getNome() + "\n" +
            "Endereco: " + m.getEndereco() + "\n" +
            "Pessoa Contato: " + m.getPessoaContato() + "\n" +
            "Descricao: " + m.getDescricao() + "\n" +
            "Tipo: " + m.getTipo() + "\n" + 
            "Id: " + m.getNumID_Objeto_Arte_Emprestados() 
            );
            System.out.println("---------------------------------------------------------");
        }

        break;

        case 3:
        for(EmprestadosDAO m : crud.SelectEmprestados()) {
          System.out.println(
            "Id: " + m.getNumID_Objeto_Arte() + "\n" +
            "Data de Devolucao: " + m.getDataDevolucao() + "\n" +
            "Data de Emprestimo: " + m.getDataEmprestimo() 
            );
            System.out.println("---------------------------------------------------------");
        }

        break;

        case 4:
        for(PermanentesDAO m : crud.SelectPermanentes()) {
          System.out.println(
            "Id: " + m.getNumID_Objeto_Arte() + "\n" +
            "Em Exposicao: " + m.getEmExposicao() + "\n" +
            "Data de Aquisicao: " + m.getDataAquisicao() 
            );
            System.out.println("---------------------------------------------------------");
        }

        break;

        case 5:
        for(ExposicaoDAO m : crud.SelectExposicao()) {
          System.out.println(
            "Nome: " + m.getNome() + "\n" +
            "Dt Inicio: " + m.getDataInicio() + "\n" +
            "Dt Fim: " + m.getDataFim() + "\n" +
            "Id: " + m.getNumID_Objeto_Arte() 
            );
            System.out.println("---------------------------------------------------------");
        }

        break;

        case 6:
        for(PinturaDAO m : crud.SelectPinturas()) {
          System.out.println(
            "Id: " + m.getNumID_Objeto_Arte() + "\n" +
            "Tipo de Tinta: " + m.getTipoTinta() + "\n" +
            "Suporte: " + m.getSuporte() + "\n" +
            "Estilo: " + m.getEstilo() 
            );
            System.out.println("---------------------------------------------------------");
        }

        break;

        case 7:
        for(EsculturaDAO m : crud.SelectEsculturas()) {
          System.out.println(
            "Id: " + m.getNumID_Objeto_Arte() + "\n" +
            "Estilo: " + m.getEstilo() + "\n" +
            "Peso: " + m.getPeso() + "\n" +
            "Altura: " + m.getAltura() + "\n" +
            "Material: " + m.getMaterial() 
            );
            System.out.println("---------------------------------------------------------");
        }

        break;

        case 8:
        for(OutrosDAO m : crud.SelectOutros()) {
          System.out.println(
            "Id: " + m.getNumID_Objeto_Arte() + "\n" +
            "Estilo: " + m.getEstilo() + "\n" +
            "Tipo: " + m.getTipo() 
            );
            System.out.println("---------------------------------------------------------");
        }

        break;

        case 9:
        for(Objeto_ArteDAO m : crud.SelectObjeto_Arte()) {
          System.out.println(
            "Id: " + m.getNumId() + "\n" +
            "Ano: " + m.getAno() + "\n" +
            "Periodo: " + m.getPeriodo() + "\n" +
            "Titulo: " + m.getTitulo() + "\n" +
            "Pais: " + m.getPais_CulturaDeOrigem() + "\n" + 
            "Descricao: " + m.getDescricao() + "\n" +
            "Artista: " + m.getNome_Artista() + "\n" +
            "Custo: " + m.getCusto() 
            );
            System.out.println("---------------------------------------------------------");
        }

        break;


      }
    }

    private static void opc3(){
      System.out.println("(1) - ALTERAR DADOS ARTISTA");
      System.out.println("(2) - ALTERAR DADOS COLECAO");
      System.out.println("(3) - ALTERAR DADOS EXPOSICAO");
      System.out.println("(4) - ALTERAR DADOS OBJETO DE ARTE EM GERAL");
      System.out.println("(5) - ALTERAR DADOS EMPRESTADOS");
      System.out.println("(6) - ALTERAR DADOS PERMANENTES");
      System.out.println("(7) - ALTERAR DADOS PINTURA");
      System.out.println("(8) - ALTERAR DADOS OUTROS");
      System.out.println("(9) - ALTERAR DADOS ESCULTURA");
      System.out.println("(10) - PARA VOLTAR AO MENU");

      int opcao = 0;
      opcao = input.nextInt();
      while(opcao != 10){

      if(opcao == 1){
      ArtistaDAO artista = new ArtistaDAO();
      System.out.println("Alterar a Data de Nascimento (ano-mes-dia): ");
      artista.setDataNascimento(CapturaNextLine());
      System.out.println("Alterar o Periodo: ");
      artista.setPeriodo(CapturaNextLine());
      System.out.println("Alterar a Data da Morte (ano-mes-dia): ");
      artista.setDataMorte(CapturaNextLine());
      System.out.println("Alterar a Descricao: ");
      artista.setDescricao(CapturaNextLine());
      System.out.println("Alterar o Pais de Origem: ");
      artista.setPaisDeOrigem(CapturaNextLine());
      System.out.println("Alterar o Estilo Principal: ");
      artista.setEstiloPrincipal(CapturaNextLine());
      System.out.println("Digite o Nome que Deseja Alterar: ");
      artista.setNome(CapturaNextLine());

      crud.updateArtista(artista);
      break;
      }

      if(opcao == 2){
      ColecaoDAO colecao = new ColecaoDAO();
      System.out.println("Alterar o Endereco: ");
      colecao.setEndereco(CapturaNextLine());
      System.out.println("Alterar a Pessoa de Contato: ");
      colecao.setPessoaContato(CapturaNextLine());
      System.out.println("Alterar a Descricao: ");
      colecao.setDescricao(CapturaNextLine());
      System.out.println("Alterar o Tipo: ");
      colecao.setTipo(CapturaNextLine());
      System.out.println("Alterar o Id: ");
      colecao.setNumID_Objeto_Arte_Emprestados(input.nextInt());
      System.out.println("Digite o Nome que Deseja Alterar: ");
      colecao.setNome(CapturaNextLine());

      crud.updateColecao(colecao);
      break;

      }

      if(opcao == 3){
      ExposicaoDAO exposicao = new ExposicaoDAO();
      System.out.println("Alterar a Data de Inicio: ");
      exposicao.setDataInicio(CapturaNextLine());
      System.out.println("Alterar a Data de Fim: ");
      exposicao.setDataFim(CapturaNextLine());
      System.out.println("Alterar o Id: ");
      exposicao.setNumID_Objeto_Arte(input.nextInt());
      System.out.println("Digite o Nome que Deseja Alterar: ");
      exposicao.setNome(CapturaNextLine());

      crud.updateExposicao(exposicao);
      break;
      }

      if(opcao == 4){
      Objeto_ArteDAO objeto = new Objeto_ArteDAO();

      
      System.out.println("Alterar o Ano: ");
      objeto.setAno(input.nextInt());
      System.out.println("Alterar o Titulo: ");
      objeto.setTitulo(CapturaNextLine());
      System.out.println("Alterar o Pais de Origem: ");
      objeto.setPais_CulturaDeOrigem(CapturaNextLine());
      System.out.println("Alterar o Descricao: ");
      objeto.setDescricao(CapturaNextLine());
      System.out.println("Alterar o Artista: ");
      objeto.setNome_Artista(CapturaNextLine());
      System.out.println("Alterar o Custo (ex: 100,57): ");
      objeto.setCusto(input.nextDouble());
      System.out.println("Alterar o Periodo: ");
      objeto.setPeriodo(CapturaNextLine());
      System.out.println("Digite o Id que Deseja Alterar: ");
      objeto.setNumId(input.nextInt());

      crud.updateObjeto_Arte(objeto);
      }

      if(opcao == 5){ 
      EmprestadosDAO emprestado = new EmprestadosDAO();

      System.out.println("Alterar a Data de Devolucao (ano-mes-dia): ");
      emprestado.setDataDevolucao(CapturaNextLine());
      System.out.println("Alterar a Data do Emprestimo (ano-mes-dia): ");
      emprestado.setDataEmprestimo(CapturaNextLine());
      System.out.println("Digite o Id que Deseja Alterar: ");
      emprestado.setNumID_Objeto_Arte(input.nextInt());

      crud.updateEmprestados(emprestado);

      break;
     }
      if(opcao == 6){
      PermanentesDAO permanentes = new PermanentesDAO();
      System.out.println("Alterar a Data do Aquisicao (ano-mes-dia): ");
      permanentes.setDataAquisicao(CapturaNextLine());
      System.out.println("Alterar SIM OU NAO se estiver em Exposicao: ");
      permanentes.setEmExposicao(CapturaNextLine());
      System.out.println("Digite o Id que Deseja Alterar: ");
      permanentes.setNumID_Objeto_Arte(input.nextInt());

      crud.updatePermanentes(permanentes);

      break;
     }

      
      if(opcao == 7){
      PinturaDAO pintura = new PinturaDAO();
      System.out.println("Alterar o Tipo de Tinta: ");
      pintura.setTipoTinta(CapturaNextLine());
      System.out.println("Alterar o Suporte: ");
      pintura.setSuporte(CapturaNextLine());
      System.out.println("Alterar o Estilo: ");
      pintura.setEstilo(CapturaNextLine());
      System.out.println("Digite o Id que Deseja Alterar: ");
      pintura.setNumID_Objeto_Arte(input.nextInt());

      crud.updatePintura(pintura);

      break;
      }

      if(opcao == 8){
      OutrosDAO outros = new OutrosDAO();
      System.out.println("Alterar o Estilo: ");
      outros.setEstilo(CapturaNextLine());
      System.out.println("Alterar o Tipo: ");
      outros.setTipo(CapturaNextLine());
      System.out.println("Digite o Id que Deseja Alterar: ");
      outros.setNumID_Objeto_Arte(input.nextInt());

      crud.updateOutros(outros);

      break;
      }
      if(opcao == 9){
      EsculturaDAO escultura = new EsculturaDAO();
      System.out.println("Alterar o Estilo: ");
      escultura.setEstilo(CapturaNextLine());
      System.out.println("Alterar o Peso (kg): ");
      escultura.setPeso(input.nextInt());
      System.out.println("Alterar a Altura (ex: 1,57): ");
      escultura.setAltura(input.nextDouble());
      System.out.println("Alterar o Material: ");
      escultura.setMaterial(CapturaNextLine());
      System.out.println("Digite o Id que Deseja Alterar: ");
      escultura.setNumID_Objeto_Arte(input.nextInt());

      crud.updateEscultura(escultura);

      break;
      }

    }
    }

    private static void opc4(){
      System.out.println("(1) - DELETAR ARTISTA");
      System.out.println("(2) - DELETAR COLECAO");
      System.out.println("(3) - DELETAR EXPOSICAO");
      System.out.println("(4) - DELETAR OBJETOS DE ARTE");
      System.out.println("(5) - PARA VOLTAR AO MENU");

      int opcao = 0;
      opcao = input.nextInt();
      while(opcao != 5){

      if(opcao == 1){
        System.out.println("Digite o Nome de quem Deseja Deletar: ");
        crud.deleteArtista(CapturaNextLine());
        break;
      }
      if(opcao == 2){
        System.out.println("Digite o Nome de quem Deseja Deletar: ");
        crud.deleteColecao(CapturaNextLine());
        break;
      }
      if(opcao == 3){
        System.out.println("Digite o Nome de quem Deseja Deletar: ");
        crud.deleteExposicao(CapturaNextLine());
        break;
      }
      if(opcao == 4){
        System.out.println("Digite o Id de quem Deseja Deletar: ");
        int id = input.nextInt();
        crud.deleteEmprestados(id);
        crud.deletePermanentes(id);
        crud.deletePintura(id);
        crud.deleteOutros(id);
        crud.deleteEscultura(id);
        crud.deleteObjeto_Arte(id);
        break;
      }

      }


    }

    private static void opc5(){
      System.out.println("(1) - PINTURAS PERMANENTES");
      System.out.println("(2) - PINTURAS EMPRESTADOS");
      System.out.println("(3) - ESCULTURAS PERMANENTES");
      System.out.println("(4) - ESCULTURAS EMPRESTADOS");
      System.out.println("(5) - OUTROS PERMANENTES");
      System.out.println("(6) - OUTROS EMPRESTADOS");
      System.out.println("(7) - IMPRIME TODOS");
      System.out.println("(8) - PARA VOLTAR AO MENU");

      int opcao = input.nextInt();
      if(opcao == 8) return;

      dao.listaObjetosPorTipoClasse(opcao);
   
    }

    private static void opc6(){

      System.out.println("Digite o Mes:");
      int mes = input.nextInt();
      System.out.println("Digite o Ano");
      int ano = input.nextInt();


      dao.listagemDeEmprestimoPorMesPorAno(mes, ano);
    
    }

    private static void opc7(){

      System.out.println("Digite o Mes:");
      int mes = input.nextInt();
      System.out.println("Digite o Ano");
      int ano = input.nextInt();


      dao.listagemDeCompraPorMesPorAno(mes, ano);
    
    }

    private static void opc8(){
      dao.curvaGastos();
    }

    private static void opc9(){

      System.out.println("Digite o Mes:");
      int mes = input.nextInt();
      System.out.println("Digite o Ano");
      int ano = input.nextInt();

      System.out.println("\n(1) - PINTURA");
      System.out.println("(2) - OUTROS");
      System.out.println("(3) - ESCULTURA");
      int opcao = input.nextInt();
      String colecao = null;
      if(opcao == 1){
        colecao = "pintura";
      }
      if(opcao == 2){
        colecao = "outros";
      }
      if(opcao == 3){
        colecao = "escultura";
      }

      
      dao.listagemQuantEmprestados(mes, ano, colecao);
    
    }

    private static void opc10(){
      dao.curvaTotaisPorColecaoPorMuseu();
    }

    


    //////////////////////////////////////////////
    //            METODO AUXILIAR               //
    //////////////////////////////////////////////

    //Metodo para consertar o erro de pula de linha do nextLine()
    private static String CapturaNextLine(){
    Scanner chave = new Scanner(System.in);
    return chave.nextLine();
    }

    private static void insertTipoDoObjeto(int id){

      System.out.println("(1) - PINTURA");    
      System.out.println("(2) - OUTROS");
      System.out.println("(3) - ESCULTURA"); 
      int opcao = input.nextInt();   
      
      if(opcao == 1){
      PinturaDAO pintura = new PinturaDAO();
      pintura.setNumID_Objeto_Arte(id);
      System.out.println("Digite o Tipo de Tinta: ");
      pintura.setTipoTinta(CapturaNextLine());
      System.out.println("Digite o Suporte: ");
      pintura.setSuporte(CapturaNextLine());
      System.out.println("Digite o Estilo: ");
      pintura.setEstilo(CapturaNextLine());
      crud.InsertPintura(pintura);

      }
      if(opcao == 2){
      OutrosDAO outros = new OutrosDAO();
      outros.setNumID_Objeto_Arte(id);
      System.out.println("Digite o Estilo: ");
      outros.setEstilo(CapturaNextLine());
      System.out.println("Digite o Tipo: ");
      outros.setTipo(CapturaNextLine());
      crud.InsertOutros(outros);

      }
      if(opcao == 3){
      EsculturaDAO escultura = new EsculturaDAO();
      escultura.setNumID_Objeto_Arte(id);
      System.out.println("Digite o Estilo: ");
      escultura.setEstilo(CapturaNextLine());
      System.out.println("Digite o Peso (kg): ");
      escultura.setPeso(input.nextInt());
      System.out.println("Digite a Altura (ex: 1,57): ");
      escultura.setAltura(input.nextDouble());
      System.out.println("Digite o Material: ");
      escultura.setMaterial(CapturaNextLine());
      crud.InsertEscultura(escultura);

      }
    }



}
