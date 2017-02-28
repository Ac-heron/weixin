package com.herohuang.weixin.bean;

/**
 * Created by Acheron on 2015/12/2.
 */
public class TextMessage  extends BaseMessage{
    
    private String Content;
    private String MsgId;



    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
