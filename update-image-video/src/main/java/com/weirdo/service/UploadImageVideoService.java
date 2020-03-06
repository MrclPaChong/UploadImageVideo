package com.weirdo.service;
import com.weirdo.dao.UploadImageVideoDao;
import com.weirdo.model.UploadImageVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Component
public class UploadImageVideoService {

    @Autowired
    private UploadImageVideoDao uploadImageVideoDao;

    //插入
    public int insertUrl(String name,String lujing,String url){
        System.out.print("开始插入=name=="+name+"\n");
        System.out.print("开始插入=lujing=="+lujing+"\n");
        System.out.print("开始插入=url=="+url+"\n");
        int jieguo=uploadImageVideoDao.insertUrl(name,lujing,url);
        System.out.print("插入结果==="+jieguo+"\n");
        return jieguo;
    }


    //查询
    public List<UploadImageVideo> selectShipin(){
        List<UploadImageVideo> uploadImageVideoList=uploadImageVideoDao.selectShipin();
        return  uploadImageVideoList;
    }
}