package com.zzx.task.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import com.zzx.task.mapper.*;
import com.zzx.task.pojo.*;
import com.zzx.task.utils.DateUtils;
import com.zzx.task.utils.SmbFileUtil;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank;
import static com.baomidou.mybatisplus.core.toolkit.StringUtils.isEmpty;
@Slf4j
@RestController
public class Test {

    @Autowired
    private F560431Mapper f560431Mapper;

    @RequestMapping("/test")
    public void ReadP1Safe() throws IOException {


        //删除旧数据
        f560431Mapper.DeleteF560431();


        //	获取共享文件夹csv文件
        String url = "172.16.6.20/file/BI/数据中心/正式开发文档/Script/PMGDataQVD/看板用数据";
        SmbFile file = SmbFileUtil.GetSmbFile(url, "历史紧固件排程达成率.csv");

        // 转SmbFlie 转换成 File
        File tempFile = File.createTempFile("历史紧固件排程达成率", "CSV");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(new SmbFileInputStream(file), out);
        }


        // 将 CSV 转换成 Bean 对象
        CsvReader reader = CsvUtil.getReader();
        List<F560431> f560533List = reader.read(ResourceUtil.getUtf8Reader(tempFile.getPath()), F560431.class);


        for (F560431 f : f560533List) {
            f.setMesoqs(Convert.toStr(Convert.toLong(f.getMesoqs())*1000000));
            f.setMeuorg(Convert.toStr(Convert.toLong(f.getMeuorg())*1000000));
            if(f.getMedrid2().length()<= 12){
                f560431Mapper.InsertF560431(f);
            }
        }
        reader.close();
        tempFile.delete();
        file.delete();

    }
}
