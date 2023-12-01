package com.example.testlogin.mode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class In4ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public In4ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is in4 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}