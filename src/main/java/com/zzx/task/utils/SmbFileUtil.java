package com.zzx.task.utils;

import jcifs.smb.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import static ch.qos.logback.core.db.DBHelper.closeConnection;

/**
 * java 通过 SmbFile 类操作共享文件夹
 */
public class SmbFileUtil {


    private SmbFile smbFile;
    private SmbFile[] files;

    /**
     * 获取共享盘指定文件
     * 传入文件名称，与文件所在文件夹路径
     *
     * @param remoteUrl
     */
    public  static SmbFile GetSmbFile(String remoteUrl,String fileName) throws IOException {

        //	此处采用优先验证登录用户信息的方法访问
        String ip="172.16.6.20";
        String url="smb://"+ remoteUrl+"/";
        String name="the\\zhongxiang.zhao";
        String password="Aa123456";
        NtlmPasswordAuthentication auth =new NtlmPasswordAuthentication(ip, name, password);
        SmbFile file = new SmbFile(url,auth);
        file.connect();
        if(file.exists()){
            SmbFile[] files = file.listFiles();
            for(SmbFile f : files){
                if(f.getName().equals(fileName)){
                    return f;
                }
            }
        }
        return null;
    }



}
