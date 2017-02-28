package com.herohuang.weixin.message;

import java.util.Map;

/**
 * Created by Acheron on 2015/12/4.
 */
public class NewsMessageSend implements IMessage {
    @Override
    public String sendMessage(Map<String ,String > map) {
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");
        
      

        return null;
    }
}