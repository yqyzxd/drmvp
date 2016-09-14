package com.wind.drmvp.hunt.di;

import com.wind.base.di.BaseMvpComponent;
import com.wind.base.di.annotation.ActivityScope;
import com.wind.drmvp.hunt.mvp.presenter.ChatPresenter;
import com.wind.drmvp.hunt.mvp.view.ChatView;
import com.wind.drmvp.hunt.mvp.view.impl.ChatMvpLayout;

import dagger.Subcomponent;

/**
 * Created by wind on 16/5/20.
 */
@ActivityScope
@Subcomponent(modules = ChatModule.class)
public interface ChatComponent extends BaseMvpComponent<ChatView,ChatPresenter> {

    void inject(ChatMvpLayout layout);


}
