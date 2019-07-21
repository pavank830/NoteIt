
package noteit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EditorModel {
    public void save(TextFile textFile){
        try{
        Files.write(textFile.getPath(),textFile.getContent());//write the contents to file
      // whose path of that file is specified as the first parameter and content as second parameter
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public Result<TextFile> load(Path path){
       try{
           List<String> lines=Files.readAllLines(path);//read all thelines in the path specified
           return new Result<>(true,new TextFile(path,lines));//if there is content in the file
      // return true and the textFile(path,content) we can return textFile directly without using 
      //Result class but for the catch when an exception arrives we need to return a error condition so for that Result class is used 
       } 
       catch(IOException e){
           e.printStackTrace();
            return new Result<>(false,null);
       }
    }
   
}
