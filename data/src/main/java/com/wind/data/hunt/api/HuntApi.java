package com.wind.data.hunt.api;

import com.wind.base.api.IPageApi;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;

import java.util.Map;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import rx.Observable;

/**
 * Created by wind on 16/5/20.
 */
public class HuntApi implements IPageApi<HuntRequest,HuntResponse>{

    private Retrofit mRetrofit;
    @Inject
    public HuntApi(Retrofit retrofit){
        this.mRetrofit=retrofit;
    }
    @Override
    public Observable<HuntResponse> get(@FieldMap final Map<String, String> fieldMap) {
        /*return Observable.create(new Observable.OnSubscribe<HuntResponse>() {
            @Override
            public void call(Subscriber<? super HuntResponse> subscriber) {
                try {
                    String result=OkHttpUtils.post()
                            .url("http://marryu.miaotu.net/app/user/user_list")
                            .params(fieldMap)
                            .build()
                            .execute().body().string();

                    subscriber.onNext(JsonParser.parserObject(result,HuntResponse.class));
                    subscriber.onCompleted();
                }catch (Exception e){
                    e.printStackTrace();
                    subscriber.onError(e);
                }

            }
        });*/

        HuntPageApi api=mRetrofit.create(HuntPageApi.class);
        return api.get(fieldMap);
    }

    @Override
    public Observable<HuntResponse> get(HuntRequest request) {
        return null;
    }
}
