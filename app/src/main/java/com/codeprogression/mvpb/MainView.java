package com.codeprogression.mvpb;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableInt;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.codeprogression.mvpb.databinding.MainViewBinding;

import javax.inject.Inject;

public class MainView extends RelativeLayout {

    @Inject
    MainPresenter presenter;
    private MainViewBinding binding;
    private ViewModel viewModel;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) return;
        MainActivity.component().inject(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) return;
        if (viewModel == null){
            viewModel = new ViewModel();
        }
        binding = DataBindingUtil.bind(this);
        binding.setViewModel(viewModel);
        binding.setListener(this);
        presenter.attach(viewModel);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        presenter.detach();
        binding.unbind();
    }

    public void add(View v) {
        // Use presenter for domain operations
        presenter.add();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        SavedState superState = new SavedState(super.onSaveInstanceState());

        /* Save view-specific data (e.g., repeating view positions)
         * Save viewModel defaults
         */
        superState.number = viewModel.number.get();

        return superState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        // update viewModel
        // update view-specific state
        viewModel = new ViewModel();
        viewModel.number.set(ss.number);
    }

    public static class ViewModel extends BaseObservable {
        public final ObservableInt number = new ObservableInt();
    }


    static class SavedState extends View.BaseSavedState {

        public int number;

        SavedState(Parcelable superState) {
            super(superState);

        }

        private SavedState(Parcel in) {
            super(in);
            number = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(number);
        }

        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

}
