package com.S23010301.rusiru;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    EditText Usernametext, Passwordtext;
    Button loginbutton;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Usernametext = findViewById(R.id.Usernametext);
        Passwordtext = findViewById(R.id.Passwordtext);
        loginbutton = findViewById(R.id.loginbutton);

        dbHelper = new DatabaseHelper(this);

        loginbutton.setOnClickListener(view -> {
            String username = Usernametext.getText().toString().trim();
            String password = Passwordtext.getText().toString().trim();

            if (!username.isEmpty() && !password.isEmpty()) {
                dbHelper.insertUser(username, password);
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}