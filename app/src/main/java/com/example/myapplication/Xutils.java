package com.example.myapplication;

import android.util.Log;
import android.widget.ImageView;


import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;
import java.util.Map;

public class Xutils {
    private static DbManager db;

//    public static  final String BASE_URL = "http://10.0.2.2:8080";

    public static  final String BASE_URL = "http://116.62.177.7:8019/";

    public static String BOOK_URL = "https://api.jike.xyz/situ/book/isbn/";
    public static String API_KEY = "13932.57660212276931d6fc1d54bdf6acd465.6de1ac347a722189968dfe4fb07c451a";

    /**根据用户输入的isbn搜索图书*/
    public static void getBook(String isbn,final GetDataCallback callback) {

        String bookUrl = BOOK_URL+isbn+"?"+"apikey="+API_KEY;

        RequestParams params = new RequestParams(bookUrl);

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("url", String.valueOf(params));
                callback.success(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.failed();
            }
            @Override
            public void onCancelled(CancelledException cex) {}
            @Override
            public void onFinished() {}
        });
    }

    /**get请求*/
        public static void get(String url, Map<String, Object> parms, final GetDataCallback callback) {
            RequestParams params = new RequestParams(BASE_URL + url);
            if(parms!=null){
                for (String key : parms.keySet()) {
                    params.addParameter(key, parms.get(key));
                }
            }
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    callback.success(result);
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    callback.failed();
                }
                @Override
                public void onCancelled(CancelledException cex) {}
                @Override
                public void onFinished() {}
            });
        }

    /**post请求：map是请求参数*/
        public static void post(String url, Map<String, Object> parms, final GetDataCallback callback) {
            RequestParams params = new RequestParams(BASE_URL + url);
            if(parms!=null){
                for (String key : parms.keySet()) {
                    params.addParameter(key, parms.get(key));
                }
            }
            Log.d("post", "post: "+params);
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    if(callback!=null){
                        callback.success(result);
                    }
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d("post", "onError: ex"+ex);
                    if(callback!=null){
                        callback.failed();
                    }
                }
                @Override
                public void onCancelled(CancelledException cex) {}
                @Override
                public void onFinished() {}
            });
        }

        /**上传文件*/
        public static void uploadFile(String url, List<KeyValue> list, final GetDataCallback callback) {
            RequestParams params = new RequestParams(BASE_URL+url);
            params.setAsJsonContent(true);
            MultipartBody body = new MultipartBody(list, "UTF-8");
            params.setRequestBody(body);
            x.http().post(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    callback.success(result);
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    callback.failed();
                }
                @Override
                public void onCancelled(CancelledException cex) {}
                @Override
                public void onFinished() {}
            });
        }
        /**回调接口*/
        public interface GetDataCallback {
            void success(String result);
            void failed(String... args);
        }

    /**初始化数据库*/
    public static DbManager initDbConfiginit()
    {
        if(db==null) {
            //本地数据的初始化
            DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                    .setDbName("xutils3_db") //设置数据库名
                    .setDbVersion(1) //设置数据库版本
                    .setDbOpenListener(new DbManager.DbOpenListener() {
                        @Override
                        public void onDbOpened(DbManager db) {
                            db.getDatabase().enableWriteAheadLogging();
                            //开启WAL, 对写入加速提升巨大
                        }
                    })
                    .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                            //数据库升级操作
                        }
                    });
            //获取数据库实例
            db = x.getDb(daoConfig);
        }
        return db;
    }

    /**绑定图片*/
    public static void display(ImageView imageView, String iconUrl, int radius) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setRadius(DensityUtil.dip2px(radius))
                .setIgnoreGif(false)
                .setUseMemCache(false)//设置使用缓存
                .setCrop(true)//是否对图片进行裁剪
               .setFailureDrawableId(R.mipmap.ic_launcher)
               .setLoadingDrawableId(R.mipmap.ic_launcher)
                .build();

        x.image().bind( imageView, iconUrl+"?"+ getimestring(), imageOptions,new Callback.CacheCallback() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(Object result) {
                return false;
            }

        });
    }




        public  static String getimestring()

        {
            return System.currentTimeMillis()+"";
        }
}