package com.nerdsoft.coder.notty.contract;

import android.view.View;

public interface IMainContract {

    interface IView{
        void initViews();
    }
    interface IPresenter{
        void onClick(View view);
    }
}
