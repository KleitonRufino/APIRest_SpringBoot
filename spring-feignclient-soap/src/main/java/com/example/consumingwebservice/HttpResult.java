package com.example.consumingwebservice;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import lombok.Data;

@Data
public class HttpResult {

    private int resultCode;
    private String response;
    private HttpType httpType;

    public HttpResult(int resultCode, HttpType httpType) {
        this.resultCode = resultCode;
        this.httpType = httpType;
    }

    public HttpResult(HttpResponse httpResponse, HttpType httpType) throws IOException {
        this(httpResponse);
        this.httpType = httpType;
    }

    public HttpResult(HttpResponse httpResponse) throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            response = EntityUtils.toString(entity);
        }
        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine != null) {
            resultCode = statusLine.getStatusCode();
        }
    }
}
