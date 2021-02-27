package com.example.flashcardapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView questionTextView = findViewById(R.id.question);
        TextView answerTextView = findViewById(R.id.answer);
        answerTextView.setVisibility(View.INVISIBLE);

        // The Question and Answer flash cards
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);
            }
        });

        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answerTextView.setVisibility(View.INVISIBLE);
                questionTextView.setVisibility(View.VISIBLE);
            }
        });

        // The multiple choice
        TextView firstChoice = findViewById(R.id.firstChoice);
        TextView secondChoice = findViewById(R.id.secondChoice);
        TextView thirdChoice = findViewById(R.id.thirdChoice);
        String answerText = answerTextView.getText().toString();

        firstChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstChoiceText = firstChoice.getText().toString();
                if (firstChoiceText.equals(answerText)){
                    firstChoice.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                } else {
                    firstChoice.setBackgroundColor(getResources().getColor(R.color.pomegranateRed,null));
                    secondChoice.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                }
            }
        });
        secondChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String secondChoiceText = secondChoice.getText().toString();
                if (secondChoiceText.equals(answerText)){
                    secondChoice.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                } else {
                    secondChoice.setBackgroundColor(getResources().getColor(R.color.pomegranateRed,null));
                }
            }
        });
        thirdChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thirdChoiceText = thirdChoice.getText().toString();
                if (thirdChoiceText.equals(answerText)){
                    thirdChoice.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                } else {
                    thirdChoice.setBackgroundColor(getResources().getColor(R.color.pomegranateRed,null));
                    secondChoice.setBackgroundColor(getResources().getColor(R.color.emeraldGreen,null));
                }
            }
        });

        // The image
        ImageView visibilityImage = findViewById(R.id.visibilityChanger);
        final boolean[] choicesAreHidden = {false};
        visibilityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make all choices invisible
                if (!choicesAreHidden[0]) {
                    firstChoice.setVisibility(View.INVISIBLE);
                    secondChoice.setVisibility(View.INVISIBLE);
                    thirdChoice.setVisibility(View.INVISIBLE);

                    // Change the image to one that indicates all choices are blocked (Slashed Eye)
                    visibilityImage.setImageResource(R.drawable.ic_slashed_eye);
                    choicesAreHidden[0] = true;
                } else if (choicesAreHidden[0]) {
                    firstChoice.setVisibility(View.VISIBLE);
                    secondChoice.setVisibility(View.VISIBLE);
                    thirdChoice.setVisibility(View.VISIBLE);

                    // Change the image to one that indicates all choices are blocked (Slashed Eye)
                    visibilityImage.setImageResource(R.drawable.ic_eye);
                    choicesAreHidden[0] = false;
                }
            }
        });

        findViewById(R.id.backgroundSelector).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstChoice.setBackgroundColor(getResources().getColor(R.color.wisteriaPurple,null));
                secondChoice.setBackgroundColor(getResources().getColor(R.color.wisteriaPurple,null));
                thirdChoice.setBackgroundColor(getResources().getColor(R.color.wisteriaPurple, null));
            }
        });
    }
}