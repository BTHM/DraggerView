package win99.com.miaogu9.rx;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import win99.com.miaogu9.util.Constant;

/**
 * @author sanshu
 * @data 16/10/8 上午9:26
 * @ToDo ${TODO}
 */

public class Api {

    public static Retrofit RxRetrofit =
            new Retrofit
                    .Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
}
