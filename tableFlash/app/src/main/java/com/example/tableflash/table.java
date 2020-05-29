package com.example.tableflash;

public class table implements java.io.Serializable{
    private String id;
    private int capacity;
    private int length;
    private int width;
    public table(String id,int capacity,int length,int width){
        this.id=id;
        this.capacity=capacity;
        this.length=length;
        this.width=width;
    }

    public String getId(){
        return id;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getLength(){
        return length;
    }

    public int getWidth(){
        return width;
    }
}
