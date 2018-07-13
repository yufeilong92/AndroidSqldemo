package com.example.dell.myapplication;

/**
 * @version V 1.0 xxxxxxxx
 * @Title: Student.java
 * @Package com.example.dell.myapplication
 * @Description: todo
 * @author: YFL
 * @date: 2018/7/13 20:25
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2018/7/13 星期五
 * 注意：本内容仅限于学川教育有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class Student {
    private int id;
    private float pricet;
    private String name;
    private long fileSize;
    private int number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPricet() {
        return pricet;
    }

    public void setPricet(float pricet) {
        this.pricet = pricet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
