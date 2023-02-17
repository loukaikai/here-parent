package com.here.modules.oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.here.common.exception.BizException;
import com.here.modules.oss.service.OssService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Lzk
 */
@Service
public class OssServiceImpl implements OssService {

    @Value("${oss.access-key-id}")
    private String accessId;

    @Value("${oss.access-key-secret}")
    private String accessKey;

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucket-name}")
    private String bucketName;

    @Value("${oss.folder-name}")
    private String folderName;

    @Override
    public boolean putObject(String filePath, String renameFile) {
        CredentialsProvider provider = new DefaultCredentialProvider(accessId, accessKey);
        OSSClient client = new OSSClient(endpoint, provider, null);
        try (InputStream inputStream = new FileInputStream(filePath)) {
            String objectName;
            if (Objects.nonNull(renameFile) && !"".equals(renameFile)) {
                objectName = folderName + "/" + renameFile;
            } else {
                int division = Math.max(filePath.lastIndexOf("/"), filePath.lastIndexOf("\\"));
                objectName = division == -1 ? folderName + "/" + filePath :
                        folderName + "/" + filePath.substring(division + 1);
            }
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);
            putObjectRequest.setProcess("true");
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
            if (200 == putObjectResult.getResponse().getStatusCode()) {
                return true;
            }
        } catch (FileNotFoundException e) {
            throw new BizException("指定文件不存在：" + filePath);
        } catch (OSSException oe) {
            String exceptionMsg = "Caught an OSSException, which means your request made it to OSS, " +
                    "but was rejected with an error response for some reason.\n" +
                    "Error Message:" + oe.getErrorMessage() + "\n" +
                    "Error Code:" + oe.getErrorCode() + "\n" +
                    "Request ID:" + oe.getRequestId() + "\n" +
                    "Host ID:" + oe.getHostId() + "\n";
            throw new BizException(exceptionMsg);
        } catch (ClientException ce) {
            String exceptionMsg = "Caught an ClientException, which means the client encountered " +
                    "a serious internal problem while trying to communicate with OSS, " +
                    "such as not being able to access the network.\n" +
                    "Error Message:" + ce.getMessage() + "\n";
            throw new BizException(exceptionMsg);
        } catch (IOException e) {
            throw new BizException(e.getMessage());
        } finally {
            client.shutdown();
        }
        return false;
    }
}
