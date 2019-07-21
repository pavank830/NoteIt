
package noteit;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import static noteit.FXMLDocumentController.a;
import static noteit.FXMLDocumentController.currentTextFile;
import static noteit.FXMLDocumentController.model;
import static noteit.FXMLDocumentController.sta;
import static noteit.FXMLDocumentController.textare;
import static noteit.NoteIt.p;
import static noteit.NoteIt.stage;

public class FXMLController implements Initializable {


    @FXML
    private Text text;
   
   public static int b=0,d=0;
   public static int c=1,e=1;
    @FXML
    private Button savebtn;
    @FXML
    private Button notsavebtn;
    @FXML
    private Button cancelbtn;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            text.setText("Do you want to save changes to "+stage.getTitle()+" ?");
            text.setWrappingWidth(300);
            b=1;
            d=1;
            cancelbtn.defaultButtonProperty().bind(cancelbtn.focusedProperty());
             savebtn.defaultButtonProperty().bind(savebtn.focusedProperty());
              notsavebtn.defaultButtonProperty().bind(notsavebtn.focusedProperty());
            //cancelbtn.defaultButtonProperty().bind(e -> {});
    }    

    @FXML
    private void save(ActionEvent event) throws IOException {
        e=0;
        cancel(event);
        FXMLDocumentController f=new FXMLDocumentController(model);
       
        if((stage.getTitle()).matches("Untitled Window-NoteIt")){
           f.test(textare);
            c=0;
          f.saveas(event);
         
        }
       else{
        TextFile textFile=new TextFile(currentTextFile.getPath(),Arrays.asList(textare.getText().split("\n"))); 
        model.save(textFile);
       
         }
         if(p==1&&c==1){
            stage.close();
            System.exit(0);
            p=0;
        }
        if(p!=1){
        while(true){
        if(a==1){
            stage.close();
            a=0;
            break;
        }
        else if(a==2){
             
             if(c==1){
                 b=0;
             f.test(textare);
             f.newf(event);
             b=1;
             
             }
             a=0;
             break;
        }
        else if(a==3){
             if(c==1){
                 d=0;
             f.test(textare);
             f.open(event);
             d=1;
             
             }
             a=0;
             break;
            
        }
        }
        }
       
    }

    @FXML
    private void dontsave(ActionEvent event) throws IOException {
        e=0;
        cancel(event);
        FXMLDocumentController f=new FXMLDocumentController(model);
        if(a==1||p==1){
            stage.close();
        }
        else if(a==2){
             b=0;
              f.test(textare);
            f.newf(event);
            a=0;
        }
         else if(a==3){
             d=0;
              f.test(textare);
            f.open(event);
            a=0;
        }
    }

    @FXML
    private void cancel(Event event) {
        sta.close();
        
    }
    

    
}
