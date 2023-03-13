package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.bean.BookInfo;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ChatViewHolder> {
    private List<BookInfo> mEntityList = new ArrayList<>();


    public void setEntityList(List<BookInfo> mEntityList) {
        this.mEntityList = mEntityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_book_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        private TextView mText;

        public ChatViewHolder(View itemView) {
            super(itemView);

        }
    }
}
