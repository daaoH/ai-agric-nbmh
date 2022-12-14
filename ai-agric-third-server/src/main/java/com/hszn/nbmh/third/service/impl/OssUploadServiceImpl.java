package com.hszn.nbmh.third.service.impl;

import com.hszn.nbmh.common.core.mould.OssData;
import com.hszn.nbmh.third.service.OssUploadService;
import com.hszn.nbmh.third.utils.AliyunOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Slf4j
@Service("ossUploadService")
public class OssUploadServiceImpl implements OssUploadService {

    private final AliyunOSSUtil aliyunOSSUtil;

    @Autowired
    public OssUploadServiceImpl(AliyunOSSUtil aliyunOSSUtil) {
        this.aliyunOSSUtil = aliyunOSSUtil;
    }

    @Value("${oss.filePath}")
    private String filePath;

    private static final List<String> IMAGES_TYPE = List.of("png", "jpg", "jpeg", "bmp", "gif", "webp", "tiff");

    private static final List<String> VIDEOS_TYPE = List.of("mp4", "avi", "flv", "mov");


    @Override
    public OssData upload(MultipartFile file) {
        // 返回客户端文件系统中的原始文件名
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try {
            OssData ossData = new OssData();
            if (file != null) {

                ossData.setFileName(fileName);
                // 判定文件名是否为 ""
                if (!"".equals(fileName.trim())) {
                    File newFile = new File(fileName);
                    FileOutputStream os = new FileOutputStream(newFile);
                    // 以字节数组的形式返回文件的内容,再输出到文件输出流中
                    os.write(file.getBytes());
                    os.close();
                    // 将接受的文件传输到给定的目标文件 file-->newFile
                    file.transferTo(newFile);
                    // 根据不同文件 类型/日期 生成不同的文件夹
                    String datePath = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                    String timeStamp = String.valueOf(System.currentTimeMillis());
                    String fileType = getFileType(fileName);
                    ossData.setType(fileType);
                    fileName = timeStamp + "." + fileType;
                    String path;
                    //图片格式 png、jpg、jpeg、bmp、gif、webp、tiff
                    if (IMAGES_TYPE.contains(fileType)) {
                        path = filePath + "images/" + datePath + fileName;
                        ossData.setPath(path);
                    }
                    //mp4、avi、flv、mov 视频格式
                    if (VIDEOS_TYPE.contains(fileType)) {
                        path = filePath + "videos/" + datePath + fileName;
                        ossData.setPath(path);
                    } else {
                        path = filePath + "other/" + datePath + fileName;
                        ossData.setPath(path);
                    }
                    // 上传到OSS
                    String uploadUrl = aliyunOSSUtil.upLoad(newFile, path);
                    newFile.delete();
                    if (uploadUrl != null) {
                        //拼接下载路径全路径
                        ossData.setPath("https://nbmhoss.oss-cn-shanghai.aliyuncs.com/" + ossData.getPath());
                        return ossData;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件类型
     * <p>
     * 例如: yyx.txt, 返回: txt
     *
     * @param fileName 文件名
     * @return 后缀（不含".")
     */
    private String getFileType(String fileName) {
        int separatorIndex = fileName.lastIndexOf(".");
        if (separatorIndex < 0) {
            return "";
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }
}
