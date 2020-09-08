package com.shanwen.select.utils;

import com.shanwen.select.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * 把类别list转成树
 */
public class TreeUtils {

    //把一个List转成树
    public static List<Category> buidTree(List<Category> list) {
        List<Category> tree = new ArrayList<>();
        for (Category node : list) {
            if (node.getParent() == 0) {
                tree.add(findChild(node, list));
            }
        }
        return tree;
    }

    static Category findChild(Category node, List<Category> list) {
        for (Category n : list) {
            if (n.getParent() == node.getCategoryId()) {
                if (node.getCategoryList() == null) {
                    node.setCategoryList(new ArrayList<Category>());
                }
                node.getCategoryList().add(findChild(n, list));
            }
        }
        return node;
    }


}
