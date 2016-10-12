package win99.com.miaogu.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import win99.com.miaogu.bean.ContentBean;
import win99.com.miaogu.bean.HomeInfo;


/**
 * @author sanshu
 * @data 16/10/8 下午2:50
 * @ToDo ${TODO}
 */

public interface MiaoGuApi {
    @GET("V1/template/info/get/1/3")
    Observable<HomeInfo<ContentBean>> getTitle(@Query("labelId") int labelId);
}
