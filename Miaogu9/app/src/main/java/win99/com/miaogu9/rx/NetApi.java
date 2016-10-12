package win99.com.miaogu9.rx;


import rx.Observable;
import win99.com.miaogu9.domain.ContentBean;
import win99.com.miaogu9.domain.HomeInfo;

/**
 * @author sanshu
 * @data 16/10/8 上午9:50
 * @ToDo ${TODO}
 */

public class NetApi {

    public static RxService sRxService= Api.RxRetrofit.create(RxService.class);


    public interface OnRxResultListener<T>{
        void onSuccess(int code, T result);
        void onFailure(Throwable t);
    }


    private static <T> void enqueue(Observable<T> observable,  OnRxResultListener<T> onResultListener){
        /*call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                *//*if (response.code() == 200) {
                    if (onResultListener != null) {
                        onResultListener.onSuccess(response.code(),response.body());
                        //怎样判断 code的值不是200 时的处理
                    }
                }else{
                    if (onResultListener != null) {
                        onResultListener.onSuccess(response.code(),response.body());
                        //怎样判断 code的值不是200 时的处理
                    }
                }*//*
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
        });*/
        /*observable.just(t)
                .subscribe().*/

    }
    //获取顶部滚动广告条
    public static void getTitle(int labelId,OnRxResultListener<HomeInfo<ContentBean>> onListener){


    }

}
