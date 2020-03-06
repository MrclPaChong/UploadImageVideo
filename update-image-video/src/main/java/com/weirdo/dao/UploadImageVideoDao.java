package com.weirdo.dao;

import com.weirdo.model.UploadImageVideo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weirdo
 * @version 1.0
 * @date 2020/3/5 13:02
 */
@Mapper
@Component
public interface UploadImageVideoDao {

    //插入
    @Insert({"insert into upload_image_video (name,filePath,url) values (#{name},#{filePath},#{url})"})
    public int insertUrl(@Param("name")String name,@Param("filePath")String lujing,@Param("url")String url);

    //查询
    @Select("select * from upload_image_video")
    public List<UploadImageVideo> selectShipin();

}
