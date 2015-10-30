package com.codeprogression.mvpb;

import com.codeprogression.mvpb.core.BaseBindingPresenter;
import com.codeprogression.mvpb.core.PerActivity;

import javax.inject.Inject;

@PerActivity
public class MainPresenter extends BaseBindingPresenter<MainView.ViewModel> {

    @Inject
    public MainPresenter() {
    }

    @Override
    protected void load() {
        if (getViewModel().number.get() < 10) {
            getViewModel().number.set(10);
        }

    }


    public void add(){
        int number = getViewModel().number.get() + 1;
        getViewModel().number.set(number);
    }

}