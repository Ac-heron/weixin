package com.herohuang;

import com.herohuang.weixin.bean.AccessToken;
import com.herohuang.weixin.util.WeixinUtil;

import java.io.IOException;

/**
 * Created by Acheron on 2015/12/8.
 */
public class WeixinTest {

    public static void main(String[] args) {
        AccessToken token = null;
        try {
            token = WeixinUtil.getAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("token："+token.getToken());
        System.out.println("有效时间"+token.getExpiresIn());
        
        //上传接口测试
        try {
            String mediaId = WeixinUtil.upload("D:/test.jpg", token.getToken(), "image");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
