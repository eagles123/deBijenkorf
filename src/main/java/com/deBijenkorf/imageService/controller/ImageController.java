package com.deBijenkorf.imageService.controller;

import com.deBijenkorf.imageService.controller.utils.Result;
import com.deBijenkorf.imageService.domain.Image;
import com.deBijenkorf.imageService.service.ImageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private Environment env;
    @Autowired
    private ImageProcessor imageProcessor;
    @Autowired
    private Image image;

    @GetMapping("/{typeName}/{dummyName}")
    public Result show(@PathVariable String typeName, @PathVariable String dummyName, @RequestParam String reference) throws Exception {
        if(typeName.equals("thumbnail")){
            image.setHeight(Integer.parseInt(env.getProperty("thumbnail.height")));
            image.setWidth(Integer.parseInt(env.getProperty("thumbnail.width")));
            image.setQuality(Integer.parseInt(env.getProperty("thumbnail.quality")));
            image.setFillColor(env.getProperty("thumbnail.fillColor"));
            image.setImageType(env.getProperty("thumbnail.imageType[0]"));
            image.setScaleType(env.getProperty("thumbnail.scaleType[1]"));
        }
        else if(typeName.equals("detailLarge")){
            image.setHeight(Integer.parseInt(env.getProperty("detailLarge.height")));
            image.setWidth(Integer.parseInt(env.getProperty("detailLarge.width")));
            image.setQuality(Integer.parseInt(env.getProperty("detailLarge.quality")));
            image.setFillColor(env.getProperty("detailLarge.fillColor"));
            image.setImageType(env.getProperty("detailLarge.imageType[0]"));
            image.setScaleType(env.getProperty("detailLarge.scaleType[1]"));
        }
        else throw new IllegalArgumentException();

        return new Result(true, imageProcessor.show(typeName,reference, image), HttpStatus.OK.toString());
    }

    @PostMapping("/flush/original")
    public Result flushOriginal(@RequestParam String reference){
        imageProcessor.flushOriginal(reference);
        return new Result(true,imageProcessor.flushOriginal(reference), HttpStatus.OK.toString());
    }

    @PostMapping("/flush/thumbnail/")
    public Result flushThumbnail(@RequestParam String reference){
        imageProcessor.flushOptimal(reference);
        return new Result(true,imageProcessor.flushOptimal(reference), HttpStatus.OK.toString());
    }


}
