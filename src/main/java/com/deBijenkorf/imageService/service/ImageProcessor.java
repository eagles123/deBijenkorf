package com.deBijenkorf.imageService.service;

import com.deBijenkorf.imageService.domain.Image;

public interface ImageProcess {
    boolean validation(Image image);
    boolean checkOptmized(String url);
    String fetchOptimized(String url);
    boolean checkOrignial(String url);
    String reSize(String url, Image image);
    boolean storeOptimized(String typeName, String fileName);
    boolean storeOriginal(String fileName);
    String fetchOrignal(String url);
    boolean flushOptimal(String url);
    boolean flushOriginal(String url);
}
