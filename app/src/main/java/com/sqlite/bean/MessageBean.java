package com.sqlite.bean;

/**
 * @author: xiaxueyi
 * @date: 2017-03-01
 * @time: 16:25
 * @说明:
 */
public class MessageBean {

    private int avatar=0;

    private String name;

    private String content;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

