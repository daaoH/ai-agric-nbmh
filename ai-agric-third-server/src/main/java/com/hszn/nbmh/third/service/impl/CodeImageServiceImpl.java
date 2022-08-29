package com.hszn.nbmh.third.service.impl;

import com.google.zxing.WriterException;
import com.hszn.nbmh.common.core.mould.CodeImageRequest;
import com.hszn.nbmh.common.core.mould.OssData;
import com.hszn.nbmh.third.service.CodeImageService;
import com.hszn.nbmh.third.service.OssUploadService;
import com.hszn.nbmh.third.utils.CodeImageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class CodeImageServiceImpl implements CodeImageService {


    private final OssUploadService ossUploadService;

    @Override
    public String generate(CodeImageRequest param) {
        String url="http://www.baidu.com";
        String fileName=System.currentTimeMillis() + ".png";
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {
            out.toString("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] b=new byte[1024 * 4];
        int n=0;
        String binary=null;
        MultipartFile multipartFile=null;
        String path=null;  //此处返回的是上传至阿里云后的路径,可进行直接输入访问
        // 生成二维码图片
        try {
            CodeImageUtil.writeToStream(url, out, 300, 300);
            InputStream in=new ByteArrayInputStream(out.toByteArray());
            int length;
            while ((length=in.read(b)) > 0) {
                out.write(b, 0, n);
            }
            //转成字符数组
            byte[] bytes=out.toByteArray();
            //创建文件类型
            multipartFile=new MockMultipartFile(fileName, fileName, "", bytes);
            //调用工具类上传阿里云服务器
            OssData ossData=ossUploadService.upload(multipartFile);
            in.close();
            return ossData.getPath(); //返回地址
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
