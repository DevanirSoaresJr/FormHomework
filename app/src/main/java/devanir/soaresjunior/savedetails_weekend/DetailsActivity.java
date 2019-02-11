package devanir.soaresjunior.savedetails_weekend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;




public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView tvSubmit = findViewById(R.id.tvSubmit);

        String age = getIntent().getStringExtra("submission");
        String name = readDataFromFile();
        String email = readFromSharedPreference();
        String info = "Name: " + name + "\n" + "Age: " + age + "\n" + "Email: " + email;
        tvSubmit.setText(info);


    }

    private String readDataFromFile() {
        File file = new File(getFilesDir(), "Submission.txt");
        int size = (int) file.length();
        byte[] contents = new byte[size];
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            fileInputStream.read(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(contents);

    }

    private String readFromSharedPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("submission", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("submission", "");
        return email;
    }
}



