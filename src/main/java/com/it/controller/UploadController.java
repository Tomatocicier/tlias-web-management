package com.it.controller;

import com.it.pojo.Result;
import com.it.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        //获取原始文件名，原始类型
//        String originalFilename = image.getOriginalFilename();
//        //获取唯一文件名(uuid)
//        int index = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(index);
//        String newFileName = UUID.randomUUID().toString();
//        //将文件存储
//        image.transferTo(new File("E:\\CloudMusic\\" + newFileName + extname));
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传,文件名:{}",image.getOriginalFilename());
        //调用阿里云OSS工具栏
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,url:{}",url);
        return Result.success(url);


    }
}
