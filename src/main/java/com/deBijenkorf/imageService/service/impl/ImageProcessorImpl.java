package com.deBijenkorf.imageService.service.impl;

import com.deBijenkorf.imageService.domain.Image;
import com.deBijenkorf.imageService.service.ImageProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class ImageProcessorImpl implements ImageProcessor {

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
    public String show(String typeName,String fileName, Image image) throws IOException {
        String result = "";
        //construct directory based on file name
        String optimaizedEndpoint = typeName+"/"+makeDir(fileName);
        String originalEndpoint = "original"+"/"+makeDir(fileName);
        //if optimzed image already in S3, return the url
        if(checkOptmized(optimaizedEndpoint))
            result = fetchOptimized(optimaizedEndpoint);
        //if optimized not in S3, check if original is in S3, if it is rezied it and return url
        else if(checkOrignial(originalEndpoint)){
            String newFile = reSize(fileName, image);
            result = storeImage(typeName,newFile);

        }
        //if both optimized and origianl is not in S3, download it and rezied it and store it to S3
        else if(!checkOrignial(originalEndpoint)){
            String downloadFile = downlaodOriginal(fileName);
            String newFile = reSize(downloadFile, image);
            result = storeImage(typeName,newFile);
        }
        return result;
    }


    @Override
    public boolean flushOptimal(String fileName) {
        boolean success = true;
        System.out.println("Flusing optimal images" +fileName);
        return success;
    }
    @Override
    public boolean flushOriginal(String fileName) {
        boolean success = true;
        System.out.println("Flusing optimal images"+fileName);
        return success;
    }


    private boolean checkOptmized(String url) throws FileNotFoundException {
        // can use s3Client to connect to S3 bucket to check if the optimized image exist.
        System.out.println("Searching AWS S3 buket for optimized image");
        boolean exist = true;
        if(!exist) throw new FileNotFoundException();
        return true;
    }


    private String fetchOptimized(String url) {
        // use s3Client to fetch the optimized image url
        System.out.println("Feching the optimzed image");
        String result = "";
        result = url; //mocking url
        return result;//*****
    }


    private boolean checkOrignial(String url) {
        //use s3Client to check if the original image exist in the S3 buket
        System.out.println("Searching AWS S3 buket for original image");
        return true;
    }


    private String reSize(String url, Image image){
        // resize the image using predefined image types
        System.out.println("Re-sizeing using height "+ image.getHeight() + " width " + image.getWidth() + " quality " + image.getQuality());
        String newUrl = url+ image.getScaleType()+image.getFillColor();
        boolean isSuccessful = true;

        return newUrl;
    }


    private String storeImage(String typeName, String fileName) throws IOException {
        boolean success = false;
        String dir = typeName;
        dir = dir+"/"+ makeDir(fileName);
        System.out.println("Saving image to "+dir);
        success = true; //mock the operation is successful;
        if(!success) throw new IOException();
        return dir;
    }


    private String downlaodOriginal(String fileName) throws IOException {
        System.out.println("Downloading original pic to S3 bukcet");
        boolean urlDown  = false;
        if(urlDown) throw new FileNotFoundException();
        return storeImage("original",fileName);

    }



    private String makeDir(String fileName) {
        System.out.println("Making new dir.....");
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
