package Tiny.capsule.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface DownloadRequest {
    @Streaming
    @GET
    Call<ResponseBody> downloadPicture(@Url String fileUrl);
}
