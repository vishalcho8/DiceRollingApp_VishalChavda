package com.vishal.dicerollingapp_vishalchavda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find and store required fields from the layout
        Button rollDiceOnce = findViewById(R.id.btnROllOnce);
        Button rollDiceTwice = findViewById(R.id.btnROllTwice);
        TextView secondResult = findViewById(R.id.tvDiceTwoResult);

        //Hide the second text view at start. will be visible only when "Roll Twice" button clicked
        secondResult.setVisibility(View.INVISIBLE);

        //Passing current instance to the onclick listener based on the button clicked.
        rollDiceOnce.setOnClickListener(this);
        rollDiceTwice.setOnClickListener(this);
    }

    public void onClick(View view) {
        //depending on which Button is clicked, roll the dice either once or twice and show the result.

        RadioGroup chooseNumOfDiceSides = findViewById(R.id.numDiceGroup);
        TextView diceOneResult = findViewById(R.id.tvDiceOneResult);
        TextView diceTwoResult = findViewById(R.id.tvDiceTwoResult);

        //Initialize score variables to zero
        int numOfDiceSide = 0;
        int resultOne = 0;
        int resultTwo = 0;

        //Get the current score of the team red and blue
        String diceOneVal = String.valueOf(diceOneResult.getText());
        String diceTwoVal = String.valueOf(diceTwoResult.getText());

        /* Based on the radio button selection store the sides
            in the variable numOfDiceSide. */

        switch(chooseNumOfDiceSides.getCheckedRadioButtonId()){
            case R.id.D_Side_Four: //if(checkedButton == R.id.D_Side_Four)
                numOfDiceSide = 4;
                break;
            case R.id.D_Side_Six: //else if(checkedButton == R.id.D_Side_Six)
                numOfDiceSide = 6;
                break;
            case R.id.D_Side_Eight: //else if(checkedButton == R.id.D_Side_Eight)
                numOfDiceSide = 8;
                break;
            case R.id.D_Side_Ten: //else if(checkedButton == R.id.D_Side_Ten)
                numOfDiceSide = 10;
                break;
            case R.id.D_Side_Twelve: //else if(checkedButton == R.id.D_Side_Twelve)
                numOfDiceSide = 12;
                break;
            default: // else
                Log.i("Wrong Choice", "Number of SIdes does not exist.");
                break;
        }

        //If Roll Once button  is clicked roll the die once and set the value of Dice one result
        //Else Roll the dice twice and set the value of DIce one and two results.

        if (view.getId() == R.id.btnROllOnce) {
            resultOne = roll(numOfDiceSide);
            diceOneResult.setText(String.valueOf(resultOne));
            diceTwoResult.setVisibility(View.INVISIBLE);
            diceTwoResult.setText(String.valueOf(resultTwo));
        } else{
            diceTwoResult.setVisibility(View.VISIBLE);
            resultOne = roll(numOfDiceSide);
            diceOneResult.setText(String.valueOf(resultOne));
            resultTwo = roll(numOfDiceSide);
            diceTwoResult.setText(String.valueOf(resultTwo));
        }

        //Reset the variables after done.
        resultOne = 0;
        resultTwo = 0;

    }

    //Method to generate random number based on number of sides selected.
    public int roll(int numberOfSides){
        int numFrom = 1;
        int numTo = numberOfSides;
        int range = numberOfSides - numFrom + 1;        // +1 includes the the entire rage between the given number including the max number.
        return ((int) (Math.random()*(range))) + numFrom; //Formula to generate number between the range of certain numbers.
    }
}