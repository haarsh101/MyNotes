package com.example.mynotes.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;
import com.example.mynotes.ReadNoteActivity;
import com.example.mynotes.ViewHolder.NotesViewHolder;
import com.example.mynotes.model.Note;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotesViewAdapter extends RecyclerView.Adapter<NotesViewHolder>{
    private Context context;
    private List<Note> notes;

    public NotesViewAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_notes_layout, parent, false);
        NotesViewHolder holder = new NotesViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.noteTitle.setText(note.getTitle());
        holder.noteDescription.setText(note.getDescription());
        if(note.getImages()!=null && note.getImages().size()>0) {
            Picasso.get().load(Uri.parse(note.getImages().get(0))).into(holder.noteImage);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ReadNoteActivity.class);
                intent.putExtra("id", note.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes!=null ? notes.size():0;
    }
}
