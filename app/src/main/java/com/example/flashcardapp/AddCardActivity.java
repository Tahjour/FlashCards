package com.example.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card_question);

        // Here, we are getting the data sent by pressing the edit icon on the MainActivity.
        // This code here is only relevant if the edit icon was pressed, since the keys in the getStringExtra method were created in the OnClickListener of the edit Icon.
        //1. We get the data and store them in strings.
        String currentQuestionString = getIntent().getStringExtra("currentQuestionString");
        String currentCorrectAnswerString = getIntent().getStringExtra("currentCorrectAnswerString");
        String currentWrongAnswerChoiceOneString = getIntent().getStringExtra("currentWrongAnswerChoiceOneString");
        String currentWrongAnswerChoiceTwoString = getIntent().getStringExtra("currentWrongAnswerChoiceTwoString");

        //2. We target the EditText elements and give them variables.
        EditText questionEditText = findViewById(R.id.newQuestionEditText);
        EditText correctAnswerEditText = findViewById(R.id.newCorrectAnswerEditText);
        EditText wrongAnswerChoiceOneEditText = findViewById(R.id.newWrongAnswerChoiceOneEditText);
        EditText wrongAnswerChoiceTwoEditText = findViewById(R.id.newWrongAnswerChoiceTwoEditText);

        //3. We use the variables to change the text of the EditText's to the text on the cards from MainActivity.
        questionEditText.setText(currentQuestionString);
        correctAnswerEditText.setText(currentCorrectAnswerString);
        wrongAnswerChoiceOneEditText.setText(currentWrongAnswerChoiceOneString);
        wrongAnswerChoiceTwoEditText.setText(currentWrongAnswerChoiceTwoString);

        findViewById(R.id.closeNewActivityIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // TODO: 3/6/2021 Use the saving button to save the entered data and update the flash card with that data.
        findViewById(R.id.saveActivityIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get text from EditText element for new questions

                String newQuestionString = questionEditText.getText().toString();
                String newCorrectAnswerString = correctAnswerEditText.getText().toString();
                String wrongAnswerOneString = wrongAnswerChoiceOneEditText.getText().toString();
                String wrongAnswerTwoString = wrongAnswerChoiceTwoEditText.getText().toString();

                // Checking if there is no text entered and displaying an error as a toast to the user.
                // TODO: 3/7/2021 Replace this approach with a toast instead of changing the hint 
                if (newQuestionString.equals("") || newCorrectAnswerString.equals("")||wrongAnswerOneString.equals("")||wrongAnswerTwoString.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Must enter data above...",Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,250);
                    toast.show();
                } else {
                    // Create new intent and specify data to be sent over to the MainActivity
                    Intent data = new Intent();
                    data.putExtra("newQuestionString", newQuestionString);
                    data.putExtra("newCorrectAnswerString", newCorrectAnswerString);
                    data.putExtra("wrongAnswerOneString", wrongAnswerOneString);
                    data.putExtra("wrongAnswerTwoString", wrongAnswerTwoString);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }
}