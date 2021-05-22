package com.example.basiccalc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    private EditText display;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#007aff"));

        // Set BackgroundDrawable
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);

        display=findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }
    private void updateText(String strToAdd){
        String oldStr= display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftStr=oldStr.substring(0,cursorPos);
        String rightStr=oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }
    public void zeroBTN(View v){
        updateText("0");
    }
    public void oneBTN(View v){
        updateText("1");

    }
    public void twoBTN(View v){
        updateText("2");

    }
    public void threeBTN(View v){
        updateText("3");

    }
    public void fourBTN(View v){
        updateText("4");

    }
    public void fiveBTN(View v){
        updateText("5");

    }
    public void sixBTN(View v){
        updateText("6");

    }
    public void sevenBTN(View v){
        updateText("7");

    }
    public void eightBTN(View v){
        updateText("8");

    }
    public void nineBTN(View v){
        updateText("9");

    }
    public void plusMinusBTN(View v){
        updateText("-");

    }
    public void pointBTN(View v){
        updateText(".");

    }

    public void addBTN(View v){
        updateText("+");

    }
    public void subtractBTN(View v){
        updateText("-");

    }
    public void multiplyBTN(View v){
        updateText("×");

    }
    public void divideBTN(View v){
        updateText("÷");

    }
    public void exponentBTN(View v){
        updateText("^");

    }
    public void parenthesesBTN(View v){
        int cursorPos=display.getSelectionStart();
        int openPar=0;
        int closePar=0;
        int textLen=display.getText().length();
        for(int i=0;i<cursorPos;i++){
            if(display.getText().toString().charAt(i) == '('){
                openPar+=1;
            }
            if(display.getText().toString().charAt(i) == ')'){
                closePar+=1;
            }
        }
        if(openPar==closePar || display.getText().toString().charAt(textLen - 1) == '('){
            updateText("(");
            display.setSelection(cursorPos+1);
        }
        else if(closePar<openPar && display.getText().toString().charAt(textLen - 1) != '('){
            updateText(")");
            display.setSelection(cursorPos+1);
        }

    }
    public void clearBTN(View v){
        display.setText("");

    }
    public void backspaceBTN(View v){
        int cursorPos=display.getSelectionStart();
        int textLen= display.getText().length();
        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }

    }
    public void equalsBTN(View v){
        String userExp= display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×","*");

        Expression exp = new Expression(userExp);

        String result= String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());

    }
}