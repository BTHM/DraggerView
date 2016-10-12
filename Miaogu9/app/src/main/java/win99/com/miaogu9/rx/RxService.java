package win99.com.miaogu9.rx;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import win99.com.miaogu9.domain.ContentBean;
import win99.com.miaogu9.domain.HomeInfo;

/**
 * @author sanshu
 * @data 16/10/8 上午9:45
 * @ToDo ${TODO}
 */

public interface RxService {
    ////设置顶部轮播图@GET("/template/info/get/1/3")
    @GET("V1/template/info/get/1/3")
    Observable<HomeInfo<ContentBean>> getTitle(@Query("labelId") int labelId);
}
