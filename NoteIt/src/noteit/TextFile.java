
package noteit;
import java.nio.file.Path;
import java.util.List;
 //In this class the file path and content is sent to the object called TextFile
//inside whih the path of fiole is assigned to datatype Path variable name "file" and 
//and the content is stored in the datatype list(ordered collection of objects with no duplicates)
//
public class TextFile {
    private final  Path path;
    private final   List<String> content;
    public TextFile(Path path,List<String> content){
        this.path=path;
        this.content=content;
    }
    public Path getPath(){
        return path;
    }
    public List<String> getContent(){
        return content;
    }

   
}
