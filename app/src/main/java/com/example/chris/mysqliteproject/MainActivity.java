package com.example.chris.mysqliteproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button addButton;
    Button removeButton;
    EditText inputEditText;
    TextView resultsTextView;
    MyDBHandler dbHandler;
    Button secondActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addButton = (Button) findViewById(R.id.addButton);
        removeButton = (Button) findViewById(R.id.removeButton);
        inputEditText = (EditText) findViewById(R.id.writeToFileEditText);
        resultsTextView = (TextView) findViewById(R.id.resultsTextView);
        dbHandler = new MyDBHandler(this, null, null, 1);
        secondActivityButton = (Button) findViewById(R.id.secondActivityButton);
        printDatabase();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = inputEditText.getText().toString();
                if ("".equals(currentText)){
                    Toast.makeText(getApplicationContext(), "Cannot enter empty string!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Entry e = new Entry (currentText);

                //resultsTextView.setText(e.get_name());

                dbHandler.addEntry(e);
                printDatabase();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentText = inputEditText.getText().toString();
                dbHandler.deleteEntry(currentText);
                printDatabase();
            }
        });

        secondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), SecondActivity.class);
                startIntent.putExtra("com.example.chris.mysqliteproject.INFO", "This information was passed from the first activity.");
                startActivity(startIntent);
            }
        });

    } // end of onCreate



    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        resultsTextView.setText(dbString);
        inputEditText.setText("");
    }

}