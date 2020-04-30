package com.deshaies.amazonbooksjson.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.deshaies.amazonbooksjson.model.Result;
import com.deshaies.amazonbooksjson.network.AmazonBooksRetrofitInstance;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AmazonBooksViewModel extends AndroidViewModel {

    private AmazonBooksRetrofitInstance amazonBooksRetrofitInstance;

    public AmazonBooksViewModel(@NonNull Application application) {
        super(application);

        amazonBooksRetrofitInstance = new AmazonBooksRetrofitInstance();
    }

    public Observable<List<Result>> getAmazonBooksRx() {
        return  amazonBooksRetrofitInstance
                .getAmazonBooksRx()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
