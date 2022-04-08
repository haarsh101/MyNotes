package com.example.mynotes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.mynotes.adapter.NotesViewAdapter;
import com.example.mynotes.context.GlobalContext;
import com.example.mynotes.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    private ImageSlider imageSlider;
    private RecyclerView notesRecyclerView;
    FloatingActionButton floatingActionButton;
    private NotesViewAdapter notesViewAdapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Paper.init(this);

        notesRecyclerView = findViewById(R.id.recycler_notes_items);
        floatingActionButton = findViewById(R.id.create_note_fab);

        notesRecyclerView.setHasFixedSize(true);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Note> notes = null;
        if(GlobalContext.loggedInUser != null) {
            notes = Paper.book().read(GlobalContext.loggedInUser.getId());
        }

        notesViewAdapter = new NotesViewAdapter(this, notes);
        notesRecyclerView.setAdapter(notesViewAdapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,NewNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (notesViewAdapter != null) {
            notesViewAdapter.notifyDataSetChanged();
        }
    }
}
