package com.awen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-13 2:32
 */
@Data
//有参数
//无参数构造
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String title;
    private String img;
    private String price;
    private String shop;
}
