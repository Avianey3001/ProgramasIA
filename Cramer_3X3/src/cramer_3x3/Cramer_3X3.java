package cramer_3x3;

import java.text.DecimalFormat;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author avide
 */
public class Cramer_3X3 extends Agent{
    double[][] x = new double[3][3];
    double[][] y = new double[3][1];
    double det,detX,detY,detZ;
    DecimalFormat df = new DecimalFormat("#.00");
    
    public Cramer_3X3(){
        
    }
    public Cramer_3X3(double[][] x, double[][] y){
        this.x = x;
        this.y = y;
    }
    
    public void determinante(double[][] x,double[][] y){
        System.out.println("Determinante");
        
        det=x[0][0]*((x[1][1]*x[2][2])-(x[1][2]*x[2][1]))-x[0][1]*((x[1][0]*x[2][2])-(x[1][2]*x[2][0]))+x[0][2]*((x[1][0]*x[2][1])-(x[1][1]*x[2][0]));
        System.out.println(df.format(det));
        if(det != 0){
        System.out.println("Determinante X");
        detX=y[0][0]*((x[1][1]*x[2][2])-(x[1][2]*x[2][1]))-x[0][1]*((y[1][0]*x[2][2])-(x[1][2]*y[2][0]))+x[0][2]*((y[1][0]*x[2][1])-(x[1][1]*y[2][0]));
        System.out.println(df.format(detX));
                
        System.out.println("Determinante Y");
        detY=x[0][0]*((y[1][0]*x[2][2])-(x[1][2]*y[2][0]))-y[0][0]*((x[1][0]*x[2][2])-(x[1][2]*x[2][0]))+x[0][2]*((x[1][0]*y[2][0])-(y[1][0]*x[2][0]));
        System.out.println(df.format(detY));
                
        System.out.println("Determinante Z");
        detZ=x[0][0]*((x[1][1]*y[2][0])-(y[1][0]*x[2][1]))-x[0][1]*((x[1][0]*y[2][0])-(y[1][0]*x[2][0]))+y[0][0]*((x[1][0]*x[2][1])-(x[1][1]*x[2][0]));
        System.out.println(df.format(detZ));
        }
        else
            System.out.println("No tiene solución");
    }
    public void resultados(){
        
        System.out.println("Resultado");
        System.out.println("ŷ = "+df.format(detX/det)+" + "+df.format(detY/det)+"x1  +  "+df.format(detZ/det)+"x2");
    }
    protected void setup() {
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

    public void action() {
        Cramer_3X3 c = new Cramer_3X3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                x[i][j] = Double.parseDouble(JOptionPane.showInputDialog("X = posición ["+i+","+j+"]"));
            }
            
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                y[i][j] = Double.parseDouble(JOptionPane.showInputDialog("Y = posición ["+i+","+j+"]"));
            }
            
        }
        c.determinante(x,y);
        c.resultados();
    }
        public int onEnd() {
              myAgent.doDelete();   
              return super.onEnd();
        }
    } 
} 

