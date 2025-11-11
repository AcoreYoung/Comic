package com.leo.comic.ui.local;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocalViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LocalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is local fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}