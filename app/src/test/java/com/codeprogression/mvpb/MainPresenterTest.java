package com.codeprogression.mvpb;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPresenterTest {

    private MainPresenter presenter;
    private MainView.ViewModel viewModel;

    @Before
    public void setup(){
        presenter = new MainPresenter();
        viewModel = new MainView.ViewModel();
    }

    @Test
    public void smokeTest(){
        presenter.attach(viewModel);
        assertThat(viewModel.number.get()).isEqualTo(10);
    }

    @Test
    public void test_presenterUpdatesViewModel(){
        presenter.attach(viewModel);
        presenter.add();
        assertThat(viewModel.number.get()).isEqualTo(11);
    }

    @Test
    public void test_presenterResetsNumber(){
        viewModel.number.set(1);
        presenter.attach(viewModel);
        assertThat(viewModel.number.get()).isEqualTo(10);
    }

    @Test
    public void test_presenterDoesNotResetNumber(){
        viewModel.number.set(15);
        presenter.attach(viewModel);
        assertThat(viewModel.number.get()).isEqualTo(15);
    }
}