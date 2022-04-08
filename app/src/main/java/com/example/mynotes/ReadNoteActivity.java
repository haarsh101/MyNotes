package com.example.mynotes;

import android.net.Uri;
import android.os.Bundle;
import android.transition.Slide;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mynotes.context.GlobalContext;
import com.example.mynotes.model.Note;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class ReadNoteActivity extends AppCompatActivity {
    private TextView noteTitle, noteDescription;
    private ImageSlider imageSlider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_notes);

        String noteId = getIntent().getExtras().getString("id");

        noteTitle = findViewById(R.id.note_title);
        noteDescription = findViewById(R.id.note_description);
        imageSlider = findViewById(R.id.read_note_image_slider);


        List<Note> notes = Paper.book().read(GlobalContext.loggedInUser.getId());
        for (int i=0;i<notes.size();i++) {
            if (noteId.equals(notes.get(i).getId())) {
                Note note = notes.get(i);
                noteTitle.setText(note.getTitle());
                noteDescription.setText(note.getDescription());
                List<SlideModel> slides = new ArrayList<>();
                for (int j=0;j<note.getImages().size();j++) {
                    SlideModel slideModel = new SlideModel(note.getImages().get(j), ScaleTypes.CENTER_INSIDE);
                    slides.add(slideModel);
                }
                imageSlider.setImageList(slides, ScaleTypes.CENTER_INSIDE);
            }
        }
    }
}
