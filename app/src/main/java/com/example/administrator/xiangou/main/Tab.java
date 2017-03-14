package com.example.administrator.xiangou.main;

/**
 * Created by zhouzongyao on 2017/2/28.
 */

public class Tab {
    private int title;
    private int icon;
    private Class frag;

    public Tab(int title, int icon, Class frag) {
        this.title = title;
        this.icon = icon;
        this.frag = frag;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getFrag() {
        return frag;
    }

    public void setFrag(Class frag) {
        this.frag = frag;
    }
}
