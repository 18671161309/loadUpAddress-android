package com.china317.developlibrary.okHttp.builder;

import com.china317.developlibrary.okHttp.request.PostFileRequest;
import com.china317.developlibrary.okHttp.request.RequestCall;

import java.io.File;

import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/9/20.
 */

public class PostFileBuilder extends OkHttpRequestBuilder<PostFileBuilder>{

    private File file;
    private MediaType mediaType;


    public OkHttpRequestBuilder file(File file)
    {
        this.file = file;
        return this;
    }

    public OkHttpRequestBuilder mediaType(MediaType mediaType)
    {
        this.mediaType = mediaType;
        return this;
    }

    @Override
    public RequestCall build()
    {
        return new PostFileRequest(url, tag, params, headers, file, mediaType,id).build();
    }
}
