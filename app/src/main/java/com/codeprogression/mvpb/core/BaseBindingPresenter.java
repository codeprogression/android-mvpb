package com.codeprogression.mvpb.core;

import android.databinding.Observable;
import android.support.annotation.CallSuper;

import rx.subscriptions.CompositeSubscription;

public abstract class BaseBindingPresenter<VM extends Observable> {

    private CompositeSubscription subscriptions;
    private VM viewModel;

    @CallSuper
    public final void attach(VM viewModel) {
        if (this.viewModel == null) {
            this.viewModel = viewModel;
        }
        resume();
    }

    @CallSuper
    public final void detach() {
        this.viewModel = null;
        pause();
    }

    protected final boolean hasViewModel(){
        return viewModel != null;
    }

    public final VM getViewModel() {
        return viewModel;
    }

    private void resume() {
        if (subscriptions != null) {
            subscriptions.unsubscribe();
        }

        this.subscriptions = new CompositeSubscription();
        load();
    }

    private void pause() {
        unload();
        if (subscriptions != null) {
            subscriptions.unsubscribe();
        }
    }


    protected abstract void load();

    protected void unload(){

    }

}

