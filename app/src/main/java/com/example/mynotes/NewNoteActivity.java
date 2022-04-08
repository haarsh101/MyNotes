package com.example.mynotes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mynotes.context.GlobalContext;
import com.example.mynotes.model.Note;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.paperdb.Paper;

public class NewNoteActivity extends AppCompatActivity {
    private List<String> uris;
    private List<SlideModel> slides;
    private EditText InputNoteTitle, InputNoteDescription;
    private Button saveButton;
    private ImageSlider imageSlider;
    FloatingActionButton AddimageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        uris = new ArrayList<>();
        slides = new ArrayList<>();
        imageSlider = findViewById(R.id.select_note_image);
        AddimageButton = (FloatingActionButton) findViewById(R.id.add_new_image);
        saveButton = (Button) findViewById(R.id.save_note);
        InputNoteTitle = (EditText) findViewById(R.id.note_title);
        InputNoteDescription = (EditText) findViewById(R.id.note_description);

        imageSlider.setImageList(slides, ScaleTypes.CENTER_INSIDE);

        AddimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uris.size()>=10){
                    Toast.makeText(NewNoteActivity.this, "You can only add upto 10 images", Toast.LENGTH_SHORT).show();
                    return;
                }
                ImagePicker.with(NewNoteActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(InputNoteTitle.getText().toString()==null || InputNoteTitle.getText().toString().length()<5){
                    Toast.makeText(NewNoteActivity.this, "Title should be atlest 5 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(InputNoteDescription.getText().toString()==null || InputNoteDescription.getText().toString().length()<100){
                    Toast.makeText(NewNoteActivity.this, "Description should be atlest 100 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Note> notes = Paper.book().read(GlobalContext.loggedInUser.getId());
                if (notes == null) {
                    notes = new ArrayList<>();
                }

                Note note = new Note(UUID.randomUUID().toString(),
                        InputNoteTitle.getText().toString(),
                        InputNoteDescription.getText().toString(),
                        uris);
                notes.add(note);

                try {
                    Paper.book().write(GlobalContext.loggedInUser.getId(), notes);
                } catch (Exception e) {
                    System.out.println("caught exception " + e);
                }

                Toast.makeText(NewNoteActivity.this, "Note saved", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(NewNoteActivity.this, HomeActivity.class));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri =  data.getData();
        uris.add(uri.toString());
        SlideModel slideModel = new SlideModel(uri.toString(), ScaleTypes.CENTER_INSIDE);
        slides.add(slideModel);
        imageSlider.setImageList(slides, ScaleTypes.CENTER_INSIDE);
    }
}
