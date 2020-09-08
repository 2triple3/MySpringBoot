import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Java8 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>();
//        list.add("aaa");
//        list.add("bb");
//        list.forEach(n-> System.out.println(n));

//        new Thread(()-> System.out.println("lambda表达式开启线程")).start();


        JButton show =  new JButton("Show");
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });



    }


}
