package devanir.soaresjunior.savedetails_weekend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Declaring Views
    Button btnSubmit;
    TextView tvName;
    TextView tvAge;
    TextView tvEmail;
    EditText etName;
    EditText etAge;
    EditText etEmail;
    TextView tvSubmit;

    //Creating ArrayList
   // private List<String> submission = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Binding Views
        btnSubmit = findViewById(R.id.btnSubmit);
        tvName = findViewById(R.id.tvName);
        tvAge = findViewById(R.id.tvAge);
        tvEmail = findViewById(R.id.tvEmail);
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etEmail = findViewById(R.id.etEmail);
        tvSubmit = findViewById(R.id.tvSubmit);


//Setting Click Listener to btnSubmit
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = etAge.getText().toString();
                //Getting Shared preferences, which works with key value pairs.
                SharedPreferences sharedPreferences = getSharedPreferences("submission", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("submission", etEmail.getText().toString());
                editor.apply();
                Toast.makeText(MainActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                writeNameToFile();

                Intent intent = new Intent
                        (MainActivity.this, DetailsActivity.class);
                intent.putExtra("submission",
                        age);
                startActivity(intent);

                //creating a String to store the values of the Edittexts
                //String sub = (etName.getText().toString() + "\n" + etAge.getText().toString() + "\n" + etEmail.getText().toString());
                //adding the string to the array list
                //submission.add(sub);
                //array list is now bigger then 0 there's more than one position so, a method to write this logs to file with execute
                // if (submission.size()>0){
                //   writeLogsToFile();

            }

            private void writeNameToFile() {
                File file = new File(getFilesDir(), "Submission.txt");
                try (FileOutputStream fileOutputStream = openFileOutput("Submission.txt", Context.MODE_PRIVATE)) {

                    fileOutputStream.write(etName.getText().toString().getBytes());

                } catch (IOException ioxception) {
                    Toast.makeText(MainActivity.this, "File Not Found", Toast.LENGTH_SHORT).show();
                    ioxception.printStackTrace();

                }
            }
        });
    }
}

