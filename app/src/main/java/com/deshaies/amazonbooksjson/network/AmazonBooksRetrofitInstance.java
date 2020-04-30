package com.deshaies.amazonbooksjson.network;

import com.deshaies.amazonbooksjson.model.Result;
import com.deshaies.amazonbooksjson.util.Constants;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AmazonBooksRetrofitInstance {

    private AmazonBooksService amazonBooksService;
    private OkHttpClient client;

    public AmazonBooksRetrofitInstance() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        amazonBooksService = createAmazonBooksService(getRetrofitInstance());
    }

    private Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private AmazonBooksService createAmazonBooksService(Retrofit retrofitInstance) {
        return retrofitInstance.create(AmazonBooksService.class);
    }

    public Observable<List<Result>> getAmazonBooksRx() {
        return amazonBooksService.getAmazonBooksRx();
    }
}
