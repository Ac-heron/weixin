package com.herohuang.weixin.message;

import com.herohuang.weixin.bean.*;
import com.herohuang.weixin.util.XMLUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Acheron on 2015/12/4.
 */
public class TextMessageSend implements IMessage {
    @Override
    public String sendMessage(Map<String ,String > map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");
        
      
        String message = ""; 
        if("1".equals(content)){
            TextMessage text = new TextMessage();
            text.setFromUserName(toUserName);
            text.setToUserName(fromUserName);
            text.setMsgType(msgType);
            text.setCreateTime(new Date().getTime() + "");
            text.setContent("你当我是神仙哦,我读书少。。。");
            message = XMLUtil.textMessageToXml(text);
        }else if("2".equals(content)){
            TextMessage text = new TextMessage();
            text.setFromUserName(toUserName);
            text.setToUserName(fromUserName);
            text.setMsgType(msgType);
            text.setCreateTime(new Date().getTime() + "");
            text.setContent("是的这个秘密我知道很久了，你长得真好看");
            message = XMLUtil.textMessageToXml(text);
        }else if("3".equals(content)){
            //发送图片消息
            Image image = new Image();
            image.setMediaId("n5sItfUhLsyulKKiS2IWjhwIdKvta-1lMPfY9WLHTPBTMKkhlZzWKdRk1TT41Tnw");
            ImageMessage imageMessage = new ImageMessage(); 
            imageMessage.setFromUserName(toUserName);
            imageMessage.setToUserName(fromUserName); 
            imageMessage.setMsgType(MessageType.MsgType.image.toString());
            imageMessage.setCreateTime(new Date().getTime()+"");
            imageMessage.setImage(image);
            message = XMLUtil.imageMessageToXml(imageMessage);
        }else{
            //测试图文消息
            NewsMessage newsMessage = new NewsMessage();
            newsMessage.setFromUserName(toUserName);
            newsMessage.setToUserName(fromUserName);
            newsMessage.setMsgType(MessageType.MsgType.news.toString());
            newsMessage.setCreateTime(new Date().getTime() + "");

            List<News> newsList = new ArrayList<News>();
            News new1 = new News();
            new1.setTitle("我的网站");
            new1.setDescription("一个有格调的网站，这里是测试描述。。。");
            new1.setPicUrl("http://www.iacheron.com/wp-content/uploads/2015/09/2015.09.24_11h01m16s_002_.jpg");
            new1.setUrl("www.iacheron.com");
            newsList.add(new1);
            newsMessage.setArticles(newsList);
            newsMessage.setArticleCount(newsList.size());
            message = XMLUtil.newsMessageToXml(newsMessage);
        }

        return message;
    }
}