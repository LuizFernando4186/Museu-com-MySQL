package com.museu;

import java.sql.*;


class Conexao { 

private static final String USERNAME = "root";
private static final String PASSWORD = "418616De*";//Senha do BD de Luiz
private static final String DATABASE_URL = "jdbc:mysql://localhost/museu";

//Fazer a conexao com o BD
//Usando a biblioteca Connection
public static Connection conectaBD() throws Exception { 

  //Faz com que a classe seja carregada pela JVM
  String driver = "com.mysql.cj.jdbc.Driver";

  Class.forName(driver);
  //Fazer a conexao com o banco de dados usando o endereco, login e senha
  Connection conexao = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);


  return conexao;

}


}
