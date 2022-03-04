/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.exc.applibrary.main;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;


import com.exc.applibrary.main.model.BaseBean;
import com.exc.applibrary.main.utils.CommonUtils;
import com.exc.applibrary.main.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import zuo.biao.library.base.BaseApplication;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.model.Parameter;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.PreferencesUtil;
import zuo.biao.library.util.SSLUtil;
import zuo.biao.library.util.StringUtil;

/**
 * HTTP请求管理类
 *
 * @author Lemon
 * @use HttpManager.getInstance().get(...)或HttpManager.getInstance().post(...)  > 在回调方法onHttpRequestSuccess和onHttpRequestError处理HTTP请求结果
 * @must 解决getToken，getResponseCode，getResponseData中的TODO
 */
public class HttpManager {
    private static final String TAG = "HttpManager";


    private Context context;
    private SSLSocketFactory socketFactory;// 单例

    private HttpManager(Context context) {
        this.context = context;

        try {
            //TODO 初始化自签名，demo.cer（这里demo.cer是空文件）为服务器生成的自签名证书，存放于assets目录下，如果不需要自签名可删除
            socketFactory = SSLUtil.getSSLSocketFactory(context.getAssets().open("demo.cer"));
        } catch (Exception e) {
            Log.e(TAG, "HttpManager  try {" +
                    "  socketFactory = SSLUtil.getSSLSocketFactory(context.getAssets().open(\"demo.cer\"));\n" +
                    "\t\t} catch (Exception e) {\n" + e.getMessage());
        }
    }

    private static HttpManager instance;// 单例

    public static HttpManager getInstance() {
        if (instance == null) {
            synchronized (HttpManager.class) {
                if (instance == null) {
                    instance = new HttpManager(BaseApplication.getInstance());
                }
            }
        }
        return instance;
    }


    /**
     * GET请求
     *
     * @param request     请求
     * @param url         网络地址
     * @param requestCode 请求码，类似onActivityResult中请求码，当同一activity中以实现接口方式发起多个网络请求时，请求结束后都会回调
     *                    {@link OnHttpResponseListener#onHttpResponse(int, String, Exception)}<br>
     *                    在发起请求的类中可以用requestCode来区分各个请求
     * @param listener
     */
    public void get(final Map<String, Object> request, final String url,
                    final int requestCode, final OnHttpResponseListener listener) {

        new AsyncTask<Void, Void, Exception>() {

            String result;

            @Override
            protected Exception doInBackground(Void... params) {
                OkHttpClient client = getHttpClient(url);
                if (client == null) {
                    return new Exception(TAG + ".get  AsyncTask.doInBackground  client == null >> return;");
                }

                StringBuffer sb = new StringBuffer();
                sb.append(url);

                Set<Map.Entry<String, Object>> set = request == null ? null : request.entrySet();
                if (set != null) {
                    boolean isFirst = true;
                    for (Map.Entry<String, Object> entry : set) {
                        sb.append(isFirst ? "?" : "&");
                        sb.append(StringUtil.trim(entry.getKey()));
                        sb.append("=");
                        sb.append(StringUtil.trim(entry.getValue()));

                        isFirst = false;
                    }
                }

                try {


                    Request.Builder builder = new Request.Builder();
                    builder.addHeader("token", PreferencesUtil.getString(context, "user_token", ""));
                    Log.i("tokent=",PreferencesUtil.getString(context, "user_token", ""));

                    result = getResponseJson(
                            client,
                            builder.url(sb.toString()).build()
                    );
                    //仅供测试 result = "{\"code\":100,\"data\":{\"id\":1,\"name\":\"TestName\",\"phone\":\"1234567890\"}}";
                    Log.i(TAG, "\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n get  url = " + url + "\n request = \n" + sb.toString());
                    Log.i(TAG, "\n request get  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");

                } catch (Exception e) {
                    Log.e(TAG, "get  AsyncTask.doInBackground  try {  result = getResponseJson(..." +
                            "} catch (Exception e) {\n" + e.getMessage());
                    return e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(Exception exception) {
                super.onPostExecute(exception);
//                try {
//                    JsonUtils.parseObject(result, BaseBean.class);
                listener.onHttpResponse(requestCode, result, exception);
//                }catch (Exception e){
//                    listener.onHttpResponse(requestCode, null, exception);
//
//                }
            }

        }.execute();

    }


    public void getBackModule(Activity activity, final Map<String, Object> request, final String url,
                              final HttpListener httpListener) {

        new AsyncTask<Void, Void, Exception>() {

            String result;

            @Override
            protected Exception doInBackground(Void... params) {
                OkHttpClient client = getHttpClient(url);
                if (client == null) {
                    return new Exception(TAG + ".get  AsyncTask.doInBackground  client == null >> return;");
                }

                StringBuffer sb = new StringBuffer();
                sb.append(url);

                Set<Map.Entry<String, Object>> set = request == null ? null : request.entrySet();
                if (set != null) {
                    boolean isFirst = true;
                    for (Map.Entry<String, Object> entry : set) {
                        sb.append(isFirst ? "?" : "&");
                        sb.append(StringUtil.trim(entry.getKey()));
                        sb.append("=");
                        sb.append(StringUtil.trim(entry.getValue()));

                        isFirst = false;
                    }
                }

                try {


                    Request.Builder builder = new Request.Builder();
                    builder.addHeader("token", PreferencesUtil.getString(context, "user_token", ""));


                    result = getResponseJson(
                            client,
                            builder.url(sb.toString()).build()
                    );
                    //仅供测试 result = "{\"code\":100,\"data\":{\"id\":1,\"name\":\"TestName\",\"phone\":\"1234567890\"}}";
                    Log.i(TAG, "\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n get  url = " + url + "\n request = \n" + sb.toString());
                    Log.i(TAG, "\n request get  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");

                } catch (Exception e) {
                    Log.e(TAG, "get  AsyncTask.doInBackground  try {  result = getResponseJson(..." +
                            "} catch (Exception e) {\n" + e.getMessage());
                    return e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(Exception exception) {
                super.onPostExecute(exception);
                if (exception == null && result != null) {
                    Object module = null;
                    try {
                        module = com.alibaba.fastjson.JSONObject.parseObject(result, httpListener.getType());
                    } catch (com.alibaba.fastjson.JSONException e) {
                        e.printStackTrace();
                    }
                    if (null == module) {
                        ToastUtils.showErrorToast(activity, "数据异常");
                        return;
                    }
                    BaseBean baseBean = com.alibaba.fastjson.JSONObject.parseObject(result, BaseBean.class);
                    if (baseBean.getCode() == 401) {
                        CommonUtils.exitLogin(401, activity);
                        return;
                    }
                    if (baseBean.getCode() != 200) {
                        ToastUtils.showErrorToast(activity, baseBean.getMessage());

                        return;
                    }
                    httpListener.onSuccess(module);
                } else {
                    httpListener.onError();
                }

            }

        }.execute();

    }


    /**
     * GET请求，最快在 19.0 删除，请尽快迁移到 {@link #get(Map, String, int, OnHttpResponseListener)}
     *
     * @param paramList   请求参数列表，（可以一个键对应多个值）
     * @param url         网络地址
     * @param requestCode 请求码，类似onActivityResult中请求码，当同一activity中以实现接口方式发起多个网络请求时，请求结束后都会回调
     *                    {@link OnHttpResponseListener#onHttpResponse(int, String, Exception)}<br>
     *                    在发起请求的类中可以用requestCode来区分各个请求
     * @param listener
     */
    @Deprecated
    public void get(final List<Parameter> paramList, final String url,
                    final int requestCode, final OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        if (paramList != null) {
            for (Parameter p : paramList) {
                request.put(p.key, p.value);
            }
        }
        get(request, url, requestCode, listener);
    }


    public static final MediaType TYPE_JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * POST请求，以FORM表单形式提交
     *
     * @param request     请求
     * @param url         网络地址
     * @param requestCode 请求码，类似onActivityResult中请求码，当同一activity中以实现接口方式发起多个网络请求时，请求结束后都会回调
     *                    {@link OnHttpResponseListener#onHttpResponse(int, String, Exception)}<br/>
     *                    在发起请求的类中可以用requestCode来区分各个请求
     * @param listener
     */
    public void post(final Map<String, Object> request, final String url
            , final int requestCode, final OnHttpResponseListener listener) {
        post(request, url, true, requestCode, listener);
    }


    public void postBackModule(Activity activity, final Map<String, Object> request, final String url
            , final HttpListener httpListener) {
        postBackModule(activity, request, url, true, httpListener);
    }

    /**
     * POST请求
     *
     * @param request     请求
     * @param url         网络地址
     * @param isJson      JSON : FORM
     * @param requestCode 请求码，类似onActivityResult中请求码，当同一activity中以实现接口方式发起多个网络请求时，请求结束后都会回调
     *                    {@link OnHttpResponseListener#onHttpResponse(int, String, Exception)}<br/>
     *                    在发起请求的类中可以用requestCode来区分各个请求
     * @param listener
     */
    public void post(final Map<String, Object> request, final String url, final boolean isJson
            , final int requestCode, final OnHttpResponseListener listener) {
        new AsyncTask<Void, Void, Exception>() {

            String result;

            @Override
            protected Exception doInBackground(Void... params) {

                try {
                    OkHttpClient client = getHttpClient(url);
                    if (client == null) {
                        return new Exception(TAG + ".post  AsyncTask.doInBackground  client == null >> return;");
                    }


                    RequestBody requestBody;
                    if (isJson) {


                        String body = JsonUtils.toJSONString(request);
                        Log.i(TAG, "\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n post  url = " + url + "\n request = \n" + body);
                        requestBody = RequestBody.create(TYPE_JSON, body);


                    } else {
                        FormBody.Builder builder = new FormBody.Builder();
                        Set<Map.Entry<String, Object>> set = request == null ? null : request.entrySet();
                        if (set != null) {
                            for (Map.Entry<String, Object> entry : set) {
                                builder.add(StringUtil.trim(entry.getKey()), StringUtil.trim(entry.getValue()));
                            }
                        }

                        requestBody = builder.build();
                    }

                    Request.Builder builder = new Request.Builder();
                    builder.addHeader("token", PreferencesUtil.getString(context, "user_token", ""));


                    Request request1 = builder
                            .url(url)
                            .post(requestBody)
                            .build();


                    result = getResponseJson(
                            client,
                            request1
                    );


                    Log.i(TAG, "\n request post  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
                } catch (Exception e) {
                    Log.e(TAG, "post  AsyncTask.doInBackground  try {  result = getResponseJson(..." +
                            "} catch (Exception e) {\n" + e.getMessage());
                    return e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(Exception exception) {
                super.onPostExecute(exception);
//                try {
//                    JsonUtils.parseObject(result, BaseBean.class);
//                    listener.onHttpResponse(requestCode, result, exception);
//                } catch (Exception e) {
//                    listener.onHttpResponse(requestCode, null, exception);
//                }
                listener.onHttpResponse(requestCode, result, exception);
            }

        }.execute();
    }


    public void postObject(final Object request, final String url
            , final int requestCode, final OnHttpResponseListener listener) {
        new AsyncTask<Void, Void, Exception>() {

            String result;

            @Override
            protected Exception doInBackground(Void... params) {

                try {
                    OkHttpClient client = getHttpClient(url);
                    if (client == null) {
                        return new Exception(TAG + ".postObject  AsyncTask.doInBackground  client == null >> return;");
                    }
                    RequestBody requestBody;
                    String body = JsonUtils.toJSONString(request);
                    Log.i(TAG, "\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n postObject  url = " + url + "\n request = \n" + body);
                    requestBody = RequestBody.create(TYPE_JSON, body);
                    Request.Builder builder = new Request.Builder();
                    builder.addHeader("token", PreferencesUtil.getString(context, "user_token", ""));
                    Request request1 = builder
                            .url(url)
                            .post(requestBody)
                            .build();

                    result = getResponseJson(
                            client,
                            request1
                    );

                    Log.i(TAG, "\n request postObject  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
                } catch (Exception e) {
                    Log.e(TAG, "postObject  AsyncTask.doInBackground  try {  result = getResponseJson(..." +
                            "} catch (Exception e) {\n" + e.getMessage());
                    return e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(Exception exception) {
                super.onPostExecute(exception);
                try {
                    JsonUtils.parseObject(result, BaseBean.class);
                    listener.onHttpResponse(requestCode, result, exception);
                } catch (Exception e) {
                    listener.onHttpResponse(requestCode, null, exception);
                }

            }

        }.execute();
    }


    public void postBackModule(Activity activity, final Map<String, Object> request, final String url, final boolean isJson
            , final HttpListener httpListener) {
        new AsyncTask<Void, Void, Exception>() {

            String result;

            @Override
            protected Exception doInBackground(Void... params) {

                try {
                    OkHttpClient client = getHttpClient(url);
                    if (client == null) {
                        return new Exception(TAG + ".post  AsyncTask.doInBackground  client == null >> return;");
                    }


                    RequestBody requestBody;
                    if (isJson) {


                        String body = JsonUtils.toJSONString(request);
                        Log.i(TAG, "\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n post  url = " + url + "\n request = \n" + body);
                        requestBody = RequestBody.create(TYPE_JSON, body);


                    } else {
                        FormBody.Builder builder = new FormBody.Builder();
                        Set<Map.Entry<String, Object>> set = request == null ? null : request.entrySet();
                        if (set != null) {
                            for (Map.Entry<String, Object> entry : set) {
                                builder.add(StringUtil.trim(entry.getKey()), StringUtil.trim(entry.getValue()));
                            }
                        }

                        requestBody = builder.build();
                    }

                    Request.Builder builder = new Request.Builder();
                    builder.addHeader("token", PreferencesUtil.getString(context, "user_token", ""));


                    Request request1 = builder
                            .url(url)
                            .post(requestBody)
                            .build();


                    result = getResponseJson(
                            client,
                            request1
                    );


                    Log.i(TAG, "\n request post  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
                } catch (Exception e) {
                    Log.e(TAG, "post  AsyncTask.doInBackground  try {  result = getResponseJson(..." +
                            "} catch (Exception e) {\n" + e.getMessage());
                    return e;
                }

                return null;
            }

            @Override
            protected void onPostExecute(Exception exception) {
                super.onPostExecute(exception);


                if (exception == null && result != null) {

                    Object module = null;

                    try {
                        module = com.alibaba.fastjson.JSONObject.parseObject(result, httpListener.getType());
                    } catch (com.alibaba.fastjson.JSONException e) {
                        e.printStackTrace();
                    }


                    if (null == module) {
                        ToastUtils.showErrorToast(activity, "数据异常");
                        return;
                    }
                    BaseBean baseBean = com.alibaba.fastjson.JSONObject.parseObject(result, BaseBean.class);
                    if (baseBean.getCode() == 401) {
                        CommonUtils.exitLogin(401, activity);
                        return;
                    }
                    if (baseBean.getCode() != 200) {
                        ToastUtils.showErrorToast(activity, baseBean.getMessage());

                        return;
                    }
                    httpListener.onSuccess(module);
                } else {
                    httpListener.onError();
                }


            }

        }.execute();
    }


    public void put(final Map<String, Object> requestMap, final String url, final int requestCode, final OnHttpResponseListener listener) {

        OkHttpClient.Builder ClientBuilder = new OkHttpClient.Builder();
        OkHttpClient mOkHttpClient = ClientBuilder.build();


        try {
            Request.Builder builder = new Request.Builder();
            //目前头信息只有token
            builder.addHeader("token", PreferencesUtil.getString(context, "user_token", ""));


            String body = JsonUtils.toJSONString(requestMap);
            Log.i(TAG, "\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n put  url = " + url + "\n request = \n" + body);
            RequestBody requestBody = RequestBody.create(TYPE_JSON, body);


            Request request = builder.url(url).put(requestBody).build();

            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ResponseBody body = response.body();
                    if (null != body) {
                        String result = body.string();

                        try {
                            JsonUtils.parseObject(result, BaseBean.class);
                            listener.onHttpResponse(requestCode, result, null);


                        } catch (Exception e) {
//                            listener.onHttpResponse(requestCode, null, null);

                        }


                        Log.i(TAG, "\n request put  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
                    } else {
                    }
                }
            });
        } catch (Exception ex) {

        }
    }


    public void patch(final Map<String, Object> requestMap, final String url, final int requestCode, final OnHttpResponseListener listener) {

        OkHttpClient.Builder ClientBuilder = new OkHttpClient.Builder();
        ClientBuilder.connectTimeout(100, TimeUnit.SECONDS);
        ClientBuilder.readTimeout(100, TimeUnit.SECONDS);
        ClientBuilder.writeTimeout(100, TimeUnit.SECONDS);
        OkHttpClient mOkHttpClient = ClientBuilder.build();

        try {
            Request.Builder builder = new Request.Builder();
            //目前头信息只有token
            builder.addHeader("token", PreferencesUtil.getString(context, "user_token", ""));


            String body = JsonUtils.toJSONString(requestMap);
            Log.i(TAG, "\n\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n put  url = " + url + "\n request = \n" + body);
            RequestBody requestBody = RequestBody.create(TYPE_JSON, body);


            Request request = builder.url(url).patch(requestBody).build();

            mOkHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ResponseBody body = response.body();
                    if (null != body) {
                        String result = body.string();

//                        try {
//                            JsonUtils.parseObject(result, BaseBean.class);
                        listener.onHttpResponse(requestCode, result, null);


//                        }catch (Exception e){
//                            listener.onHttpResponse(requestCode, null, null);
//
//                        }


                        Log.i(TAG, "\n request put  result = \n" + result + "\n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n\n");
                    } else {
                    }
                }
            });
        } catch (Exception ex) {

        }
    }


    /**
     * POST请求，以FORM表单形式提交，最快在 19.0 删除，请尽快迁移到 {@link #post(Map, String, int, OnHttpResponseListener)}
     *
     * @param paramList   请求参数列表，（可以一个键对应多个值）
     * @param url         网络地址
     * @param requestCode 请求码，类似onActivityResult中请求码，当同一activity中以实现接口方式发起多个网络请求时，请求结束后都会回调
     *                    {@link OnHttpResponseListener#onHttpResponse(int, String, Exception)}<br>
     *                    {@link OnHttpResponseListener#onHttpResponse(int, String, Exception)}<br/>
     *                    在发起请求的类中可以用requestCode来区分各个请求
     * @param listener
     */
    @Deprecated
    public void post(final List<Parameter> paramList, final String url,
                     final int requestCode, final OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        if (paramList != null) {
            for (Parameter p : paramList) {
                request.put(p.key, p.value);
            }
        }
        post(request, url, requestCode, listener);
    }


    //httpGet/httpPost 内调用方法 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * @param url
     * @return
     */
    private OkHttpClient getHttpClient(String url) {
        Log.i(TAG, "getHttpClient  url = " + url);
        if (StringUtil.isEmpty(url)) {
            Log.e(TAG, "getHttpClient  StringUtil.isEmpty(url) >> return null;");
            return null;
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(105, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        Map<String, String> map = new LinkedHashMap<>();
                        if (cookies != null) {
                            for (Cookie c : cookies) {
                                if (c != null && c.name() != null && c.value() != null) {
                                    map.put(c.name(), StringUtil.get(c.value()));
                                }
                            }
                        }
                        saveCookie(url == null ? null : url.host(), JsonUtils.toJSONString(map));//default constructor not found  cookies));
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        String host = url == null ? null : url.host();
                        Map<String, String> map = host == null ? null : JsonUtils.parseObject(getCookie(host), HashMap.class);

                        List<Cookie> list = new ArrayList<>();

                        Set<Map.Entry<String, String>> set = map == null ? null : map.entrySet();
                        if (set != null) {
                            for (Map.Entry<String, String> entry : set) {
                                if (entry != null && entry.getKey() != null && entry.getValue() != null) {
                                    list.add(new Cookie.Builder().domain(host).name(entry.getKey()).value(entry.getValue()).build());
                                }
                            }
                        }

                        return list;
                    }
                });

        //添加信任https证书,用于自签名,不需要可删除
        if (url.startsWith(StringUtil.URL_PREFIXs) && socketFactory != null) {
            builder.sslSocketFactory(socketFactory);
        }

        return builder.build();
    }


    public static final String KEY_COOKIE = "cookie";

    /**
     * @param host
     * @return
     */
    public String getCookie(String host) {
        if (host == null) {
            Log.e(TAG, "getCookie  host == null >> return \"\"");
            return "";
        }
        return context.getSharedPreferences(KEY_COOKIE, Context.MODE_PRIVATE).getString(host, "");
    }

    /**
     * @param host
     * @param value
     */
    public void saveCookie(String host, String value) {
        if (host == null) {
            Log.e(TAG, "saveCookie  host == null >> return;");
            return;
        }
        context.getSharedPreferences(KEY_COOKIE, Context.MODE_PRIVATE)
                .edit()
                .remove(host)
                .putString(host, value)
                .commit();
    }


    /**
     * @param client
     * @param request
     * @return
     * @throws Exception
     */
    private String getResponseJson(OkHttpClient client, Request request) throws Exception {
        if (client == null || request == null) {
            Log.e(TAG, "getResponseJson  client == null || request == null >> return null;");
            return null;
        }
        Response response = client.newCall(request).execute();

        return response.body().string();
//        return response.isSuccessful() ? response.body().string() : null;
    }


    //httpGet/httpPost 内调用方法 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * 上传图片
     *
     * @param url
     * @param imagePath 图片路径
     * @return 新图片的路径
     * @throws IOException
     * @throws JSONException
     */
    public static String uploadImage(String url, String imagePath) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.i("imagePath", imagePath);
        File file = new File(imagePath);
        RequestBody image = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imagePath, image)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        return jsonObject.optString("image");
    }

}