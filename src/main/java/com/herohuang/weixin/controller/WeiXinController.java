package com.herohuang.weixin.controller;

import com.herohuang.weixin.message.MessageType;
import com.herohuang.weixin.service.WXPay;
import com.herohuang.weixin.service.WXPrepay;
import com.herohuang.weixin.util.CheckUtil;
import com.herohuang.weixin.util.OrderUtil;
import com.herohuang.weixin.util.XMLUtil;
import org.dom4j.DocumentException;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author herohuang
 * @dateTime 2015-11
 */
@Controller
@RequestMapping("/weixin")
public class WeiXinController extends BaseController {


    /**
     * 微信开发接入
     * API地址： http://mp.weixin.qq.com/wiki/16/1e87586a83e0e121cc3e808014375b74.html
     * @param request
     * @return
     */
   @RequestMapping(value = "/checkSignature.do",method = RequestMethod.GET)
   @ResponseBody
   public String checkSignature(String signature,String timestamp,String nonce,String echostr){
       if(CheckUtil.checkSignature(signature, timestamp, nonce)) return echostr; else return "";
   }

    /**
     * 接收消息
     * @param request
     * @return
     */

    @RequestMapping(value = "/checkSignature.do",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String resmes(HttpServletRequest request) {
        String message = null;
        try {
            Map<String, String> map = XMLUtil.xmlToMap(request);
            message = MessageType.getMessageSend(map.get("MsgType")).sendMessage(map);
           /* if("text".equals(msgType)){
                TextMessage text = new TextMessage();
                text.setFromUserName(toUserName);
                text.setToUserName(fromUserName);
                text.setMsgType(msgType);
                text.setCreateTime(new Date().getTime() + "");
                text.setContent("您发送的消息是" + content);

                message = XMLUtil.textMessageToXml(text);
               // System.out.println(message);
                
            }*/
           
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }




    /**
     * App端调用此接口，生成预支付交易单(prepay_id)
     * 取得预付单信息后，重新生成签名后将数据传输给APP
     * @param request 请求
     * @return 预支付返回结果
     */
    @RequestMapping("/getPrepay.do")
    @ResponseBody
    public String getPrepay(HttpServletRequest request) {
        WXPrepay prePay = new WXPrepay();
        prePay.setAppid("wxd678efh567hg6787");
        prePay.setBody("商品或支付单简要描述");
        prePay.setPartnerKey("partnerKey");
        prePay.setMch_id("partnerId");
        prePay.setNotify_url("http://www.iacheron.com/wx.do");
        prePay.setOut_trade_no(OrderUtil.generate_out_trade_no());
        prePay.setSpbill_create_ip(request.getRemoteAddr());
        prePay.setTotal_fee("1");
        prePay.setTrade_type("APP");

        String prepay_res = prePay.submitXmlGetPrepayId();
        String prepayid = Jsoup.parse(prepay_res).select("prepay_id").html();
        String res = "";
        
        if (null != prepayid && !"".equals(prepayid)) {
            res = WXPay.createPackageValue("wxd678efh567hg6787", "partnerKey", prepayid);
        }
        return res;
    }


}