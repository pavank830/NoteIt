
package noteit;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static noteit.FXMLController.b;
import static noteit.FXMLController.c;
import static noteit.FXMLController.d;
import static noteit.NoteIt.stage;
import static noteit.NoteIt.rn;
import static noteit.NoteIt.rn1;
import static noteit.NoteIt.rn2;
import static noteit.NoteIt.rn3;
import org.controlsfx.dialog.FontSelectorDialog;

public class FXMLDocumentController implements Initializable {

    @FXML
    private MenuBar mb;
    @FXML
    private AnchorPane pane;
    @FXML
    private TextArea textarea;
    public static TextArea textare;
    public static EditorModel model;
    public static TextFile currentTextFile;
    public static String oldData;
    public static String newData;
    String newpath;
    public static Stage sta;
   public static EditorModel mod;
   public static int a,n,o,cl;
   
   
    public FXMLDocumentController(EditorModel model){
        this.model=model;
           
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        mb.prefWidthProperty().bind(pane.widthProperty());//menubar resizes it's widht w.r.t window
        textarea.prefWidthProperty().bind(pane.widthProperty());//textarea resizes it's width w.r.t window
        textarea.prefHeightProperty().bind(pane.heightProperty());//textarea resizes it's height w.r.t window
        textarea.setWrapText(true);//Automatically text goes to new line at end of each line
    oldData=textarea.getText();
    newData=oldData;
    textare=textarea;
   a=0;
  n=1;
   o=1;
   cl=1;
   rn = () -> {try {
                save(new ActionEvent());
            } catch (IOException ex) {
                //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        };
      rn1 = () -> {try {
                newf(new ActionEvent());
            } catch (IOException ex) {
                //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        };
         rn2 = () -> {try {
                open(new ActionEvent());
            } catch (IOException ex) {
                //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        };
         rn3 = () -> {
                print(new ActionEvent());
              
        };
    }    
 

    @FXML
    private void close(ActionEvent event) throws IOException {
        a=1;
        String g="";
        if((stage.getTitle()).matches("Untitled Window-NoteIt")&&(textarea.getText().matches(".+"))){
       change(event,"FXML.fxml");
    
        }
        else if((textarea.getText().matches(oldData)&&oldData.isEmpty())){
            stage.close();
        }
        else if(!(newData.matches(oldData))){
         change(event,"FXML.fxml");
         
    }
        else{
        
            stage.close(); 
        }
       if(cl==1)
        {
        newData=textare.getText();
        cl=0;
        }
 
    }
     

    @FXML
    public void newf(ActionEvent event) throws IOException {
        System.out.print("qwer");
        //final int i=0;
       
        if(a==0){
         if((stage.getTitle()).matches("Untitled Window-NoteIt")&&(textarea.getText().matches(".+"))){
       change(event,"FXML.fxml");
        System.out.print("qwer1");
        
         }
         else if((textarea.getText().matches(oldData)&&oldData.isEmpty())){
            b=0;
        }
        else if((!(newData.matches(oldData)))){
         change(event,"FXML.fxml");
         }
        
         a=2;
        }
        
        if(b==0){
            System.out.print("qwer2");
        textarea.clear();
        stage.setTitle("Untitled Window-NoteIt");
        a=0;
        }
        if(n==1)
        {
        newData=textare.getText();
        n=0;
        }
    }

    @FXML
    public void open(ActionEvent event) throws IOException {
      if(a==0){
         if((stage.getTitle()).matches("Untitled Window-NoteIt")&&(textarea.getText().matches(".+"))){
       change(event,"FXML.fxml");
        }
          else if((textarea.getText().matches(oldData)&&oldData.isEmpty())){
          d=0;
        }
        else if(!(newData.matches(oldData))){
         change(event,"FXML.fxml");
    }
         a=3;
      }
       if(d==0){
        Path pathOfFile=null;
        FileChooser fileChooser=filechoose();
        File file=fileChooser.showOpenDialog(stage);
       
       if(file!=null){
              pathOfFile=file.toPath();
               String nm=pathOfFile.toString();
              filename(nm);
            Result<TextFile> io=model.load(file.toPath());
            
            if(io.isOk()){//&&io.hasdata()){
                currentTextFile=io.getData();
                textarea.clear();
                currentTextFile.getContent().forEach(line -> textarea.appendText(line+"\n"));
                textarea.positionCaret(0);
               // newData=textare.getText();
      
       }
         else{
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("File Open");
            alert.setHeaderText("ERROR");
            alert.setContentText("Error in opening a file");
              alert.show();
              stage.close();
        }
       }
       a=0;
       }
    
         if(o==1)
        {
        newData=textare.getText();
        o=0;
        }
    }

    @FXML
    public  void save(ActionEvent event) throws IOException {
        
        if((stage.getTitle()).matches("Untitled Window-NoteIt")&&(textarea.getText().matches(".+"))){
          saveas(event);
        // System.out.print(textarea.getText());
        }
         else {
        TextFile textFile=new TextFile(currentTextFile.getPath(),Arrays.asList(textarea.getText().split("\n"))); 
        model.save(textFile);
        
         }
   /*can be written directly also by commenting above two lines
        try{
        Files.write(currentTextFile.getPath(),Arrays.asList(textarea.getText().split("\n")));//write the contents to file
                                                             // whose path of that file is specified as the first parameter
                                                             //and content as second parameter
        }
        catch(IOException e){
            e.printStackTrace();
        }
        */
  // currentTextFile=null;
  newData=textare.getText();
    }

    @FXML
    public void saveas(ActionEvent event) throws IOException {
        
          FileChooser fileChooser=filechoose();
      
        File file=fileChooser.showSaveDialog(stage);
        if(file!=null){
         newpath=file.getAbsolutePath();
       
        File newfile=new File(newpath);
        
        if(newfile.createNewFile()){
           filename(newpath);
          TextFile textFile=new TextFile(file.toPath(),Arrays.asList(textarea.getText().split("\n"))); 
          currentTextFile=textFile;
        model.save(textFile);
        c=1;
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Create");
            alert.setHeaderText("ERROR");
            alert.setContentText("Error in creating a file");
            alert.show();
        }
        }
      newData=textare.getText();  
    }
    public FileChooser filechoose(){
          FileChooser fileChooser =new FileChooser();//FileChooser can be used to invoke file open dialogs 
                                                //for selecting single or mutiple files
        
        fileChooser.setInitialDirectory(new File("./"));
        FileChooser.ExtensionFilter exfilter=new FileChooser.ExtensionFilter("Only Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(exfilter);
        return fileChooser;
    }
    public void filename(String nm){
       
        int lastslash=nm.lastIndexOf("\\");
        String a=nm.substring(lastslash+1);
        
        if(a==null){
        stage.setTitle("Untitled Window-NoteIt");
        }
        else{
           stage.setTitle(a); 
        }
    }
     public  void change(ActionEvent e,String res) throws IOException  
    {
            Parent parent = FXMLLoader.load(getClass().getResource(res));
Stage sta = new Stage();
sta.initOwner(stage);
sta.setTitle("Title");
sta.initModality(Modality.APPLICATION_MODAL);//to avoid parent stage to be closed before child syage
sta.setScene(new Scene(parent));
sta.show();
sta.resizableProperty().setValue(Boolean.FALSE);    
this.sta=sta;
sta.setOnCloseRequest((WindowEvent we) -> {
             a=0;
            }); 
textare=textarea;
    }
     public void test(TextArea textar){
         textarea=textar;
     }

    @FXML
    private void print(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
if (job != null && job.showPrintDialog(stage)){
        
    
}
job.endJob();
    }

    @FXML
    private void page(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();
if (job != null && job.showPageSetupDialog(stage)){
        
   // 
}
job.endJob();
    }

    @FXML
    private void font(ActionEvent event) {
       Font font=null;
      FontSelectorDialog fs=new FontSelectorDialog(textarea.getFont());
      
      font=fs.getResult();
      fs.setTitle("Font");
      fs.show();
      if(font!=null)
      textarea.setFont(font);
    }
   
  
    
}
//pending
/*
save as
new
edit 
help
*/

