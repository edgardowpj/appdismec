package com.example.practi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("TestUpload.php")
    Call<ResponsePOJO> uploadDocument(
            @Field("PDF") String encodedPDF
    );

}
