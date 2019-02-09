package devanir.soaresjunior.savedetails_weekend;

import android.content.Intent;
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

        TextView tvAgeIntent = findViewById(R.id.tvAgeIntent);
        Intent intent = getIntent();
        String age = intent.getStringExtra("Age");
        tvAgeIntent.setText(age);

        TextView tvLogs = findViewById(R.id.tvSubmit);

        ArrayList<String> logs =
                getIntent().getStringArrayListExtra("submission");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <logs.size(); i++){
            stringBuilder.append(logs.get(i));
            stringBuilder.append("\n");

        }

        tvLogs.setText(readDataFromFile());
    }
    private String readDataFromFile(){
        File file = new File(getFilesDir(), "Submission.txt");
        int size = (int) file.length();
        byte[] contents = new byte[size];
        try (FileInputStream fileInputStream = new FileInputStream(file)){
            fileInputStream.read(contents);
        } catch (IOException e){
            e.printStackTrace();
        }
        return new String(contents);

    }

    }



