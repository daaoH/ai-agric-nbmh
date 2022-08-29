package com.hszn.nbmh.third.service.impl;

import com.hszn.nbmh.common.core.mould.OssData;
import com.hszn.nbmh.third.service.ExcelService;
import com.hszn.nbmh.third.service.OssUploadService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Service
public class ExcelServiceImpl implements ExcelService {

    private final OssUploadService ossUploadService;

    @Override
    @Transactional
    public OssData exportExcel(Map<String, Object> map, Map<String, Object> exlParam) throws Exception {
        Template dateTmp;
        Writer fw;
        InputStream in;
        OutputStream out=null;
        // 此处需要给你个版本信息
        Configuration cfg=new Configuration(Configuration.VERSION_2_3_28);
        String tempFoldPath=(String) map.get("tempFoldPath"); // 模板所在的路径
        String file=(String) map.get("filePath");// 生成表格模板的路径
        String tampPath=(String) map.get("tampPath");// 模板名称
        String excelName=(String) map.get("excelName");// 最后生成表格的名称
        // **********初始化参数**********
        File tempFoldFile=new File(tempFoldPath);
        if (!tempFoldFile.exists()) {
            tempFoldFile.mkdirs();
        }
        cfg.setDirectoryForTemplateLoading(tempFoldFile);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateUpdateDelayMilliseconds(0);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        // **********获取freemaker模板**********
        dateTmp=cfg.getTemplate(tampPath);

        // **********将数据写入freemaker模板**********
        fw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file)), "UTF-8"));
        dateTmp.process(exlParam, fw);

        // **********从freemaker模板读出数据写到Excel表格并生成出来**********
//        String fileDir="excelTest001";

        // 文件保存目录
//        String filePath=new File("").getAbsolutePath();
        // 生成保存文件路径
//        String createPath=filePath + "/" + fileDir + "/";
        // 构建源文件
        File files=new File(file);

        MultipartFile cMultiFile=new MockMultipartFile(files.getName(), files.getName(), "multipart/form-data", new FileInputStream(files));
        // 文件夹不存在就创建
//        createFolder(createPath);
        // 删除原来的文件
//        deleteFile(createPath + excelName);
        // 构建目标文件
//        File fileCopy=new File(createPath + excelName);
//        // 目标文件不存在就创建
//        if (!(fileCopy.exists())) {
//            fileCopy.createNewFile();
//        }
        // 源文件创建输入流
        //in = new FileInputStream(files);
        //
        //MultipartFile multipartFile = new MockMultipartFile(excelName, excelName, ContentType.APPLICATION_OCTET_STREAM.toString(), in);
        //Map<String, Object> fileMap = FileHandle.upload(multipartFile, uplodPathConfig.getPath());
        //FileData fileData = new FileData();
        //fileData.setCreateTime(new Date());
        //fileData.setIsCheck("false");
        //fileData.setType(fileMap.get("type").toString());
        //fileData.setUrl(fileMap.get("url").toString());
        //QXZTResponse data = remoteFileDataService.createdWithReturn(fileData);
        //FileData fileDatas = JSON.parseObject(JSON.toJSONString(data.getData()), FileData.class);
        //fileMap.put("id", fileDatas.getId());
        //fileMap.remove("url");
        return ossUploadService.upload(cMultiFile);
    }

}
