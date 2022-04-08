package com.example.mynotes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynotes.context.GlobalContext;
import com.example.mynotes.model.User;
import com.example.mynotes.utils.InputValidator;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import io.paperdb.Paper;

public class RegisterActivity extends AppCompatActivity{
    private Button signup;
    private EditText name,mobile,email,password;
    private InputValidator inputValidator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputValidator = new InputValidator();
        signup = findViewById(R.id.register_btn);

        name=findViewById(R.id.register_username_input);
        mobile=findViewById(R.id.register_phone_number_input);
        email=findViewById(R.id.register_email_id_input);
        password=findViewById(R.id.register_password_input);

        signup.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                if(name.getText().toString()==null){
                    Toast.makeText(RegisterActivity.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email.getText().toString()==null){
                    Toast.makeText(RegisterActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mobile.getText().toString()==null){
                    Toast.makeText(RegisterActivity.this, "Please enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.getText().toString()==null) {
                    Toast.makeText(RegisterActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!inputValidator.isValidEmail(email.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this, "Invalid Email. Should follow email convention and between 4 and 25 characters long", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!inputValidator.isValidMobile(mobile.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this, "Invalid Phone Number. Should ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!inputValidator.isValidPassword(password.getText().toString(), name.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Invalid password. Min 8 and max 15 characters. Should not contain your name, the first character\n" +
                            "should be lowercase, must contain at least 2 uppercase characters, 2 digits and 1 special character", Toast.LENGTH_LONG).show();
                    return;
                }

                List<User> users=new ArrayList<>();
                if(Paper.book().contains("users")) {
                    users = Paper.book().read("users");
                }
                User user = new User(UUID.randomUUID().toString(),
                        name.getText().toString(),
                        mobile.getText().toString(),
                        email.getText().toString(),
                        Base64.getEncoder().encodeToString(password.getText().toString().getBytes(StandardCharsets.UTF_8)));
                GlobalContext.loggedInUser=user;
                users.add(user);
                Paper.book().write("users",users);

                Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }


}
