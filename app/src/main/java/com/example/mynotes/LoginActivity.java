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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private Button login;
    private EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login_btn);
        email = findViewById(R.id.login_phone_number_input);
        password = findViewById(R.id.login_password_input);

        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                if(email.getText().toString()==null){
                    Toast.makeText(LoginActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.getText().toString()==null){
                    Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Paper.book().contains("users")) {
                    List<User> users = Paper.book().read("users");
                    String encryptedPassword = Base64.getEncoder().encodeToString(password.getText().toString().getBytes(StandardCharsets.UTF_8));
                    int flag=0;
                    for(int i=0;i<users.size();i++){
                        if(users.get(i).getEmail().equals(email.getText().toString()) || users.get(i).getMobile().equals(email.getText().toString())){
                            flag=1;
                            if(users.get(i).getPassword().equals(encryptedPassword)){
                                GlobalContext.loggedInUser=users.get(i);
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    if(flag==0){
                        Toast.makeText(LoginActivity.this, "User not found. Please register first", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Please register first", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
