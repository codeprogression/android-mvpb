package com.codeprogression.mvpb.core

import android.databinding.Observable
import android.support.annotation.CallSuper
import rx.Subscription
import rx.subscriptions.CompositeSubscription

abstract class BaseBindingPresenter<VM : Observable> {

    private var subscriptions: CompositeSubscription = CompositeSubscription()
    lateinit protected var viewModel: VM

    protected var hasViewModel: Boolean = false
        private set

    @CallSuper
    fun attach(viewModel: VM) {
        hasViewModel = true
        this.viewModel = viewModel
        resume()
    }

    @CallSuper
    fun detach() {
        hasViewModel = false
        pause()
    }

    private fun resume() {
        subscriptions.unsubscribe()
        subscriptions = CompositeSubscription()
        load()
    }

    private fun pause() {
        unload()
        subscriptions.unsubscribe()
    }

    protected abstract fun load()

    protected fun unload() {

    }

    fun addSubscription(subscription: Subscription) {
        subscriptions.add(subscription)
    }

    fun removeSubscription(subscription: Subscription) {
        subscriptions.remove(subscription)
    }

}

