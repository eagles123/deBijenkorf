package com.deBijenkorf.imageService.service;

import com.deBijenkorf.imageService.domain.Image;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ImageProcessor {


    String show(String typeName, String fileNamem, Image image) throws IOException;
    boolean flushOptimal(String fileName);
    boolean flushOriginal(String fileName);
}
