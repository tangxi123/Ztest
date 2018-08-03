package base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import object.TokenResult;
import util.ParameterBuilder;


import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



public class Token {
    private static String token = null;
    public static String getToken() throws IOException {

            URL url = new URL("http://192.168.31.100:8011/bbd-user/oauth/token");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置method和headers
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/json");
            conn.setRequestProperty("From","web");
            //设置parameters
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            User user = new User("test","123456");
            ObjectMapper mapper = ParameterBuilder.getParameterMapper(user);
            String str = mapper.writeValueAsString(user);
            out.writeBytes(str);
            out.flush();
            out.close();
            //设置超时
            conn.setConnectTimeout(5000);
            //读取返回内容
            int status = conn.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while((inputLine = in.readLine()) != null){
                content.append(inputLine);
            }
            in.close();
            String result = content.toString();

            ObjectMapper mapper1 = new ObjectMapper();
            TokenResult t = mapper1.readValue(result,TokenResult.class);
            token = t.getData().get("access_token").toString();
            return token;
    }

    public static void main(String[] args) throws IOException, IllegalAccessException {
        String s = Token.getToken();
        System.out.println(s);





    }

}
