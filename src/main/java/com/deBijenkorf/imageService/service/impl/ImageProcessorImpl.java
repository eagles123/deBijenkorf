package com.deBijenkorf.imageService.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.deBijenkorf.imageService.domain.Image;
import com.deBijenkorf.imageService.service.ImageProcess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageProcessImpl implements ImageProcess {

    @Value("${cloud.aws.accesskey}")
    private String accessKey;

    @Value("${cloud.aws.secretKey}")
    private String secretKey;

//    AWSCredentials credentials  = new BasicAWSCredentials(accessKey,secretKey);
//
//    AmazonS3 s3Client = AmazonS3ClientBuilder
//            .standard()
//            .withCredentials(new AWSStaticCredentialsProvider(credentials))
//            .withRegion(Regions.EU_WEST_1)
//            .build();


    @Override
    public boolean validation(Image image) {
        //validate the image type, detail omited
        System.out.println("Validating the data....");
        return true;
    }

    @Override
    public boolean checkOptmized(String url) {
        // can use s3Client to connect to S3 bucket to check if the optimized image exist.
        System.out.println("Searching AWS S3 buket for optimized image");
        return true;
    }

    @Override
    public String fetchOptimized(String url) {
        // use s3Client to fetch the optimized image url
        String result = "";
        result = "https://bucketww2.s3.ap-southeast-2.amazonaws.com/%E7%85%A7%E7%89%87.jpg"; //mocking url
        return url;//*****
    }

    @Override
    public boolean checkOrignial(String url) {
        //use s3Client to check if the original image exist in the S3 buket
        System.out.println("Searching AWS S3 buket for original image");
        return true;
    }

    @Override
    public String reSize(String url, Image image) {
        // resize the image using predefined image types
        System.out.println("Re-sizeing using height "+ image.getHeight() + " width " + image.getWidth() + " quality " + image.getQuality());
        String newUrl = url+ image.getScaleType()+image.getFillColor();
        return newUrl;
    }

    @Override
    public boolean storeOptimized(String typeName, String fileName) {
        boolean success = false;
        String dir = typeName;
        dir = dir+makeDir(fileName);
        System.out.println("Saving image to "+dir);
        success = true; //mock the operation is successful;
        return success;
    }

    @Override
    public boolean storeOriginal(String fileName) {
        boolean success = false;
        String dir = "original";// set path for oringinal images
        dir = dir+makeDir(fileName);
        System.out.println("Saving orignal images to "+ dir);
        success = true; //mock the operation is successful;
        return success;
    }

    @Override
    public String fetchOrignal(String fileName) {
        String result = "https://bucket.s3.ap-southeast-2.amazonaws.com/%E7%85%A7%E7%89%87.jpg"; //mocking url
        return result;//*****

    }

    @Override
    public boolean flushOptimal(String url) {
        boolean success = true;
        System.out.println("Flusing optimal images");
        return success;
    }

    @Override
    public boolean flushOriginal(String url) {
        boolean success = true;
        System.out.println("Flusing optimal images");
        return success;
    }

    private String makeDir(String fileName) {
        //example: abcdef.jpg or /somedir/anotherdir/abcdef.jpg
        String dir = "";
        int fileLength = fileName.length();
        //unique-original-image-filename
        fileName.replace("/","_");
        //if file name length is between 4-8 characters, construct path using first 4 chars
        if(fileLength >=4 && fileLength <=8){
            dir = fileName.substring(0,4) + "/" + fileName;
        }
        //if file name length is more than 8 characters, construct path use first 4 chars and second 4 chars
        else if(fileLength >8){
            dir = fileName.substring(0,4) + "/" + fileName.substring(4,8) +"/"+fileName;
        }
        //else just use the fileName (not specified in the spec)
        else dir = fileName;
        return dir;
    }

}
