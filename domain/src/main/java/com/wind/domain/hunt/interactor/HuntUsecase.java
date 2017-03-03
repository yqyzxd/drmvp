package com.wind.domain.hunt.interactor;

import com.wind.data.Repository;
import com.wind.data.hunt.request.HuntRequest;
import com.wind.data.hunt.response.HuntResponse;
import com.wind.base.usecase.Usecase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by wind on 16/5/20.
 */
public class HuntUsecase extends Usecase<HuntRequest,HuntResponse> {

    private Repository<HuntRequest,HuntResponse> repository;
    @Inject
    public HuntUsecase(Repository<HuntRequest,HuntResponse> repository){
        this.repository=repository;
    }
    @Override
    protected Observable<HuntResponse> buildUsecaseObservable(HuntRequest request) {
        return repository.get(request);
    }
}
