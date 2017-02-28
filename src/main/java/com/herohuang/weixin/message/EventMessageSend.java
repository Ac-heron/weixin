package com.herohuang.weixin.message;

import com.herohuang.weixin.util.XMLUtil;
import com.herohuang.weixin.bean.TextMessage;

import java.util.Date;
import java.util.Map;

/**
 * Created by Acheron on 2015/12/4.
 */
public class EventMessageSend implements IMessage {
    @Override
    public String sendMessage(Map<String, String> map) {
        String event = map.get("Event");
        String toUserName = map.get("ToUserName");
        String fromUserName = map.get("FromUserName");
        String msgType = map.get("MsgType");
        String content = map.get("Content");

        TextMessage text = new TextMessage();


        if ("subscribe".equals(event)) {
            text.setFromUserName(toUserName);
            text.setToUserName(fromUserName);
            text.setMsgType(MessageType.MsgType.text.toString());
            text.setCreateTime(new Date().getTime() + "");
            text.setContent("哟哟哟，切克闹，欢迎关注地球上最特殊的公众号，在这里有你想要的一切\n回复1:了解一切~\n回复2:让我夸你~ ");
        } else if ("unsubscribe".equals(event)) {

        }

        String message = XMLUtil.textMessageToXml(text);
        return message;
    }
}

