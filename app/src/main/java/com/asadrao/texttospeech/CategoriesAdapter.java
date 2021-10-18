package com.asadrao.texttospeech;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    Context context;
    TextToSpeech tts;
    ArrayList<CategoryModel> categoryModelArrayList;

    public CategoriesAdapter(Context context, ArrayList<CategoryModel> categoryModelArrayList) {
        this.context = context;
        this.categoryModelArrayList = categoryModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_categories, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tvTitle.setText(categoryModelArrayList.get(position).title);
        //holder.tvTitle.setText("" + categoryModelArrayList.get(position).title.toUpperCase().charAt(0));
        holder.image.setImageResource(categoryModelArrayList.get(position).icon);

        if (categoryModelArrayList.get(position).id == 0) {
            holder.imgDelete.setVisibility(View.GONE);
        } else {
            holder.imgDelete.setVisibility(View.VISIBLE);
        }

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryModelArrayList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            Locale loc = new Locale("ur", "pk");
                            int result = tts.setLanguage(loc);
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Toast.makeText(context, "This language is not supported", Toast.LENGTH_SHORT).show();
                            } else {
                                tts.speak(categoryModelArrayList.get(position).title, TextToSpeech.QUEUE_ADD, null);
                            }
                        } else {
                            Toast.makeText(context, "Initialization Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView image, imgDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            image = itemView.findViewById(R.id.image);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }
}