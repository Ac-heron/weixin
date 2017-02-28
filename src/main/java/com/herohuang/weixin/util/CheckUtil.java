package com.herohuang.weixin.util;

import java.util.Arrays;

/**
 * Created by Acheron on 2015/11/30.
 */
public class CheckUtil {
       
    private static final String token = "iacheron";

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
       //1. 将token、timestamp、nonce三个参数进行字典序排序
       String args[] = new String[]{token,timestamp,nonce};
       Arrays.sort(args);
        
       //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
           stringBuffer.append(args[i]); 
        }
        String shaStr = Sha1Util.getSha1(stringBuffer.toString());
        
       //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return shaStr.equals(signature);
    }
}
