package com.deBijenkorf.imageService.controller.utils;

public class Result {
    private boolean flag;
    private String url;
    private String statusCode;
    private Object data;

    public Result(boolean flag, String statusCode) {
        this.flag = flag;
        this.statusCode = statusCode;
    }


    public Result(boolean flag, Object data, String statusCode) {
        this.flag = flag;
        this.data = data;
        this.statusCode = statusCode;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
