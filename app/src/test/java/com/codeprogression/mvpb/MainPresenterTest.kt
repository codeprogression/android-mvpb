package com.codeprogression.mvpb

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    lateinit private var presenter: MainPresenter
    lateinit private var viewModel: MainView.ViewModel

    @Before
    fun setup() {
        presenter = MainPresenter()
        viewModel = MainView.ViewModel()
    }

    @Test
    fun smokeTest() {
        presenter.attach(viewModel)
        assertThat(viewModel.number.get()).isEqualTo(10)
    }

    @Test
    fun test_presenterUpdatesViewModel() {
        presenter.attach(viewModel)
        presenter.add()
        assertThat(viewModel.number.get()).isEqualTo(11)
    }

    @Test
    fun test_presenterResetsNumber() {
        viewModel.number.set(1)
        presenter.attach(viewModel)
        assertThat(viewModel.number.get()).isEqualTo(10)
    }

    @Test
    fun test_presenterDoesNotResetNumber() {
        viewModel.number.set(15)
        presenter.attach(viewModel)
        assertThat(viewModel.number.get()).isEqualTo(15)
    }
}