package com.herohuang.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.herohuang.weixin.bean.AccessToken;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Acheron on 2015/12/7.
 */
public class WeixinUtil {
    //测试号 
    private static  final String APPID="wx2e6dab1e6aee4df0";
    private static  final String APPSECRET="d4624c36b6795d1d99dcf0547af5443d";
    
    
/*    private static  final String APPID="wxbe719a71d4ccba6f";
    private static  final String APPSECRET="78b24d0e738047d99f784cfb95b662dd";*/
    private static  final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    //上传文件接口
    private static  final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    
    public static JSONObject doGetStr(String url) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity httpEntity = response.getEntity();
        
        if(httpClient != null) {
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            jsonObject = (JSONObject) JSONObject.parse(result);
        }
        response.close();
        return jsonObject;
    }

    public static JSONObject doPostStr(String url,String outStr) throws IOException {
        JSONObject jsonObject = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
       
        httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        
        HttpEntity httpEntity = response.getEntity();
        if(httpClient != null) {
            String result = EntityUtils.toString(httpEntity, "UTF-8");
            jsonObject = (JSONObject) JSONObject.parse(result);
        }
        response.close();
        return jsonObject;
    }
    
    
    public static AccessToken getAccessToken() throws IOException {
        AccessToken accessToken = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null) {
           accessToken.setToken(jsonObject.getString("access_token"));
           accessToken.setExpiresIn(jsonObject.getInteger("expires_in"));
        }
        return accessToken;
    }
    
    public static String upload(String filepath,String accessToken,String type) throws IOException {
        File file = new File(filepath);
        if(!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }
        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replaceAll("TYPE", type);

        URL urlObj = new URL(url);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
       
        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        
        //设置请求头信息
        con.setRequestProperty("Connection","Keep-Alive");
        con.setRequestProperty("Charset","UTF-8");
        //设置边界
        String boundary = "-------------"+System.currentTimeMillis();
        con.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);


        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition:form-data;name=\"file\";filename=\""+file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        
        byte[] head = sb.toString().getBytes("UTF-8");
        
        //获取输入流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        //输出表头
        out.write(head);
        
        //文件正文部分
        //把文件以流的方式推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while((bytes = in.read(bufferOut)) != -1){
             out.write(bufferOut,0,bytes);
        }
        in.close();
        //结尾，定义最后数据分隔线
        byte[] foot = ("\r\n--"+boundary+"--\r\n").getBytes("UTF-8"); 
        out.write(foot);
        out.flush();        
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        //读取url响应
        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line = null;
        while((line=reader.readLine()) != null) {
            buffer.append(line);
        }
        if(result ==null ) {
            result = buffer.toString();
        }
        if(reader != null ) {
            reader.close();
        }
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        System.out.println("jsonObject:--->"+jsonObject);
        String mediaId = jsonObject.getString("media_id");
        System.out.println(mediaId);
        return mediaId;
    }
    
    
}
