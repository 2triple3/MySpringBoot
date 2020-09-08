package com.shanwen.select.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanwen.select.entity.Content;
import com.shanwen.select.mapper.ContentMapper;
import com.shanwen.select.service.ContentService;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

}
