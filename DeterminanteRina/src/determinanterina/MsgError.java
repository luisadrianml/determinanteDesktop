/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package determinanterina;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author pc167
 */
public class MsgError {
private Stage dialogStage;



public void msgBox(String title){
    dialogStage = new Stage();
    GridPane grd_pan = new GridPane();
    grd_pan.setAlignment(Pos.CENTER);
    grd_pan.setHgap(10);
    grd_pan.setVgap(10);//pading
    Scene scene =new Scene(grd_pan,300,150);
    dialogStage.setScene(scene);
    dialogStage.setTitle("alert");
    dialogStage.initModality(Modality.WINDOW_MODAL);

    Label lab_alert= new Label(title);
    grd_pan.add(lab_alert, 0, 1);

    Button btn_ok = new Button("fermer");
    btn_ok.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
            // TODO Auto-generated method stub
            dialogStage.hide();

        }
    });
    grd_pan.add(btn_ok, 0, 2);

    dialogStage.show();

}



}
