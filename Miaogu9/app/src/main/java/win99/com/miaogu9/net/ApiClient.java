package win99.com.miaogu9.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import win99.com.miaogu9.util.Constant;

/**
 * @author sanshu
 * @data 16/9/7 下午4:19
 * @ToDo ${TODO}
 */

public class ApiClient {

    /*static String   BASE_URL  = "http://v3.wufazhuce.com:8000/";
    static Retrofit sRetrofit = new Retrofit
            .Builder()
            .baseUrl(BASE_URL) // 主机地址
            .addConverterFactory(GsonConverterFactory.create()) // 转换工厂
            .build();// 构建*/

    public static Retrofit sRetrofit = new Retrofit
            .Builder()
            .baseUrl(Constant.BASE_URL) // 主机地址
            .addConverterFactory(GsonConverterFactory.create()) // 转换工厂
            .build();// 构建

    //
    public static Retrofit sRetrofitQa = new Retrofit
            .Builder()
            .baseUrl(Constant.URL_QA) // 主机地址
            .addConverterFactory(GsonConverterFactory.create())// 转换工厂
            .build();// 构建

    //登陆
    public static Retrofit sRetrofitMember = new Retrofit
            .Builder()
            .baseUrl(Constant.MEMBER_URL) // 主机地址
            .addConverterFactory(GsonConverterFactory.create())// 转换工厂
            .build();// 构建*/

}
