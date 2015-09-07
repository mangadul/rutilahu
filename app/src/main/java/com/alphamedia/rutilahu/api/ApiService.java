package com.alphamedia.rutilahu.api;

import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

public interface ApiService {

    public static final String BASE_URL = "http://demo.alphamedia.web.id/fieldreport";

    @Multipart
    @POST("/files")
    //ApiResult
    String uploadFile(@Part("file") TypedFile resource, @Query("path") String path);
}