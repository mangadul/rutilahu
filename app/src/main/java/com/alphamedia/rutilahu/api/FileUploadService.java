package com.alphamedia.rutilahu.api;

import retrofit.Callback;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

public interface FileUploadService {

    public static final String BASE_URL = "http://demo.alphamedia.web.id/fieldreport";

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json; charset=utf-8"
    })
    //@Multipart
    @POST("/fu.php")
    void upload(@Part("myfile") TypedFile file,
                @Part("description") String description,
                Callback<String> cb);

}

