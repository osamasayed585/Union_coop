package com.visionstech.demoapp.core.network.interceptors;

import com.google.gson.Gson;
import com.visionstech.demoapp.core.network.responses.BaseResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.hilt.android.scopes.ViewModelScoped;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import timber.log.Timber;

@ViewModelScoped
public class NetworkInterceptor implements Interceptor {


    @Inject
    public NetworkInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) {
        Gson gson = new Gson();
        Request request = chain.request();

        if (!isInternetAvailable()) {
            return new Response.Builder()
                    .code(200) // Whatever code
                    .body(ResponseBody.create(gson.toJson(BaseResponse.builder().status("OK").build()), null)) // Whatever body
                    .protocol(Protocol.HTTP_2)
                    .message("")
                    .request(request)
                    .build();
        } else {

            try {

                Response response = chain.proceed(request);
                ResponseBody body = response.body();
                String bodyString = body.string();
                MediaType contentType = body.contentType();

                if (isValidJSON(bodyString) && response.code() != 200) {
                    BaseResponse baseResponse = gson.fromJson(bodyString, BaseResponse.class);
                    String editedResponse = gson.toJson(baseResponse);
                    Timber.d("intercept: %s", editedResponse);
                    return response.newBuilder().body(ResponseBody.create(editedResponse, contentType)).code(200).build();
                } else {
                    return response.newBuilder().body(ResponseBody.create(bodyString, contentType)).build();
                }
            } catch (IOException e) {
                Timber.e(e);
            }
        }
        return null;
    }


    public boolean isValidJSON(String json) {
        try {
            new JSONObject(json);
        } catch (JSONException ex) {
            try {
                new JSONArray(json);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public boolean isInternetAvailable() {
        try {
            int timeoutMs = 4000;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }


}