package com.wind.domain;

import com.wind.data.base.BaseRequest;
import com.wind.data.base.BaseResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by wind on 16/5/19.
 */
public abstract class Usecase<Q extends BaseRequest,R extends BaseResponse> {



    private  Subscription mSubscription= Subscriptions.empty();
    public void execute(Q request,Subscriber<R> subscriber){
         mSubscription=buildUsecaseObservable(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    protected abstract Observable<R> buildUsecaseObservable(Q request);


    public void unsubscribe(){
        if (!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
}