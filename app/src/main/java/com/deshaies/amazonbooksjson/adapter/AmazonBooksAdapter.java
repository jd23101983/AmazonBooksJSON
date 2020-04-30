package com.deshaies.amazonbooksjson.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.deshaies.amazonbooksjson.R;
import com.deshaies.amazonbooksjson.model.Result;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AmazonBooksAdapter extends RecyclerView.Adapter<AmazonBooksAdapter.BookViewHolder> {

    private List<Result> bookResults;
    private ViewGroup theParent;

    public AmazonBooksAdapter(List<Result> bookResults) {
        this.bookResults = bookResults;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        theParent = parent;

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        try {
            if (bookResults.get(position) != null) {
                Glide.with(theParent.getContext())
                        .load(bookResults.get(position).getImageURL())
                        .placeholder(R.drawable.placeholder)
                        .into(holder.bookImage);
                holder.bookTitle.setText(bookResults.get(position).getTitle());
             }
        }
        catch (Exception e) {e.printStackTrace(); }
    }

    @Override
    public int getItemCount() {
        return bookResults.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.book_image)
        ImageView bookImage;

        @BindView(R.id.book_title_textview)
        TextView bookTitle;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}