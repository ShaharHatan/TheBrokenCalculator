package com.example.thebrokencalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calc extends AppCompatActivity {

    private Button[] BTNDigitList;
    //div=0 , mul=1 , sub=2 , add=3
    private Button[] operatorsList;
    private Button BTNEqual;

    private String[] valToCalc = new String[]{"", ""};
    private int currentVal = 0;
    private int lastOperatorClicked;

    private TextView textToShow;
    private TextView theFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        findView();
        initView();
    }

    private void initView() {
        for (int i = 0; i < BTNDigitList.length; i++) {
            int finalI = i;
            BTNDigitList[i].setOnClickListener(v -> {
                digitClicked(finalI);
            });
        }

        for (int i = 0; i < operatorsList.length; i++) {
            int finalI = i;
            operatorsList[i].setOnClickListener(v -> {
                operatorClicked(finalI);
            });
        }


        BTNEqual.setOnClickListener(v -> {
            calc();
        });

        theFlag.setVisibility(View.INVISIBLE);
    }

    private void operatorClicked(int finalI) {
        lastOperatorClicked = finalI;
        if (currentVal != 1)
            currentVal++;
    }

    private void digitClicked(int finalI) {
        valToCalc[currentVal] = valToCalc[currentVal].concat(BTNDigitList[finalI].getText().toString());
        textToShow.setText(valToCalc[currentVal]);
    }

    private void calc() {
        double results = 0;
        if (valToCalc[1].length() == 0 || valToCalc[0].length() == 0) {
            textToShow.setText("Invalid input!");
        } else if (lastOperatorClicked < 0) {
            textToShow.setText("you have to choose operator");
        } else {
            int val0 = Integer.parseInt(valToCalc[0]);
            int val1 = Integer.parseInt(valToCalc[1]);
            if (lastOperatorClicked == 0) {
                //div
                if (val1 == 0)
                    theFlag.setVisibility(View.VISIBLE);
                results = (double) val0 / val1;
            } else if (lastOperatorClicked == 1) {
                results = val0 * val1;
            } else if (lastOperatorClicked == 2) {
                results = val0 - val1;
            } else {
                results = val0 + val1;
            }
            textToShow.setText(val0 + "" + operatorsList[lastOperatorClicked].getText().toString() + "" + val1 + "=" + results);
        }
        lastOperatorClicked = -1;
        currentVal = 0;
        valToCalc[0] = "";
        valToCalc[1] = "";
    }

    private void findView() {
        BTNDigitList = new Button[]{
                findViewById(R.id.button0),
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9)
        };

        operatorsList = new Button[]{
                findViewById(R.id.buttonDiv),
                findViewById(R.id.buttonMul),
                findViewById(R.id.buttonSub),
                findViewById(R.id.buttonAdd)
        };

        BTNEqual = findViewById(R.id.buttonEqual);
        textToShow = findViewById(R.id.textToShow);
        theFlag = findViewById(R.id.theFlag);
    }
}