package com.herohuang.weixin.util;

import java.util.Date;
import java.util.Random;

public class OrderUtil {


    /**
     * 生成商户订单号
     * @return 商户订单号
     */
    public static String generate_out_trade_no() {
        String currTime = TenpayUtil.getCurrTime();
        String strRandom = TenpayUtil.buildRandom(10) + "";
        return currTime+strRandom;
    }

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String GetTimestamp() {
		return Long.toString(new Date().getTime() / 1000);
	}

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public static String CreateNoncestr() {
		Random random = new Random();
		return MD5.GetMD5String(String.valueOf(random.nextInt(10000)));
	}
}
