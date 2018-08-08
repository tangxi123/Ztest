package testcases;

import base.TestDataProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import object.Result;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ArchiveQueryCharts {
    private static String result;
    private static String expectsData;
    private static Result expectedResult;
    private static Result actualResult;
    private static ObjectMapper mapper = new ObjectMapper();
    @BeforeClass
    public void setUp() throws IOException, SQLException {
        //获取预期结果
        ResultSet expects = TestDataProvider.getParameters("archiveQueryCharts", "important");
        while(expects.next()){
            expectsData = expects.getString("ExpectedResults");
            System.out.println(expectsData);
        }
        if(expects != null){
            try{
                expects.close();
            }catch(SQLException sqlEx){}
            expects = null;
        }
        ObjectMapper mapperExpected = new ObjectMapper();
        expectedResult = mapperExpected.readValue(expectsData, Result.class);
        System.out.println(expectedResult.getCode());
        System.out.println(expectedResult.getMessage());
        System.out.println(expectedResult.getData().get("lkjbStat"));

        //建立网络连接
        URL url = new URL("http://192.168.31.100:8011/web-api/archive/query/charts?type=important");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式和头信息
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setRequestProperty("From","web");
        conn.setRequestProperty("Authorization","eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJmdWxsTmFtZSI6Iua1i-ivlTEyMiIsInJvbGVJZCI6MSwicm9sZSI6IlBST1ZJTkNFX0FETUlOIiwicm9sZU5hbWUiOiLnnIHljoXotoXnuqfnrqHnkIblkZgiLCJhcmVhIjo1MTAwMDAwMDAwMDAsIm9yZ0xldmVsIjoiUFJPVklOQ0UiLCJvcmdJZCI6MTcyLCJvcmdDb2RlIjo1MTAwMDAwMDAwMDAsInVzZXJJZCI6MjksInN1YiI6InRlc3QiLCJpZENhcmQiOiI1MTAxMDYxOTk0MDMzMDQ0MjYiLCJpc3MiOiJCQkQiLCJhdWQiOiJHQVRFV0FZIiwibmJmIjoxNTMzNjI1NTYyLCJpYXQiOjE1MzM2MjU1NjIsImV4cCI6MTUzMzcxMTk2Mn0.c7eI4HKfVIW_EdUIpS_y1tQmgJ8UAFRNzWjSrX-4SKA");
        //设置参数
        conn.setDoOutput(true);
        //设置超时
        conn.setConnectTimeout(5000);
        //读取返回内容
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        result = content.toString();

    }
    @Test
    public void givenArchiveCounts_whenImportant_thenTrue(){
        //实际结果
        System.out.println(result);
        ObjectMapper mapper = new ObjectMapper();
        try {
            actualResult = mapper.readValue(result, Result.class);
            System.out.println(actualResult.getCode());
            System.out.println(actualResult.getMessage());
            System.out.println(actualResult.getData().get("lkjbStat"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(actualResult.toString(),expectedResult.toString());


    }
    @AfterClass
    public void tearDown(){
        //扫尾
        try {
            TestDataProvider.wirteActualResults(actualResult,"archiveQueryCharts", "important");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
