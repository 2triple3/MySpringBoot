package com.awen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-13 0:01
 */
@Data
//有参数
//无参数构造
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String title;
    private String img;
    private String price;
    //可以自己添加属性
}
