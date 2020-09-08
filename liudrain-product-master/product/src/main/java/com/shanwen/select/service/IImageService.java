package com.shanwen.select.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shanwen.select.entity.Image;
import com.shanwen.select.entity.Order;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService extends IService<Image> {

    String imageUrl(MultipartFile file);

}
