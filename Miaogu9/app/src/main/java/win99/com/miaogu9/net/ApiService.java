package win99.com.miaogu9.net;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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
 * @ToDo ${TODO}
 */

public interface ApiService {
    @GET("api/hp/detail/{id}")
    Call<ResultWrapper<ResultInfo>> getDetailInfo(@Path("id") int id);

    ////设置顶部轮播图
    //@GET("V1/template/info/get/1/3")
    @GET("/template/info/get/1/3")
    Call<HomeInfo<ContentBean>> getHomeTitle(@Query("labelId") int labelId);

    //垂直滚动广告条的实现使用
    @GET("V1/template/info/get/{position}/{pageSize}")
    Call<HomeInfo<Announcement>> getAnnounce(@Path("position") int position, @Path("pageSize") int pageSize,
                                             @QueryMap Map<String, String> map);

    /*//横着可以滚动的投顾热评
    @GET("V1/issues/show/hot/1/5")
    Call<ResponseBody> getHotAdviser();*/
    @GET("V1/issues/show/hot/1/5")
    Call<ArrayList<ModelHotAdviser>> getHotAdviser();

    //视频获取
    //垂直滚动广告条的实现使用
    //V1/template/info/get/1/10
    @GET("V1/template/info/get/{position}/{pageSize}")
    Call<HomeInfo<TvInfo>> getTvData(@Path("position") int position, @Path("pageSize") int pageSize,
                                     @QueryMap Map<String, String> map);
    /*@GET("V1/template/info/get/1/10")
    Call<HomeInfo<TvInfo>> getTvData(@QueryMap Map<String, String> map);*/

    //用户登陆
    @FormUrlEncoded
    @POST("V1/member/auth/login")
    Call<UserInfo> login(@FieldMap Map<String,String> map);

    //reset Name
    @FormUrlEncoded
    @POST("V1/member/modify/nickname")
    Call<AccountInfo> reName(@FieldMap Map<String,String> map);

    //修改密码
    @FormUrlEncoded
    @POST("V1/member/modify/passwd/original")
    Call<AccountInfo> changePwd(@FieldMap Map<String,String> map);

    //退出登陆
    @FormUrlEncoded
    @POST("V1/member/auth/logout")
    Call<AccountInfo> logout(@FieldMap Map<String,String> map);

    //注册
    @FormUrlEncoded
    @POST("V1/member/auth/{function}")
    Call<RegisterInfo> register(@Path("function") String function,@FieldMap Map<String,String> map);

    //发送验证码
    @FormUrlEncoded
    @POST("V1/group/sms/validator")
    Call<RegisterInfo> getSmsCode(@FieldMap Map<String,String> map);


}
