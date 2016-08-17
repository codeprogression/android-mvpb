package com.codeprogression.mvpb

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.DataBindingUtil
import android.databinding.ObservableInt
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.codeprogression.mvpb.databinding.MainViewBinding
import javax.inject.Inject

class MainView(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    @Inject
    lateinit internal var presenter: MainPresenter
    lateinit private var binding:  MainViewBinding
    private var viewModel: ViewModel = ViewModel()

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (!isInEditMode) {
            MainActivity.component()?.inject(this)
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isInEditMode) return
        binding = DataBindingUtil.bind(this)
        binding.viewModel = viewModel
        binding.listener = this
        presenter.attach(viewModel)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detach()
        binding.unbind()
    }

    fun add(@Suppress("UNUSED_PARAMETER") v: View) {
        // Use presenter for domain operations
        presenter.add()
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = SavedState(super.onSaveInstanceState())

        /* Save view-specific data (e.g., repeating view positions)
         * Save viewModel defaults
         */
        superState.number = viewModel.number.get()

        return superState
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }

        super.onRestoreInstanceState(state.superState)

        // update viewModel
        // update view-specific state
        viewModel = ViewModel()
        viewModel.number.set(state.number)
    }

    class ViewModel : BaseObservable() {
        val number = ObservableInt()
    }

    class SavedState : View.BaseSavedState {

        var number: Int = 0

        constructor(superState: Parcelable) : super(superState) {

        }

        private constructor(`in`: Parcel) : super(`in`) {
            number = `in`.readInt()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(number)
        }

        companion object {

            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}
