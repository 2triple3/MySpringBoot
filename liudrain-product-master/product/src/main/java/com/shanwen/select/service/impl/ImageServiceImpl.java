package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Image;
import com.shanwen.select.mapper.ImageMapper;
import com.shanwen.select.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements IImageService {

    @Autowired
    ImageMapper imageMapper;
    @Value("${domain}")
    String domain;
    @Value("${imagePath}")
    String imagePath;

    @Override
    public String imageUrl(MultipartFile file) {

        File targetFile = null;
        String url = "";//返回存储路径
        String fileName = file.getOriginalFilename();//获取文件名加后缀
        if (fileName != null && fileName != "") {
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            //新的文件名
            fileName = new Date().getTime() + "_" + new Random().nextInt(1000) + fileF;
            //获取文件夹路径
            File file1 = new File(imagePath);
            //如果文件夹不存在则创建
            if (!file1.exists() && !file1.isDirectory()) {
                file1.mkdirs();
            }

            //将图片存入文件夹
            targetFile = new File(file1, fileName);
            try {
                //将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                //赋予权限
               /* String command = "chmod 775 -R " + targetFile;
                Runtime runtime = Runtime.getRuntime();
                Process proc = runtime.exec(command);*/
                //生成文件地址
                url = domain + "/file/" +
                        fileName;
                return url;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
