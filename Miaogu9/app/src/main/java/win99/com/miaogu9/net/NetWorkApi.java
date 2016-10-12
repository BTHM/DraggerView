package win99.com.miaogu9.net;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import win99.com.miaogu9.domain.AccountInfo;
import win99.com.miaogu9.domain.Announcement;
import win99.com.miaogu9.domain.ContentBean;
import win99.com.miaogu9.domain.HomeInfo;
import win99.com.miaogu9.domain.ModelHotAdviser;
import win99.com.miaogu9.domain.RegisterInfo;
import win99.com.miaogu9.domain.ResultInfo;
import win99.com.miaogu9.domain.ResultWrapper;
import win99.com.miaogu9.domain.TvInfo;
import win99.com.miaogu9.domain.UserInfo;

/**
 * @author sanshu
 * @data 16/9/7 下午4:19
 * @ToDo $  所有网络请求均写在这里
 * retrofit请求原型
 * Retrofit retrofit = new Retrofit.Builder().baseUrl("").addConverterFactory(GsonConverterFactory.
    create()).build();
    ApiService apiService = retrofit.create(NetWorkApi.apiService.getClass());
    Call<ResultWrapper<ResultInfo>> call = apiService.getDetailInfo(1);
    call.enqueue(new Callback<ResultWrapper<ResultInfo>>() {
    @Override
    public void onResponse(Call<ResultWrapper<ResultInfo>> call, Response<ResultWrapper<ResultInfo>> response) {

    }

    @Override
    public void onFailure(Call<ResultWrapper<ResultInfo>> call, Throwable t) {

    }
    });
 */

public class NetWorkApi {


    // 得到接口对象的实体
    static ApiService apiService = ApiClient.sRetrofit.create(ApiService.class);

    static ApiService apiServiceQa = ApiClient.sRetrofitQa.create(ApiService.class);

    static ApiService sRetrofitMember = ApiClient.sRetrofitMember.create(ApiService.class);


    //这里写成静态的不合适,所以放进调用的方法的形参 static OnResultListener mOnResultListener;

    //OnResultListener mOnResultListener;



    public interface OnResultListener<T>{
        void onSuccess(int code, T result);
        void onFailure(Throwable t);
    }

    /**
     * @param onResultListener,这里的是 没掉用的地方
     */
    /*public void setOnResultListener(OnResultListener onResultListener){
        mOnResultListener=onResultListener;
    }*/

    /**
     * @param call
     * @param onResultListener 在回调里的方法是同步的 可以直接进行ui操作
     * @param <T>
     */
    private static <T> void enqueue(Call<T> call, final OnResultListener<T> onResultListener){
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                /*if (response.code() == 200) {
                    if (onResultListener != null) {
                        onResultListener.onSuccess(response.code(),response.body());
                        //怎样判断 code的值不是200 时的处理
                    }
                }else{
                    if (onResultListener != null) {
                        onResultListener.onSuccess(response.code(),response.body());
                        //怎样判断 code的值不是200 时的处理
                    }
                }*/
                if (onResultListener != null) {
                    onResultListener.onSuccess(response.code(),response.body());
                    //怎样判断 code的值不是200 时的处理
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                if (onResultListener != null) {
                    onResultListener.onFailure(t);
                }
            }
        });

    }

    /**实际的网络请求,每一个网络请求创建一个方法,
     * @param id
     * @param onListener
     */
    public static void getNetData(int id,OnResultListener<ResultWrapper<ResultInfo>> onListener){

        Call<ResultWrapper<ResultInfo>> call = apiService.getDetailInfo(id);
        enqueue(call,onListener);

    }
    //获取顶部滚动广告条
    public static void getHomeTitle(int labelId,OnResultListener<HomeInfo<ContentBean>> onListener){

        Call<HomeInfo<ContentBean>> call = apiService.getHomeTitle(labelId);
        enqueue(call,onListener);
    }

    //垂直滚动广告条的实现使用
    public static void getAnnconce(int position,int pageSize,Map<String,String> map, OnResultListener<HomeInfo<Announcement>> onResultListener){

        Call<HomeInfo<Announcement>> call = apiService.getAnnounce(position,pageSize,map);
        enqueue(call,onResultListener);
    }

      //横着可以滚动的投顾热评
    public static void getHotAdviser(OnResultListener<ArrayList<ModelHotAdviser>> onResultListener){
        Call<ArrayList<ModelHotAdviser>> call = apiServiceQa.getHotAdviser();
        enqueue(call,onResultListener);
    }

    //视频获取
    public static void getTvData(int position, int PageSize, Map map, OnResultListener<HomeInfo<TvInfo>> onResultListener){
        Call call = apiService.getTvData(position, PageSize, map);
        enqueue(call,onResultListener);
    }
    /*public static void getTvData(Map map, OnResultListener<HomeInfo<TvInfo>> onResultListener){
        Call<HomeInfo<TvInfo>> call = apiServiceTv.getTvData(map);
        enqueue(call,onResultListener);
    }*/

    public static void login(Map<String,String> map,OnResultListener<UserInfo> onResultListener){
        Call<UserInfo> call = sRetrofitMember.login(map);
        enqueue(call,onResultListener);
    }

    //修改昵称
    public static void reName(Map<String,String> map,OnResultListener<AccountInfo> onResultListener){
        Call<AccountInfo> call = sRetrofitMember.reName(map);
        enqueue(call,onResultListener);
    }

    //修改密码
    public static void changePwd(Map<String,String> map,OnResultListener<AccountInfo> onResultListener){
        Call<AccountInfo> call = sRetrofitMember.changePwd(map);
        enqueue(call,onResultListener);
    }

    //退出登陆
    public static void logout(Map<String,String> map,OnResultListener<AccountInfo> onResultListener){
        Call<AccountInfo> call = sRetrofitMember.logout(map);
        enqueue(call,onResultListener);
    }

    //注册
    public static void register(String function,Map<String,String> map,OnResultListener<RegisterInfo> onResultListener){
        Call<RegisterInfo> call = sRetrofitMember.register(function,map);
        enqueue(call,onResultListener);
    }

    //发送获取验证码
    public static void getSmsCode(Map<String,String> map,OnResultListener<RegisterInfo> onResultListener){
        Call<RegisterInfo> call = sRetrofitMember.getSmsCode(map);
        enqueue(call,onResultListener);
    }

}
