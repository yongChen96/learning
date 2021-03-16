package com.example.minio.utils;

import com.example.minio.minio.MinioPropreties;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MinioUtil
 * @Description: minio使用工具类
 * @Author: yongchen
 * @Date: 2020/11/11 17:26
 **/
@Component
@Slf4j
public class MinioUtil {

    private MinioClient minioClient;

    @Resource
    private MinioPropreties minioPropreties;

    /**
     * @Author: yongchen
     * @Description: 初始化minion客户端连接
     * @Date: 17:42 2020/11/11
     * @Param: []
     * @return: void
     **/
    @PostConstruct
    void init() {
        log.info(">>>>>>>>>>>>> 初始化Minio客户端连接 >>>>>>>>>>>>>");
        minioClient = MinioClient.builder()
                .endpoint(minioPropreties.getEndpoint())
                .credentials(minioPropreties.getAccessKey(), minioPropreties.getSecretKey())
                .build();
    }

    /**
     * @Author: yongchen
     * @Description: 判断bucket是否存在
     * @Date: 11:29 2020/11/12
     * @Param: [bucketName]
     * @return: java.lang.Boolean
     **/
    public boolean bucketExists(String bucketName) {
        boolean bucketExists = false;
        try {
            bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 判断桶是否存在失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        if (!bucketExists) {
            log.info(">>>>>>>>>>>>> 判断名为{}的桶不存在 >>>>>>>>>>>>>", bucketName);
        }
        return bucketExists;
    }

    /**
     * @Author: yongchen
     * @Description: 创建桶
     * @Date: 11:55 2020/11/12
     * @Param: [bucketName]
     * @return: void
     **/
    public void makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 创建桶失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author: yongchen
     * @Description: 获取桶列表信息
     * @Date: 11:51 2020/11/12
     * @Param: []
     * @return: java.util.List<io.minio.messages.Bucket>
     **/
    public List<Bucket> listBuckets() {
        try {
            List<Bucket> bucketList = minioClient.listBuckets();
            return bucketList;
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 获取桶列表异常！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 根据桶名称删除桶信息
     * @Date: 11:53 2020/11/12
     * @Param: [bucketName]
     * @return: void
     **/
    public void removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 删除桶异常！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author: yongchen
     * @Description: 根据桶名称和对象信息获取存储信息
     * @Date: 14:27 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: java.io.InputStream
     **/
    public InputStream getObject(String bucketName, String objectName) {
        try {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 获取文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 获取预览url
     * @Date: 14:40 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: java.lang.String
     **/
    public String getObjectUrl(String bucketName, String objectName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(bucketName)
                    .object(objectName)
                    .expiry(1, TimeUnit.DAYS)
                    .build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 获取文件url失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 通过InputStream上传对象
     * @Date: 14:31 2020/11/12
     * @Param: [bucketName, objectName, iso, size]
     * @return: io.minio.ObjectWriteResponse
     **/
    public ObjectWriteResponse putObject(String bucketName, String objectName, InputStream iso, Long size, String contentType) {
        try {
            return minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(iso, size, -1)
                    .contentType(contentType)
                    .build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 上传文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 上传文件
     * @Date: 14:44 2020/11/12
     * @Param: [bucketName, objectName, fileName]
     * @return: io.minio.ObjectWriteResponse
     **/
    public ObjectWriteResponse uploadObject(String bucketName, String objectName, String fileName) {
        try {
            return minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .filename(fileName)
                    .build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 上传文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 文件下载
     * @Date: 14:45 2020/11/12
     * @Param: [bucketName, objectName, fileName]
     * @return: void
     **/
    public void downloadObject(String bucketName, String objectName, String fileName) {
        try {
            minioClient.downloadObject(DownloadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .filename(fileName)
                    .build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 下载文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author: yongchen
     * @Description: 删除文件或文件夹
     * @Date: 14:49 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: void
     **/
    public void removeObject(String bucketName, String objectName) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 删除文件失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @Author: yongchen
     * @Description: 判断对象是否存在
     * @Date: 14:51 2020/11/12
     * @Param: [bucketName, objectName]
     * @return: io.minio.StatObjectResponse
     **/
    public StatObjectResponse statObject(String bucketName, String objectName) {
        try {
            return minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build());
        } catch (Exception e) {
            log.error(">>>>>>>>>>>>> 判断对象是否存在失败！{} >>>>>>>>>>>>>", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author: yongchen
     * @Description: 判断文件夹是否存在
     * @Date: 14:06 2021/3/16
     * @Param: [bucketName, objectName]
     * @return: java.lang.Boolean
     **/
    public Boolean doesFolderExist(String bucketName, String objectName) {
        Boolean flag = false;
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).prefix(objectName).recursive(false).build());
            for (Result<Item> result : results) {
                Item item = result.get();
                if (item.isDir() && objectName.equals(item.objectName())) {
                    flag = true;
                }
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @Author: yongchen
     * @Description: 创建文件夹
     * @Date: 17:58 2021/3/16
     * @Param: [bucketName, folderName]
     * @return: io.minio.ObjectWriteResponse
     **/
    public ObjectWriteResponse createFolder(String bucketName, String folderName) {
        try {
            return minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(folderName).stream(
                            new ByteArrayInputStream(new byte[]{}), 0, -1)
                            .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
