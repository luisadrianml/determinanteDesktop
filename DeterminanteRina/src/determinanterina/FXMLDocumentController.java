/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package determinanterina;

import java.awt.event.KeyEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



/**
 *
 * @author pc167
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField numeroM;
    
        @FXML
    private Label filaycolumna;
        
    @FXML
    private Label lblMatriz;
    
     @FXML
    private TextField numeroM2;

    @FXML
    private Button buttonEnter2;
    
        @FXML
    private Label labelnumberMatriz;

    @FXML
    private Label detailsLabel;

    @FXML
    private Label messageLabel;
    
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
        
        String entrada = numeroM.getText().toString();
        
        if (confirmarEntry(entrada)) {
            guardarenMatriz(Integer.parseInt(entrada), posM);
//            labelSetFilaColumna();
            if (matrixFull()) {

                completed();
                if(posM==2) {
                limpiar();
                }
                
            }
        } else {
//            Parent root;
//        try {
//            
//            root = FXMLLoader.load(getClass().getResource("AlertDialog.fxml"));
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root, 450, 450));
//            stage.show();
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//        
        }
        
    }
    
    void completed() {
                numeroM.setEditable(false);
                numeroM.clear();
                buttonEnter.setDisable(true);
                numeroM.setDisable(true);
                numeroM.setPromptText("Matrices llena");
                f=0;c=0;
                posM++;
                if (posM==3) {
                procesos();
                 }
    }
    
    
    boolean matrixFull() {
        if(fin && f==nP) {

            return true;
        } else {
            return false;
        }
        
    }
    
    void imprimirMatrix() {
        for (int i=0;i<nP;i++) {
            for (int e=0; i<nP; e++) {
                System.out.println(matrix1[i][e]);
            }
        }
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
                
                System.out.println("Matriz#"+posMatrix+"["+f+"]["+c+"]: "+numero);
                
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

    
    
    public boolean confirmarEntry(String entrada) {
        if (entrada.matches("^[0-9]+$")) {
            return true;
        } else {
                        System.out.println("ELSE no es digito");
            return false;
            

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
    
    public void clicked() {
        
    }
    
//     private void keyType(final TextField textField){
//         textField.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//        @Override  public void handle(KeyEvent inputevent) {
//              if (!(inputevent.toString().matches("\\d")&& !inputevent.toString().matches("\\,"))|| textField.getText().length()>14) {              
//                           inputevent.consume();
//                           
//        }
//            }
//        });
//         
//     }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textAreaResultados.setEditable(false);
        ordenSelection.getItems().clear();
        ordenSelection.getItems().addAll("2","3", "4","5", "6","8","9");
//        lblMatriz.setVisible(false);
//        labelnumberMatriz.setVisible(false);
        
        ordenSelection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            if (t1 != null) {
                clicked();
                selected(t1);
            }
//            labelSetFilaColumna();
            }
            
        });
    }    
//
//    private void labelSetFilaColumna() {
//        filaycolumna.setText("[Fila:"+(f+1)+"][Columna:"+(c+1)+"]");
//    }
    
    
    
    
    
    public static int[] sumaFila(int[] suma, int[][] matriz) {
        for (int fila=0;fila<suma.length;fila++) {
                for (int column=0;column<suma.length;column++) {
                    suma[fila] = suma[fila] + matriz[fila][column];
                }
            }
        
        return suma;
    }
    
 
    
    public static int[] sumaColumna(int[] suma, int[][] matriz) {
        for (int column=0;column<suma.length;column++) {
                for (int fila=0;fila<suma.length;fila++) {
                    suma[column] = suma[column] + matriz[fila][column];
                }
            }
        return suma;
    }
    
    public static int sumaInternaVector(int[] vector) {
        int suma = 0;
        for (int i=0; i < vector.length; i++) {
            suma = suma + vector[i];
        }
        return suma;
    }
    
    public static int[] productoColumna(int[] producto, int[][] matriz) {
        for (int column=0;column<producto.length;column++) {
                for (int fila=0;fila<producto.length;fila++) {
                    producto[column] = producto[column] * matriz[fila][column];
                }
            }
        
        return producto;
    }
    
    public static int[] productoFila(int[] producto, int[][] matriz) {
        for (int fila=0;fila<producto.length;fila++) {
                for (int column=0;column<producto.length;column++) {
                    producto[fila] = producto[fila] * matriz[fila][column];
                }
            }
        
        return producto;
    }
    

    public static void imprimirVector(int[] vector) {
        for (int i=0;i<vector.length;i++) {
            System.out.println("| "+vector[i] + " |");
        }
        System.out.println("");
    }
    
    public static double determinante(int[][] matriz) {
    double det;
    
    if(matriz.length==2) {
        det=(matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
        return det;
    }
    
    double suma=0;
    
    for(int i=0; i<matriz.length; i++){
        int[][] nm = new int[matriz.length-1][matriz.length-1];
        for(int j=0; j<matriz.length; j++){
            if(j!=i){
                for(int k=1; k<matriz.length; k++){
                    int indice=-1;
                    if(j<i) {
                        indice=j;
                    }
                    else if(j>i) {
                        indice=j-1;
                    }
                nm[indice][k-1]= matriz[j][k];
                }
            }
        }
        if(i%2==0)
        suma+=matriz[i][0] * determinante(nm);
        else
        suma-=matriz[i][0] * determinante(nm);
    }
    return suma;
}public static int[] llenarVector(int[] vector, int contenido) {
            for (int fila=0;fila<vector.length;fila++) {
                vector[fila] = contenido;
            }
        return vector;
    }

public static int[][] cambiarMatriz(int[][] matriz, int operacion) {
        switch (operacion) {
            case 1: {
                // raiz
                for (int fila=0;fila<matriz.length;fila++) {
                    for (int column=0;column<matriz.length;column++) {
                         matriz[fila][column] = (int) Math.sqrt(matriz[fila][column]); 
                    }
                }
                return matriz;
            }
            case 2: {
                // logaritmo natural
                for (int fila=0;fila<matriz.length;fila++) {
                    for (int column=0;column<matriz.length;column++) {
                         matriz[fila][column] = (int) Math.log(matriz[fila][column]); 
                    }
                }
                return matriz;
            }
        }
        
        
        return matriz;
    }

    public static int productoInternaVector(int[] vector) {
        int producto = 1;
        for (int i=0;i<vector.length;i++) {
            producto = producto*vector[i];
        }
        return producto;
    }

    private void procesos() {
         if (nP%2==0) {
            int[] suma1 = new int[matrix1.length];
            suma1 = sumaFila(suma1, matrix1);
            
            int[] suma2 = new int[matrix2.length];
            suma2 = sumaFila(suma2, matrix2);
            
            int[] producto1 = new int[matrix1.length];
            producto1 = llenarVector(producto1, 1);
            producto1 = productoColumna(producto1, matrix1);
            
            int[] producto2 = new int[matrix2.length];
            producto2= llenarVector(producto2, 1);
            producto2 = productoColumna(producto2, matrix2);
            
            textAreaResultados.appendText("\nSuma de filas para determinantes :");
            textAreaResultados.appendText("\nVector determinante 1: " + Arrays.toString(suma1));
            textAreaResultados.appendText("\nVector determinante 2: " + Arrays.toString(suma2));
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nProducto de columnas para determinantes :");
            textAreaResultados.appendText("\nVector determinante 1: " + Arrays.toString(producto1));
            textAreaResultados.appendText("\nVector determinante 2: " + Arrays.toString(producto2));
  

        }
        if (nP%2!=0 && nP%3==0) {
            
            double sumaDeterminantes = determinante(matrix1)+determinante(matrix2);

            
            double restaDeterminantes = determinante(matrix1)-determinante(matrix2);
            
            textAreaResultados.appendText("\nSuma de determinantes \n");
            textAreaResultados.appendText(Double.toString(sumaDeterminantes));
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nResta de determinantes \n");
            textAreaResultados.appendText(Double.toString(restaDeterminantes));
            textAreaResultados.appendText("\n");
        }
        if (nP==2 || nP==3) {
            double productoDeterminantes = determinante(matrix1)*determinante(matrix2);
            
            double divisionDeterminantes;
            if (determinante(matrix2)!=0) {
                divisionDeterminantes = determinante(matrix1)/determinante(matrix2);
            } else {
                divisionDeterminantes = 0;
            }   
            
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nProducto de determinantes: \n");
            textAreaResultados.appendText(Double.toString(productoDeterminantes));
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nDivision de determinantes: \n");
            textAreaResultados.appendText(Double.toString(divisionDeterminantes));
        }
        if (nP>=4 && nP<=7) {
            
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nDeterminante con raiz: ");
            if (sumaInternaVector(sumaFila(new int[matrix1.length], matrix1))>productoInternaVector(productoColumna(new int[matrix1.length], matrix1))) {
                int [][] matrizNewS1 = cambiarMatriz(matrix1, 1);
                double determinanteSuma1 = determinante(matrizNewS1);
                textAreaResultados.appendText("\n");
                textAreaResultados.appendText(Double.toString(determinanteSuma1));
           
            }
            if (sumaInternaVector(sumaFila(new int[matrix2.length], matrix2))>productoInternaVector(productoColumna(new int[matrix2.length], matrix1))) {
                int [][] matrizNewS2 = cambiarMatriz(matrix2, 1);
                double determinanteSuma2 = determinante(matrizNewS2);

                textAreaResultados.appendText("\n");
                textAreaResultados.appendText(Double.toString(determinanteSuma2));
            }
            
            textAreaResultados.appendText("\n");
            textAreaResultados.appendText("\nDeterminante con logaritmo: ");
            
            if (productoInternaVector(productoFila(new int[matrix1.length], matrix1))<sumaInternaVector(sumaColumna(new int[matrix1.length], matrix1))) {
                int [][] matrizNewP1 = cambiarMatriz(matrix1, 2);
                double determinanteProducto1 = determinante(matrizNewP1);

                textAreaResultados.appendText("\n");
                textAreaResultados.appendText(Double.toString(determinanteProducto1));
            } 
            if (productoInternaVector(productoFila(new int[matrix2.length], matrix2))<sumaInternaVector(sumaColumna(new int[matrix1.length], matrix1))) {
                int [][] matrizNewP2 = cambiarMatriz(matrix2, 2);
                double determinanteProducto2 = determinante(matrizNewP2);

                textAreaResultados.appendText("\n");
                textAreaResultados.appendText(Double.toString(determinanteProducto2));
            }
           
        }
    }
    
}
