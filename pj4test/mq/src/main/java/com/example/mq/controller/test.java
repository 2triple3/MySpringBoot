package com.example.mq.controller;

public class test {
    public static void main(String[] args) {
        //System.out.println(a());
        bb x = new bb();
        System.out.println(x.b);

    }
    public static  int a(){
        try{
            System.out.println(0);
            return 1;
        }finally{
            System.out.println(2);
        }
    }
}
