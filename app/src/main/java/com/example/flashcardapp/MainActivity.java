package com.example.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the question and answer TextViews and set the answer TextView to invisible on app launch.
        TextView flashCardQuestionTextView = findViewById(R.id.mainFlashCardQuestionTextView);
        TextView flashCardAnswerTextView = findViewById(R.id.mainFlashCardAnswerTextView);
        
        flashCardAnswerTextView.setVisibility(View.INVISIBLE);

// The Question and Answer flash cards Section
        flashCardQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the question card to invisible and set the answer card to visible when clicked
                flashCardQuestionTextView.setVisibility(View.INVISIBLE);
                flashCardAnswerTextView.setVisibility(View.VISIBLE);
            }
        });

        flashCardAnswerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set the answer card to invisible and set the question card to visible when clicked
                flashCardAnswerTextView.setVisibility(View.INVISIBLE);
                flashCardQuestionTextView.setVisibility(View.VISIBLE);
            }
        });

// The multiple choice section
        // Getting the TextViews of each choice into variables
        TextView correctAnswerChoiceTextView = findViewById(R.id.correctAnswerTextView);
        TextView wrongAnswerChoiceOneTextView = findViewById(R.id.wrongAnswerOneTextView);
        TextView wrongAnswerChoiceTwoTextView = findViewById(R.id.wrongAnswerTwoTextView);

        // Get the text displayed in the answer TextView card
        String answerText = flashCardAnswerTextView.getText().toString();

        // TODO: 3/8/2021 Refactor how these 3 choices change color because the current way is a bir hard-coded
        wrongAnswerChoiceOneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstChoiceText = wrongAnswerChoiceOneTextView.getText().toString();
                if (firstChoiceText.equals(answerText)){
                    wrongAnswerChoiceOneTextView.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                } else {
                    wrongAnswerChoiceOneTextView.setBackgroundColor(getResources().getColor(R.color.pomegranateRed,null));
                    correctAnswerChoiceTextView.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                }
            }
        });
        correctAnswerChoiceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secondChoiceText = correctAnswerChoiceTextView.getText().toString();
                if (secondChoiceText.equals(answerText)){
                    correctAnswerChoiceTextView.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                } else {
                    correctAnswerChoiceTextView.setBackgroundColor(getResources().getColor(R.color.pomegranateRed,null));
                }
            }
        });
        wrongAnswerChoiceTwoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thirdChoiceText = wrongAnswerChoiceTwoTextView.getText().toString();
                if (thirdChoiceText.equals(answerText)){
                    wrongAnswerChoiceTwoTextView.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                } else {
                    wrongAnswerChoiceTwoTextView.setBackgroundColor(getResources().getColor(R.color.pomegranateRed,null));
                    correctAnswerChoiceTextView.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                }
            }
        });

        // The hide or show icon
        ImageView visibilityImage = findViewById(R.id.choiceVisibilityChangerIcon);
        final boolean[] choicesAreHidden = {false};
        visibilityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make all choices invisible
                if (!choicesAreHidden[0]) {
                    wrongAnswerChoiceOneTextView.setVisibility(View.INVISIBLE);
                    correctAnswerChoiceTextView.setVisibility(View.INVISIBLE);
                    wrongAnswerChoiceTwoTextView.setVisibility(View.INVISIBLE);

                    // Change the image to one that indicates all choices are blocked (Slashed Eye)
                    visibilityImage.setImageResource(R.drawable.ic_slashed_eye);
                    choicesAreHidden[0] = true;
                } else if (choicesAreHidden[0]) {
                    wrongAnswerChoiceOneTextView.setVisibility(View.VISIBLE);
                    correctAnswerChoiceTextView.setVisibility(View.VISIBLE);
                    wrongAnswerChoiceTwoTextView.setVisibility(View.VISIBLE);

                    // Change the image to one that indicates all choices are shown (Normal Eye)
                    visibilityImage.setImageResource(R.drawable.ic_eye);
                    choicesAreHidden[0] = false;
                }
            }
        });

        // Sets all colors to default when the background of the screen is clicked
        findViewById(R.id.backgroundSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongAnswerChoiceOneTextView.setBackgroundColor(getResources().getColor(R.color.wisteriaPurple,null));
                correctAnswerChoiceTextView.setBackgroundColor(getResources().getColor(R.color.wisteriaPurple,null));
                wrongAnswerChoiceTwoTextView.setBackgroundColor(getResources().getColor(R.color.wisteriaPurple, null));
            }
        });

        // The add a new flashcard question icon
        findViewById(R.id.addCardQuestionsIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates a new intent and starts an activity with the expectation of a result being returned from the started activity.
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent,100);
            }
        });

        findViewById(R.id.editCurrentCardsIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentQuestionString = flashCardQuestionTextView.getText().toString();
                String currentCorrectAnswerString = flashCardAnswerTextView.getText().toString();
                String currentWrongAnswerChoiceOneString = wrongAnswerChoiceOneTextView.getText().toString();
                String currentWrongAnswerChoiceTwoString = wrongAnswerChoiceTwoTextView.getText().toString();
                
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("currentQuestionString", currentQuestionString);
                intent.putExtra("currentCorrectAnswerString", currentCorrectAnswerString);
                intent.putExtra("currentWrongAnswerChoiceOneString", currentWrongAnswerChoiceOneString);
                intent.putExtra("currentWrongAnswerChoiceTwoString",currentWrongAnswerChoiceTwoString);
                MainActivity.this.startActivityForResult(intent, 101);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Basic error checking and activity authentication is done in the condition of this if statement.
        // We check if the request code is equal to the one assigned when the add card icon is clicked
        // We also make sure to run the body of the if statement if the data returned is not null, so we avoid a crash.
        if ((requestCode == 100 || requestCode == 101)  && data != null) {
            // We get the text for the new question and for the new correct answer for the question
            String newQuestionString = data.getExtras().getString("newQuestionString");
            String newCorrectAnswerString = data.getExtras().getString("newCorrectAnswerString");
            String wrongAnswerOneString = data.getExtras().getString("wrongAnswerOneString");
            String wrongAnswerTwoString = data.getExtras().getString("wrongAnswerTwoString");

            // We target the original question and answer TextViews and set the text to the new ones we received above.
            ((TextView)findViewById(R.id.mainFlashCardQuestionTextView)).setText(newQuestionString);
            ((TextView)findViewById(R.id.mainFlashCardAnswerTextView)).setText(newCorrectAnswerString);
            ((TextView)findViewById(R.id.correctAnswerTextView)).setText(newCorrectAnswerString);
            ((TextView)findViewById(R.id.wrongAnswerOneTextView)).setText(wrongAnswerOneString);
            ((TextView)findViewById(R.id.wrongAnswerTwoTextView)).setText(wrongAnswerTwoString);

            Snackbar.make(findViewById(R.id.mainFlashCardQuestionTextView),"Updated...", Snackbar.LENGTH_SHORT).show();
            // TODO: 3/7/2021 Update the rest of the choices accordingly 
        }
    }
}