package mobi.idappthat.snapchat_clone_android.Utils;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * Created by kolten on 2/23/18.
 */

public class HttpUtil {

    private OkHttpClient client;
    private Request.Builder builder;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public void get(String url, HttpCallback cb) {
        call("GET", url, null, cb);
    }

    public void post(String url, String body, HttpCallback cb) {
        call("POST", url, body, cb);
    }

    private void call(String method, String url, String body, final HttpCallback cb) {
        RequestBody b = RequestBody.create(JSON, body);
        Request request = new Request.Builder()
                .url(url)
                .method(method, method.equals("GET") ? null : b)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                cb.onFailure(null, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    cb.onFailure(response, null);
                    return;
                }
                cb.onSuccess(response);
            }
        });
    }
}


