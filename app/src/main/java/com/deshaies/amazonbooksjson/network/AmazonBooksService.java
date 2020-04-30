package com.deshaies.amazonbooksjson.network;

import com.deshaies.amazonbooksjson.model.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import static com.deshaies.amazonbooksjson.util.Constants.*;

public interface AmazonBooksService {

    @GET(URL_POSTFIX)
    Observable<List<Result>> getAmazonBooksRx();
}
