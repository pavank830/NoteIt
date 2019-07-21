
package noteit;

public class Result<T> {
    private T data;
    private boolean b;
    public Result(boolean b,T data){
        this.b=b;
        this.data=data;
    }
    public boolean isOk(){
        return b;
    }
    public boolean hasdata(){
        return data !=null;
    }
    public T getData(){
        return data;
    }
}
