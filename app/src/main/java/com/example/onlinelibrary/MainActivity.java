package com.example.onlinelibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText user2, pass2;
    TextView display2;
    Button submit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user2 = findViewById(R.id.user);
        pass2 = findViewById(R.id.pass);
        submit2 = findViewById(R.id.submit);
        submit2.setOnClickListener(new View.OnClickListener() {
            Integer counter =0;
            @Override
            public void onClick(View v) {
                if(counter > 1)  Toast.makeText(getApplicationContext(),"Failed Login Attempts",Toast.LENGTH_SHORT).show();
                else if ((user2.getText().toString().isEmpty())||(pass2.getText().toString().isEmpty())){
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                    counter++;
                }
                else if(!isValidPassword(pass2.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                    counter++;
                }
                else {
                    Intent Intent = new Intent(getApplicationContext(), userlist.class);
                    startActivity(Intent);
                    Toast.makeText(getApplicationContext(),"Successful Login",Toast.LENGTH_SHORT).show();
                }
            }
            public boolean isValidPassword(final String password) {

                Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
                Pattern lowerCasePatten = Pattern.compile("[a-z ]");
                Pattern digitCasePatten = Pattern.compile("[0-9 ]");

                boolean flag = true;
                if ((password.length() < 8))    flag = false;
                if (!specailCharPatten.matcher(password).find()) flag = false;
                if (!lowerCasePatten.matcher(password).find() && !UpperCasePatten.matcher(password).find()) flag = false;
                if (!digitCasePatten.matcher(password).find()) flag = false;
                return flag;
            }
        });

    }
}
//
//    Button login;
//    FirebaseAuth auth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        login = findViewById(R.id.submit);
//        auth = FirebaseAuth.getInstance();
//
//
//    }
//    @Override
//    protected  void onStart() {
//        super.onStart();
//        FirebaseUser user = auth.getCurrentUser();
//        if(user ==null)
//            startActivity(new Intent(MainActivity.this, loginActivity.class));
//
//
//    }
//}