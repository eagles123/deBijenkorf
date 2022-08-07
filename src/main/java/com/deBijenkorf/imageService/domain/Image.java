package com.deBijenkorf.imageService.domain;


import org.springframework.stereotype.Component;

@Component
public class Image {
    private int height;
    private int width;
    private int quality;
    private String scaleType;
    private String imageType;
    private String fillColor;


    public Image(int height, int width, int quality, String scaleType, String imageType, String fillColor) {
        this.height = height;
        this.width = width;
        this.quality = quality;
        this.scaleType = scaleType;
        this.imageType = imageType;
        this.fillColor = fillColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getScaleType() {
        return scaleType;
    }

    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }
}
