package com.deshaies.amazonbooksjson.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.deshaies.amazonbooksjson.R;
import com.deshaies.amazonbooksjson.adapter.AmazonBooksAdapter;
import com.deshaies.amazonbooksjson.model.Result;
import com.deshaies.amazonbooksjson.util.DebugLogger;
import com.deshaies.amazonbooksjson.viewmodel.AmazonBooksViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.amazon_books_recyclerview)
    RecyclerView amazonBooksRecyclerView;

    private AmazonBooksViewModel amazonBooksViewModel;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        amazonBooksViewModel = ViewModelProviders.of(this).get(AmazonBooksViewModel.class);
        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(amazonBooksViewModel.getAmazonBooksRx().subscribe(amazonBookResults -> {
            displayInformationRx(amazonBookResults);
        }, throwable -> {
            DebugLogger.logError(throwable);
        }));

    }

    private void displayInformationRx(List<Result> amazonBookResults) {

        updateRecyclerView(amazonBookResults);

        for (int i = 0; i < amazonBookResults.size(); i++) {
            if (amazonBookResults.get(i) != null) {
                DebugLogger.logDebug("RxJava : " + amazonBookResults.get(i).getTitle());
                DebugLogger.logDebug("RxJava : " + amazonBookResults.get(i).getImageURL());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void updateRecyclerView(List<Result> amazonBookResults) {
        AmazonBooksAdapter adapter = new AmazonBooksAdapter(amazonBookResults);
        amazonBooksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        amazonBooksRecyclerView.setAdapter(adapter);
    }
}
