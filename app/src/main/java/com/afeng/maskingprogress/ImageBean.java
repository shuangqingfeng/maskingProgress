package com.afeng.maskingprogress;

/**
 * @author Created by fengunion
 * @Description:
 * @date 2018/1/9
 */

public class ImageBean {
    private int percent;
    private String tag;

    @Override
    public String toString() {
        return "ImageBean{" +
                "percent=" + percent +
                ", tag='" + tag + '\'' +
                '}';
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
