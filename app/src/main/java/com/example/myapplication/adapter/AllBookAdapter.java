package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.bean.BookInfo;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:全部已读书籍
 * @Author: dick
 * @CreateDate: 2023/3/13
 * @Version:
 */
public class AllBookAdapter extends RecyclerView.Adapter<AllBookAdapter.ChatViewHolder> {
    private List<BookInfo> mEntityList = new ArrayList<>();
    public void setEntityList(List<BookInfo> mEntityList) {
        this.mEntityList = mEntityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_all_book_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        if (position%2== 0) {
            holder.iv.setImageResource(R.drawable.image_history);

        } else {
            holder.iv.setImageResource(R.drawable.image_top);

        }
    }

    @Override
    public int getItemCount() {
        return mEntityList.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;

        public ChatViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image_book);

        }
    }
}
