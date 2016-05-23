package com.wind.data.base.bean;

import java.io.Serializable;

/**
 * Created by shi on 2015/9/26.
 */
public class Photo implements Serializable{
    private int id;
    //url
    private Img img;
    //文字说明
    private String text;
    //图片id
    private String ID;

    public Img getImg() {
        return img;
    }

    public void setImg(Img img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
