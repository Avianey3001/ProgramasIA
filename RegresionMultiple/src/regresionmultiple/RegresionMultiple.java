package regresionmultiple;

import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
/**
 *
 * @author avide
 */
public class RegresionMultiple extends Agent {
    double[][] x = new double[3][3];
    double[][] y = new double[3][1];
    double[][] xT = new double[3][3];
    double[][] xTy = new double[3][1];
    double[][] adj = new double[3][3];
    double[][] r = new double [3][1];
    double det;
    DecimalFormat df = new DecimalFormat("#.00");
    
    public void mostrarMatriz(double[][] x,double[][] y){ 
        int tamanioX= x.length;
        System.out.println("Matriz X");
        for(int i=0;i<x.length;i++){
            for(int j=0;j<x[0].length;j++){
                System.out.print("\t"+x[i][j]+"\t");
                xT[j][i]=x[i][j];  
            }
            System.out.println("\n");
        }
          System.out.println("Matriz Transpuesta X");
          for(int i=0;i<xT.length;i++){
            for(int j=0;j<xT[0].length;j++){
                System.out.print("\t"+xT[i][j]+"\t");
            }
            System.out.println("\n");
        }
          System.out.println("Matriz Transpuesta X * Matriz X");
          double[][] c = new double[xT.length][x[0].length];
          for(int i=0;i<xT.length;i++){
            for(int j=0;j<xT[0].length;j++){
                for (int k=0; k<x.length; k++)
                  c[i][j] = c[i][j] + x[i][k] * xT[k][j];
                System.out.print("\t"+df.format((c[i][j]))+"\t");
            }
            System.out.println("\n");
          }
          System.out.println("Matriz Transpuesta X * Y");
                xTy[0][0] = (xT[0][0]*y[0][0]) + (xT[0][1]*y[1][0]) + (xT[0][2]*y[2][0]);
                xTy[1][0] = (xT[1][0]*y[0][0]) + (xT[1][1]*y[1][0]) + (xT[1][2]*y[2][0]);
                xTy[2][0] = (xT[2][0]*y[0][0]) + (xT[2][1]*y[1][0]) + (xT[2][2]*y[2][0]);
                for(int i=0;i<y.length;i++){
                     for(int j=0;j<y[0].length;j++){
                        System.out.print("\t"+df.format(xTy[i][j])+"\t");
                     }
                System.out.println("\n");
        }
                System.out.println("Determinante X Transpuesta * X");
                det=c[0][0]*((c[1][1]*c[2][2])-(c[1][2]*c[2][1]))-c[0][1]*((c[1][0]*c[2][2])-(c[1][2]*c[2][0]))+c[0][2]*((c[1][0]*c[2][1])-(c[1][1]*c[2][0]));
                System.out.println(df.format(det));
                System.out.println("Inversa de X Transpuesta X");
                adj[0][0] = (c[1][1]*c[2][2])-(c[1][2]*c[2][1]) ;
                adj[0][1] = -1*((c[1][0]*c[2][2])-(c[1][2]*c[2][0])) ;
                adj[0][2] = (c[1][0]*c[2][1])-(c[1][1]*c[2][0]);
                
                adj[1][0] = -1*((c[0][1]*c[2][2])-(c[0][2]*c[2][1]));
                adj[1][1] = (c[0][0]*c[2][2])-(c[0][2]*c[2][0]);
                adj[1][2] = -1*((c[0][0]*c[2][1])-(c[0][1]*c[2][0]));
                
                adj[2][0] = (c[0][1]*c[1][2])-(c[0][2]*c[1][1]);
                adj[2][1] = -1*((c[0][0]*c[1][2])-(c[0][2]*c[1][0]));
                adj[2][2] = (c[0][0]*c[1][1])-(c[0][1]*c[1][0]);
                for(int i=0;i<adj.length;i++){
            for(int j=0;j<adj[0].length;j++){
                //System.out.print("\t"+df.format(adj[i][j]) +"\t");
                adj[j][i]=adj[i][j];
                System.out.print((adj[i][j])*(1/det)+"\t");
            }
            System.out.println("\n");
        }
                System.out.println("Resultados");
                r[0][0] = (adj[0][0]*xTy[0][0]) + (adj[0][1]*xTy[1][0]) + (adj[0][2]*xTy[2][0]);
                r[1][0] = (adj[1][0]*xTy[0][0]) + (adj[1][1]*xTy[1][0]) + (adj[1][2]*xTy[2][0]);
                r[2][0] = (adj[2][0]*xTy[0][0]) + (adj[2][1]*xTy[1][0]) + (adj[2][2]*xTy[2][0]);
                for(int i=0;i<xTy.length;i++){
                     for(int j=0;j<xTy[0].length;j++){
                        System.out.print("\t"+df.format(r[i][j])+"\t");
                     }
                System.out.println("\n");
            }   
    }
    protected void setup() {
    addBehaviour(new MyOneShotBehaviour());
  } 

  private class MyOneShotBehaviour extends OneShotBehaviour {

    public void action() {
        RegresionMultiple rm = new RegresionMultiple();
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
        rm.mostrarMatriz(x,y);
        
    }
        public int onEnd() {
              myAgent.doDelete();   
              return super.onEnd();
    }
  } 
}