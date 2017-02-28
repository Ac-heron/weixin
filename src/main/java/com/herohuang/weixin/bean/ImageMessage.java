package com.herohuang.weixin.bean;

/**
 * Created by Acheron on 2015/12/9.
 */
public class ImageMessage extends  BaseMessage {
    private Image Image;

    public com.herohuang.weixin.bean.Image getImage() {
        return Image;
    }

    public void setImage(com.herohuang.weixin.bean.Image image) {
        Image = image;
    }
}
