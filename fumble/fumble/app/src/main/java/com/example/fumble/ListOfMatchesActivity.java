package com.example.fumble;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListOfMatchesActivity extends AppCompatActivity {

    private ListView matchesListView;
    private ArrayAdapter<String> adapter;
    private Button buttonCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_matches);

        matchesListView = findViewById(R.id.matchesListView);
        buttonCancel = findViewById(R.id.buttonCancel);

        ArrayList<String> matchDetailsStringList = new ArrayList<>();
        for (PostMatchActivity.MatchDetails details : PostMatchActivity.matchDetailsList) {
            matchDetailsStringList.add(details.toString());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, matchDetailsStringList);
        matchesListView.setAdapter(adapter);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go back to the previous one
                finish();
            }
        });
    }
}
