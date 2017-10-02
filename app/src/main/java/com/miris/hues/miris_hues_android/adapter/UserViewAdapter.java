package com.miris.hues.miris_hues_android.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.miris.hues.miris_hues_android.R;
import com.miris.hues.miris_hues_android.data.CognitiveTextData;

import java.util.List;

/**
 * Created by Null on 2017-10-01.
 */

interface ItemClickListener {
    void onItemClick(int position);
}

public class UserViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemClickListener {
    private List<CognitiveTextData> items;
    private Context context;

    public UserViewAdapter(Context context, List<CognitiveTextData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        if (viewType == 0) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            holder = new UsersViewHolder(v, this);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
            holder = new JsonTextDataViewHolder(v, this);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }

        CognitiveTextData item = items.get(position);

        for (int linesIdx = 0; linesIdx < item.getLines().size(); linesIdx++) {
            CognitiveTextData.Lines lines = item.getLines().get(linesIdx);
            for (int wordsIdx = 0; wordsIdx < lines.getWords().size(); wordsIdx++) {
                TextView textView = new TextView(context);
                textView.setText(lines.getWords().get(wordsIdx).getText());
                textView.setTextColor(Color.BLACK);
                ((JsonTextDataViewHolder) holder).root.addView(textView);
            }
        }

//        final ImageLoader il = VolleyServerConnection.getInstance(context).getImageLoader();
//
//        ((UsersViewHolder) holder).name.setText(items.get(position).getName().getFirst());
//        il.get(items.get(position).getUserProfileImage().getLarge(),
//                ImageLoader.getImageListener(((UsersViewHolder) holder).profileImg,
//                        R.mipmap.ic_launcher,
//                        R.mipmap.ic_launcher));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(int position) {
//        Toast.makeText(context, getItemTitle(position), Toast.LENGTH_SHORT).show();
    }

//    public String getItemTitle(int position) {
//        return items.get(position).getName().getFirst();
//    }

    static class UsersViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView profileImg;

        public UsersViewHolder(View itemView, final ItemClickListener itemClickListener) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.item_text);
            this.profileImg = (ImageView) itemView.findViewById(R.id.item_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    static class JsonTextDataViewHolder extends RecyclerView.ViewHolder {
        private TextView boundingBox;
        private TextView word;
        private LinearLayout root;

        public JsonTextDataViewHolder(View itemView, final ItemClickListener itemClickListener) {
            super(itemView);
            this.word = (TextView) itemView.findViewById(R.id.item_word);
            this.root = (LinearLayout) itemView.findViewById(R.id.item_word_linearlayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
