/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package determinanterina;


import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



/**
 *
 * @author LuisAdrianML 2014
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField numeroM;

    @FXML
    private Button buttonEnter;
    
    private int matrix1[][]; 
    private int matrix2[][];
    private int nP;
    private int posM = 1;
    private int f = 0; 
    private int c = 0; 
    private boolean fin = false;

    @FXML
    private ComboBox<String> ordenSelection;
    
    @FXML
    private TextArea textAreaResultados;
    
    
    @FXML
    void enterNumber(ActionEvent event) {
        
        String entrada = numeroM.getText();
        if (!numeroM.getText().isEmpty()) {
            guardarenMatriz(Integer.parseInt(entrada), posM);
            numeroM.clear();

            if (isMatrixFull()) {
                isCompleted();
                if(posM==2) {
                limpiar();
                }
            }     
            labelSetFilaColumna();
        }

    }
    
    void isCompleted() {
                numeroM.setEditable(false);
                numeroM.clear();
                buttonEnter.setDisable(true);
                numeroM.setDisable(true);
                numeroM.setPromptText("Uff, llenamos las matrices");
                f=0;c=0;
                posM++;
                if (posM==3) {
                procesos();
                 }
    }
    
    
    boolean isMatrixFull() {
        return fin && f==nP;
        
    }
    
    void guardarenMatriz(int numero, int posMatrix) {
        if(f<nP) {
            if (c<nP) {
                switch(posMatrix) {
                    case 1: {
                        matrix1[f][c] = numero;
                        break;
                    }
                    case 2: {
                        matrix2[f][c] = numero;
                        break;
                    }
                }  
                //System.out.println("Matriz#"+posMatrix+"["+f+"]["+c+"]: "+numero);   
                c++;
            }
                if (nP==c) {
                    f++; c=0;
                } 
                if (nP==f) {
                    fin = true;
                }
        }
    }

    public void selected(String s) {
        createMatrix(Integer.parseInt(s));
        limpiar();
    }
    
    void limpiar() {
        numeroM.setEditable(true);
        numeroM.setDisable(false);
        numeroM.setPromptText("Digite numero (matriz #"+posM+")");
        buttonEnter.setDisable(false);
        f = 0;
        c = 0;
        
    }
    
    public void createMatrix(int n) {
        matrix1 = new int[n][n];
        matrix2 = new int[n][n];
        nP= n;
    }

    
    private void keyType(final TextField textField){
         textField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
        @Override 
        public void handle(KeyEvent inputevent) {
              if (!(inputevent.getCharacter().matches("[0-9]"))|| textField.getText().length()>14) {              
                           inputevent.consume();                   
        }
            }
        });
    }
     
    private void keyPressed(final TextField textField) {
         textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke)
            {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    enterNumber(null);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textAreaResultados.setEditable(false);
        numeroM.setEditable(false);
        numeroM.setPromptText("Uhm.. Aun no estamos listos");
        textAreaResultados.setText("Bienvenidos, \n"
                + "El primer paso es seleccionar el grado \n"
                + "para luego insertar los datos de la \n"
                + "matriz, hasta llegar al resultado. \n\n"
                + "¡Suerte!");
        ordenSelection.getItems().clear();
        ordenSelection.getItems().addAll("2","3", "4","5", "6","8","9");
        ordenSelection.setPromptText("Seleccione un orden");
        keyType(numeroM);
        keyPressed(numeroM);
        ordenSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            if (t1 != null) {
                selected(t1);
                restart();
            }
            labelSetFilaColumna();
            }
            
        });
    }   
    
    private void restart() {
        f=0;
        c=0;
        posM=1;
        numeroM.setEditable(true);
        numeroM.setDisable(false);
        numeroM.setPromptText("Digite numero (matriz #"+posM+")");
        buttonEnter.setDisable(false);
        textAreaResultados.clear();
        
        
    }

    private void labelSetFilaColumna() {

        if (posM!=3) {
            textAreaResultados.setText("Matriz #"+posM+" [Fila: "+(f+1)+"][Columna: "+(c+1)+"]");
        } 
    } 

    private void procesos() {
       
            textAreaResultados.clear();
            
            textAreaResultados.appendText("\nDeterminate #1: "+ Matrices.determinante(matrix1));
            textAreaResultados.appendText("\nDeterminate #2: "+ Matrices.determinante(matrix2));
            textAreaResultados.appendText("\n");
        
         if (nP%2==0) {
            int[] suma1 = new int[matrix1.length];
            suma1 = Matrices.sumaFila(suma1, matrix1);
            
            int[] suma2 = new int[matrix2.length];
            suma2 = Matrices.sumaFila(suma2, matrix2);
            
            int[] producto1 = new int[matrix1.length];
            producto1 = Matrices.llenarVector(producto1, 1);
            producto1 = Matrices.productoColumna(producto1, matrix1);
            
            int[] producto2 = new int[matrix2.length];
            producto2= Matrices.llenarVector(producto2, 1);
            producto2 = Matrices.productoColumna(producto2, matrix2);
            
            textAreaResultados.appendText("\nSuma de filas para determinantes :");
            textAreaResultados.appendText("\nVector determinante 1: " + Arrays.toString(suma1));
            textAreaResultados.appendText("\nVector determinante 2: " + Arrays.toString(suma2));
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nProducto de columnas para determinantes :");
            textAreaResultados.appendText("\nVector determinante 1: " + Arrays.toString(producto1));
            textAreaResultados.appendText("\nVector determinante 2: " + Arrays.toString(producto2));
            textAreaResultados.appendText("\n");
  

        }
        if (nP%2!=0 && nP%3==0) {
            
            double sumaDeterminantes = Matrices.determinante(matrix1)+Matrices.determinante(matrix2);

            
            double restaDeterminantes = Matrices.determinante(matrix1)-Matrices.determinante(matrix2);
            
            textAreaResultados.appendText("\nSuma de determinantes \n");
            textAreaResultados.appendText(Double.toString(sumaDeterminantes));
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nResta de determinantes \n");
            textAreaResultados.appendText(Double.toString(restaDeterminantes));
            textAreaResultados.appendText("\n");
        }
        if (nP==2 || nP==3) {
            double productoDeterminantes = Matrices.determinante(matrix1)*Matrices.determinante(matrix2);
            
            double divisionDeterminantes;
            if (Matrices.determinante(matrix2)!=0) {
                divisionDeterminantes = Matrices.determinante(matrix1)/Matrices.determinante(matrix2);
            } else {
                divisionDeterminantes = 0;
            }   
            
            
            textAreaResultados.appendText("\nProducto de determinantes: \n");
            textAreaResultados.appendText(Double.toString(productoDeterminantes));
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nDivision de determinantes: \n");
            textAreaResultados.appendText(Double.toString(divisionDeterminantes));
            textAreaResultados.appendText("\n");
        }
        if (nP>=4 && nP<=7) {
            
 
            textAreaResultados.appendText("\nDeterminante con raiz: ");
            if (Matrices.sumaInternaVector(Matrices.sumaFila(new int[matrix1.length], matrix1))>Matrices.productoInternaVector(Matrices.productoColumna(new int[matrix1.length], matrix1))) {
                int [][] matrizNewS1 = Matrices.cambiarMatriz(matrix1, 1);
                double determinanteSuma1 = Matrices.determinante(matrizNewS1);
                textAreaResultados.appendText("\n");
                textAreaResultados.appendText(Double.toString(determinanteSuma1));
           
            }
            if (Matrices.sumaInternaVector(Matrices.sumaFila(new int[matrix2.length], matrix2))>Matrices.productoInternaVector(Matrices.productoColumna(new int[matrix2.length], matrix1))) {
                int [][] matrizNewS2 = Matrices.cambiarMatriz(matrix2, 1);
                double determinanteSuma2 = Matrices.determinante(matrizNewS2);

                textAreaResultados.appendText("\n");
                textAreaResultados.appendText(Double.toString(determinanteSuma2));
            }
            
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nDeterminante con logaritmo: ");
            
            if (Matrices.productoInternaVector(Matrices.productoFila(new int[matrix1.length], matrix1))<Matrices.sumaInternaVector(Matrices.sumaColumna(new int[matrix1.length], matrix1))) {
                int [][] matrizNewP1 = Matrices.cambiarMatriz(matrix1, 2);
                double determinanteProducto1 = Matrices.determinante(matrizNewP1);

                textAreaResultados.appendText("\n");
                textAreaResultados.appendText(Double.toString(determinanteProducto1));
            } 
            if (Matrices.productoInternaVector(Matrices.productoFila(new int[matrix2.length], matrix2))<Matrices.sumaInternaVector(Matrices.sumaColumna(new int[matrix1.length], matrix1))) {
                int [][] matrizNewP2 = Matrices.cambiarMatriz(matrix2, 2);
                double determinanteProducto2 = Matrices.determinante(matrizNewP2);

                textAreaResultados.appendText("\n");
                textAreaResultados.appendText(Double.toString(determinanteProducto2));
            }
           
        }
    }
    
}
