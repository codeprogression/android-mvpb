package com.codeprogression.mvpb

import com.codeprogression.mvpb.core.PerActivity
import dagger.Component

@PerActivity
@Component
interface MainComponent {
    fun inject(view: MainActivity)
    fun inject(view: MainView)
}