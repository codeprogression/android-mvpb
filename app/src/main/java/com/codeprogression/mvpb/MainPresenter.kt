package com.codeprogression.mvpb

import com.codeprogression.mvpb.core.BaseBindingPresenter
import com.codeprogression.mvpb.core.PerActivity

import javax.inject.Inject

@PerActivity
class MainPresenter
@Inject
constructor() : BaseBindingPresenter<MainView.ViewModel>() {

    override fun load() {
        if (viewModel.number.get() < 10) {
            viewModel.number.set(10)
        }
    }

    fun add() {
        val number = viewModel.number.get() + 1
        viewModel.number.set(number)
    }

}