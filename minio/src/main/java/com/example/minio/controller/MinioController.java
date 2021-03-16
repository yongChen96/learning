package com.example.minio.controller;

import com.example.minio.utils.MinioUtil;
import com.sun.deploy.net.HttpResponse;
import io.minio.ObjectWriteResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: MinioController
 * @Description: Minio上传下载
 * @Author: yongchen
 * @Date: 2020/11/12 15:35
 **/
@RestController
@RequestMapping("/minio")
@Api(value = "Minio上传下载")
public class MinioController {

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.bucket-name}")
    private String bucketName;

    @Resource
    private MinioUtil minioUtil;

    /**
     * @Author: yongchen
     * @Description: 测试putFile方法
     * @Date: 15:42 2020/11/12
     * @Param: [file]
     * @return: io.minio.ObjectWriteResponse
     **/
    @PostMapping("/putFile")
    @ApiOperation(value = "测试putFile方法")
    public Map<String, String> putFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        InputStream stream = file.getInputStream();
        String contentType = file.getContentType();
        if (!minioUtil.bucketExists(bucketName)) {
            minioUtil.makeBucket(bucketName);
        }
        ObjectWriteResponse object = minioUtil.putObject(bucketName, filename, stream, (long) stream.available(), contentType);
        Map<String, String> map = new HashMap<>();
        map.put("bucketName", bucketName);
        map.put("objectName", filename);
        return map;
    }

    /**
     * @Author: yongchen
     * @Description: 获取对象路径
     * @Date: 15:57 2020/11/16
     * @Param: [bucketName, objectName]
     * @return: java.lang.String
     **/
    @GetMapping("/getUrl")
    @ApiOperation(value = "获取对象路径")
    public String getFileUrl(String bucketName, String objectName){
        return minioUtil.getObjectUrl(bucketName, objectName);
    }

    /**
     * @Author: yongchen
     * @Description: 根据Minio桶名称和文件名称删除文件
     * @Date: 11:26 2020/11/16
     * @Param: [bucketName, objectName]
     * @return: java.lang.String
     **/
    @GetMapping("/remove")
    @ApiOperation(value = "根据Minio桶名称和文件名称删除文件")
    public String removeFile(String bucketName, String objectName){
        minioUtil.removeObject(bucketName, objectName);
        return "删除成功！";
    }

    /**
     * @Author: yongchen
     * @Description: 文件下载
     * @Date: 11:59 2020/11/16
     * @Param: [bucketName, objectName, response]
     * @return: void
     **/
    @GetMapping(value = "/download", produces = "application/octet-stream")
    @ApiOperation(value = "文件下载")
    public void download(String bucketName, String objectName, HttpServletResponse response) {
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            InputStream object = minioUtil.getObject(bucketName, objectName);
            int length = 0;
            byte buf[] = new byte[1024];
            while ((length = object.read(buf)) > 0) {
                os.write(buf, 0, length);
            }
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
