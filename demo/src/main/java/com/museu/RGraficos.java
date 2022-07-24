package com.museu;

import com.github.rcaller.rstuff.RCaller;
import com.github.rcaller.rstuff.RCode;
import com.github.rcaller.graphics.DefaultTheme;

import javax.swing.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RGraficos { 

    public RGraficos() {}

    //Nesta classe e usado o RCaller q e uma integracao do Java com R 
    //Para plotar os graficos solicitados pelo enunciado
    //code envia uma String para ser processado no R 
    //code.showPlot(file) imprime o grafico usando o Java

    public void plotCusto(String[] x,double[] y) {
        try {
    
          RCaller caller = RCaller.create();
          
          caller.setGraphicsTheme(new DefaultTheme());
    
    
          RCode code = RCode.create();

    
          code.addDoubleArray("Custo", y);
          code.addStringArray("Datas", x);
    
          File file = code.startPlot();
          code.addRCode("library(ggplot2)");
          code.addRCode("ggplot() + geom_line(col='blue',lwd=1.1,aes(x=Datas, y=Custo, group=1)) + labs(title='CUSTO DOS OBJETOS')");
          //code.addRCode("ggsave(filename='E:/Downloads/teste.png')");
          code.endPlot();
    
          caller.setRCode(code);
          
          caller.runOnly();
          
          /** 
           * Manipulacao de grafico gerado como ImageIcon
          */
          ImageIcon ii = code.getPlot(file);
          
          code.showPlot(file);
          
          
        } catch (Exception e) {
          Logger.getLogger(RGraficos.class.getName()).log(Level.SEVERE, e.getMessage());
        }
      }



      public void plotColecao(int[] quantidade) {
        try {
    
          RCaller caller = RCaller.create();
          
          caller.setGraphicsTheme(new DefaultTheme());
    
    
          RCode code = RCode.create();

    
          String[] colecoes = {"pintura", "outros", "escultura"};
          code.addIntArray("Quantidade", quantidade);
          code.addStringArray("Colecoes", colecoes);
          
    
          File file = code.startPlot();
          code.addRCode("library(ggplot2)");
          code.addRCode("ggplot() + geom_line(col='red',lwd=1.1,aes(x=Colecoes, y=Quantidade, group=1),colour='darkorange', size=1.5) + theme(axis.text.x=element_text(angle=90, hjust=1, vjust=0.5))+ labs(title='QUANTIDADE NO MUSEU')");
          code.endPlot();
    
          caller.setRCode(code);
          
          caller.runOnly();
          
          /** 
           * Manipulacao de grafico gerado como ImageIcon
          */
          ImageIcon ii = code.getPlot(file);
          
          code.showPlot(file);
          
          
        } catch (Exception e) {
          Logger.getLogger(RGraficos.class.getName()).log(Level.SEVERE, e.getMessage());
        }
      }

    
}