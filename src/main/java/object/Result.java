package object;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class Result {
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
    @Override
    public String toString(){
        return  "code = "+ code +
                "message = " + message +
                "data = " + data.toString();
    }
}
