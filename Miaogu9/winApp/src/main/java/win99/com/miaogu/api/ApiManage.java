package win99.com.miaogu.api;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import win99.com.miaogu.MiaoGuApp;
import win99.com.miaogu.util.Constant;
import win99.com.miaogu.util.NetWorkUtil;


/**
 * @author sanshu
 * @data 16/10/8 下午2:26
 * @ToDo ${TODO}
 */

public class ApiManage {
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Response originalResponse = chain.proceed(chain.request());

            if (NetWorkUtil.isNetWorkAvailable(MiaoGuApp.sContext)) {
                int maxAge = 60 * 60; // 在线缓存在1分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    public static ApiManage apiManage;
    private static File         httpCacheDirectory = new File(MiaoGuApp.sContext.getCacheDir(), "MiaoGuCache");
    private static int          cacheSize          = 10 * 1024 * 1024; // 10 MiB
    private static Cache        cache              = new Cache(httpCacheDirectory, cacheSize);
    private static OkHttpClient client             = new OkHttpClient.Builder()
                                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                                .cache(cache)
                                .build();
   
    private MiaoGuApi miaoGuApi;

    public static ApiManage getInstence() {
        if (apiManage == null) {
            synchronized (ApiManage.class) {
                if (apiManage == null) {
                    apiManage = new ApiManage();
                }
            }
        }
        return apiManage;
    }

    public MiaoGuApi getZhihuApiService() {
        if (miaoGuApi == null) {
            synchronized (this) {
                if (miaoGuApi == null) {
                    miaoGuApi = new Retrofit.Builder()
                            .baseUrl(Constant.BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build().create(MiaoGuApi.class);
                }
            }
        }

        return miaoGuApi;
    }
    
}
