package com.example.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

public class BookInfo {
    //书本id
    int id;
    //书本阅读次数
    int count;
    //书本阅读时长
    long time;
    //书本类型
    String type;
    //书本封面
    int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public BookInfo(int id, int count, long time, String type) {
        this.id = id;
        this.count = count;
        this.time = time;
        this.type = type;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", count=" + count +
                ", time=" + time +
                '}';
    }

    public static List<BookInfo> data1() {
        List<BookInfo> list = new ArrayList<>();
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 70, 20l, "言情"));
        return list;
    }

    public static List<BookInfo> data2() {
        List<BookInfo> list = new ArrayList<>();
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 72, 20l, "言情"));
        list.add(new BookInfo(12, 103, 20l, "名著3"));
        list.add(new BookInfo(12, 30, 20l, "名著2"));
        list.add(new BookInfo(12, 141, 20l, "漫画5"));
        list.add(new BookInfo(12, 70, 20l, "言情4"));
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 33, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 70, 20l, "言情"));
        list.add(new BookInfo(12, 100, 20l, "名著3"));
        list.add(new BookInfo(12, 30, 20l, "名著2"));
        list.add(new BookInfo(12, 140, 20l, "漫画5"));
        list.add(new BookInfo(12, 70, 20l, "言情4"));
        return list;
    }
    public static List<BookInfo> data3() {
        List<BookInfo> list = new ArrayList<>();
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        return list;
    }
    public static List<BookInfo> data4() {
        List<BookInfo> list = new ArrayList<>();
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        list.add(new BookInfo(12, 100, 20l, "名著"));
        list.add(new BookInfo(12, 30, 20l, "名著"));
        list.add(new BookInfo(12, 140, 20l, "漫画"));
        return list;
    }

}
