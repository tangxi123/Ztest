package object;

import java.util.Map;

public class TokenResult {
    String code;
    String message;
    Map<String, Object> data;
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setData(Map<String, Object> data){
        this.data = data;
    }
    public Map<String, Object> getData(){
        return this.data;
    }
}
