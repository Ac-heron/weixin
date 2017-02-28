package com.herohuang.weixin.message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Acheron on 2015/12/4.
 */
public class MessageType {

    public enum MsgType{
        text,image,voice,video,shortvideo,location,link,event,news;
    }

    private static Map<String, IMessage> map = new HashMap<String, IMessage>();
    static {
        map.put(MsgType.text.toString(),new TextMessageSend());
        map.put(MsgType.event.toString(),new EventMessageSend());
        map.put(MsgType.news.toString(),new NewsMessageSend());
    }
    public static IMessage getMessageSend(String type){
        return map.get(type);
    }
    
    
    
    
  
}
