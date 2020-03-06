package com.weirdo.controller;

import com.weirdo.model.UploadImageVideo;
import com.weirdo.response.BaseResponse;
import com.weirdo.response.StatusCode;
import com.weirdo.service.UploadImageVideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class MyfileCOntroller {


    @Value("${cbs.imagesPath}")
    private String mImagesPath;

    //引入日志
    Logger log= LoggerFactory.getLogger(MyfileCOntroller.class);

    @Autowired
    private UploadImageVideoService uploadImageVideoService;

     private String  url;



    @RequestMapping("/file")
    public String file(){
        System.out.print("================请求路径===跳转file页面====="+"\n");
        return "/file";

    }


    @RequestMapping(value="/uploadFile",produces="application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse uploadFile(@RequestParam("fileName") MultipartFile file) {

        //判断文件是否为空
        if (file.isEmpty()) {
            log.info("上传文件为空！！");
            return new BaseResponse(StatusCode.FileIsNull);
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        log.info("上传的文件名为: "+fileName+"\n");
        //防止文件名重复加时间戳
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        log.info("（加个时间戳，尽量避免文件名称重复）保存的文件名为: "+fileName+"\n");


        //加个时间戳，尽量避免文件名称重复
        //String path = "E:/fileUpload/" +fileName;
        String path = "/opt/UploadImageVideo/file/"+fileName;
        //文件绝对路径
        log.info("保存文件绝对路径"+path+"\n");

        //创建文件路径
        File dest = new File(path);

        //判断文件是否已经存在
        if (dest.exists()) {
            return new BaseResponse(StatusCode.FielExist);
        }

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            //上传文件
            file.transferTo(dest);

            log.info("保存文件路径"+path+"\n");
            //正式项目
            //url="http://你自己的域名/项目名/images/"+fileName;
            //本地运行项目
            //url="http://127.0.0.1:8188/images/"+fileName;

            url="http://148.70.156.132:8188/"+fileName;
            //保存到数据库
            int res = uploadImageVideoService.insertUrl(fileName,path,url);

            log.info("保存的完整path(文件的访问路径)===="+path+"\n");
            log.info("保存的完整url(文件真实存在路径)===="+url+"\n");
        } catch (IOException e) {
            return new BaseResponse(StatusCode.FailPath);
        }

        return new BaseResponse(200,"上传成功",url);
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/query")
    @ResponseBody
    public List<UploadImageVideo> huizhiDuanxin(){
        System.out.print("查询视频"+"\n");
        List<UploadImageVideo> list=uploadImageVideoService.selectShipin();
        return list;
    }

}