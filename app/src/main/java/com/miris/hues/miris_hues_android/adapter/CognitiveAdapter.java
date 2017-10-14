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

public class CognitiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemClickListener {
    private List<CognitiveTextData> items;
    private Context context;

    public CognitiveAdapter(Context context, List<CognitiveTextData> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        holder = new JsonTextDataViewHolder(v, this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder == null) {
            return;
        }

        CognitiveTextData item = items.get(position);
        String str = "";

        for (int linesIdx = 0; linesIdx < item.getLines().size(); linesIdx++) {
            CognitiveTextData.Lines lines = item.getLines().get(linesIdx);
            for (int wordsIdx = 0; wordsIdx < lines.getWords().size(); wordsIdx++) {
                str += lines.getWords().get(wordsIdx).getText() + " ";
            }
            str += '\n';
        }

        TextView textView = new TextView(context);
        textView.setText(str);
        textView.setTextColor(Color.BLACK);
        ((JsonTextDataViewHolder) holder).root.addView(textView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onItemClick(int position) {

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
