package com.example.mynotes.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;

public class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView noteTitle,noteDescription;
    public ImageView noteImage;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        noteTitle = itemView.findViewById(R.id.notes_title);
        noteDescription = itemView.findViewById(R.id.notes_description);
        noteImage = itemView.findViewById(R.id.notes_image);
    }

    @Override
    public void onClick(View view) {

    }
}
