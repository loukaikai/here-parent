package com.here.modules.oss.service;

/**
 * 阿里云OSS服务
 * @author Lzk
 */
public interface OssService {

    /**
     * 获取OSS签名
     * @param filePath 上传文件路径
     * @param renameFile 文件重命名名称
     * @return 上传结果
     */
    boolean putObject(String filePath, String renameFile);

}
