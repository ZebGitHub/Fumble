package com.example.fumble;

import android.os.Bundle;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;



import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;


public class PostMatchActivity extends AppCompatActivity {

    private Spinner venueLocationSpinner;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText playersJoinedET;
    private EditText playersNeededET;
    private Button buttonPost;
    private Button buttonCancel;

    private String datePickerText;
    private String timePickerText;


    public static List<MatchDetails> matchDetailsList = new ArrayList<>();

    public class MatchDetails {
        private String venueLocation;
        private String matchDateTime;
        private String playersJoined;
        private String playersNeeded;

        public MatchDetails(String venueLocation, String matchDateTime, String playersJoined, String playersNeeded) {
            this.venueLocation = venueLocation;
            this.matchDateTime = matchDateTime;
            this.playersJoined = playersJoined;
            this.playersNeeded = playersNeeded;
        }

        @Override
        public String toString() {
            return "Venue: " + venueLocation +
                    "\nDate and Time: " + matchDateTime +
                    "\nPlayers Joined: " + playersJoined +
                    "\nPlayers Needed: " + playersNeeded;
        }



    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_match);

        venueLocationSpinner = (Spinner) findViewById(R.id.venueLocationSpinner);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        playersJoinedET = (EditText) findViewById(R.id.playersJoinedET);
        playersNeededET = (EditText) findViewById(R.id.playersNeededET);
        buttonPost = (Button) findViewById(R.id.buttonPost);
        buttonCancel = (Button) findViewById(R.id.buttonCancel);

        String[] venueLocations = {"Marie Reed Soccer Field", "Shaw Field (Georgetown)",
                "GW Mount Vernon Field", "Bundy Field", "Jellef Recreation Center"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, venueLocations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        venueLocationSpinner.setAdapter(adapter);


        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the text from EditText widgets
                String venueLocationText = venueLocationSpinner.getSelectedItem().toString();

                // Retrieve selected date from DatePicker
                int year = datePicker.getYear();
                int month = datePicker.getMonth() + 1; // DatePicker months are 0-indexed
                int day = datePicker.getDayOfMonth();
                String datePickerText = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month, day);

                // Retrieve selected time from TimePicker
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String timePickerText = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);

                // Combine date and time into matchDateTimeText
                String matchDateTimeText = datePickerText + " " + timePickerText;
                String playersJoinedText = playersJoinedET.getText().toString();
                String playersNeededText = playersNeededET.getText().toString();


                // Validate that values have been selected/entered
                if (venueLocationText.isEmpty()) {
                    // Show an error message, e.g., with a Toast
                    showToast("Please select a venue location");
                    return;
                }

                if (datePickerText.isEmpty() || timePickerText.isEmpty()) {
                    showToast("Please select both date and time");
                    return;
                }

                if (playersJoinedText.isEmpty() || playersNeededText.isEmpty()) {
                    showToast("Please enter the number of players joined and needed");
                    return;
                }

                MatchDetails matchDetails = new MatchDetails(
                        venueLocationText, matchDateTimeText, playersJoinedText, playersNeededText);

                matchDetailsList.add(matchDetails);

                Intent intent = new Intent(PostMatchActivity.this, HomePageActivity.class);
                startActivity(intent);

                // Show a toast indicating that the match has been posted
                Toast.makeText(PostMatchActivity.this, "Match Posted!", Toast.LENGTH_SHORT).show();


            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Finish the current activity to go back to the previous one
                finish();
            }
        });
    }

}