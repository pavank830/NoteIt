
package noteit;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import static noteit.FXMLDocumentController.a;
import static noteit.FXMLDocumentController.sta;
import static noteit.FXMLDocumentController.textare;
import static noteit.FXMLDocumentController.oldData;

public class NoteIt extends Application {
static Stage stage;    
public static int p=0;
KeyCombination kc=new KeyCharacterCombination("S",KeyCombination.CONTROL_DOWN);
KeyCombination kc1=new KeyCharacterCombination("N",KeyCombination.CONTROL_DOWN);
KeyCombination kc2=new KeyCharacterCombination("O",KeyCombination.CONTROL_DOWN);
KeyCombination kc3=new KeyCharacterCombination("P",KeyCombination.CONTROL_DOWN);
//KeyCombination kc4=new KeyCharacterCombination("S",KeyCombination.CONTROL_DOWN);
public static Runnable rn,rn1,rn2,rn3;
    @Override
    public void start(Stage stage) throws Exception {
       //Parent root = FXMLLoader.load());
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        loader.setControllerFactory(t -> new FXMLDocumentController(new EditorModel()));//????
      Scene scene = new Scene(loader.load());
       
            
     
        stage.setScene(scene);
    scene.getAccelerators().put(kc,rn);
    scene.getAccelerators().put(kc1,rn1);
    scene.getAccelerators().put(kc2,rn2);
    scene.getAccelerators().put(kc3,rn3);
    
        stage.setTitle("Untitled Window-NoteIt");
        this.stage=stage;
         Platform.setImplicitExit(false);
        //stage.initStyle(StageStyle.UNDECORATED);
       
        stage.setOnCloseRequest(event -> {
            if(!(textare.getText().matches(oldData))){
                //oldData=textare.getText();
               //FXMLDocumentController f=new FXMLDocumentController(model); 
               event.consume();
                     stage.onCloseRequestProperty();
             
            Parent parent=null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
                } catch (IOException ex) {
                    
                }
Stage stai = new Stage();
p=1;
stai.initOwner(stage);
stai.setTitle("Title");
stai.initModality(Modality.APPLICATION_MODAL);//to avoid parent stage to be closed before child syage
stai.setScene(new Scene(parent));
stai.show();
stai.resizableProperty().setValue(Boolean.FALSE);    
sta=stai;
stai.setOnCloseRequest((WindowEvent we) -> {
             a=0;
            }); 
            }
} );
       
        stage.show();
        Platform.setImplicitExit(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
