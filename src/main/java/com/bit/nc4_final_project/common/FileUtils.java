package com.bit.nc4_final_project.common;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.bit.nc4_final_project.configuration.NaverConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component


public class FileUtils {
    private final AmazonS3 s3;
    @Value("${ncp.accessKey}")
    private String accessKey;

    @Value("${ncp.bucket}")
    private String bucketName;

    @Value("${ncp.endPoint}")
    private String storageUrl;


    public FileUtils(NaverConfiguration naverConfiguration) {
        s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        naverConfiguration.getEndPoint(), naverConfiguration.getRegionName()
                ))
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(
                                naverConfiguration.getAccessKey(), naverConfiguration.getSecretKey()
                        )
                ))
                .build();
    }

    public AmazonS3 getS3() {
        return s3;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getStorageUrl() {
        return storageUrl;
    }

    public String saveFile(MultipartFile picture) {
        // 고유한 파일 이름 생성 (예: UUID 사용)
        String fileName = UUID.randomUUID().toString() + "_" + picture.getOriginalFilename();

        // 파일 메타데이터 설정 (옵션)
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(picture.getSize());
        metadata.setContentType(picture.getContentType());

        try {
            // MultipartFile을 InputStream으로 변환
            InputStream inputStream = picture.getInputStream();

            // 파일 업로드
            s3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)); // 파일을 public으로 설정

            // 업로드된 파일의 URL 생성 및 반환
            return s3.getUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 에러 발생: " + e.getMessage());
        }
    }
}

