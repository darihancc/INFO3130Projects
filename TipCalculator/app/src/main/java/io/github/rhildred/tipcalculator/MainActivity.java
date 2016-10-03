package io.github.rhildred.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity
implements OnEditorActionListener, View.OnClickListener {
    private View screen;
    private EditText billAmountEditText;
    private TextView tipTextView;
    private TextView totalTextView;
    private TextView tipPercentTextView;
    private Button percentUpButton, percentDownButton;
    private float tipPercent = .15f;
    private final String TAG="Tip Calculator Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(android.R.id.content);
        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        tipPercentTextView = (TextView) findViewById(R.id.percentTextView);
        percentUpButton = (Button) findViewById(R.id.percentUpButton);
        percentDownButton = (Button) findViewById(R.id.percentDownButton);
        billAmountEditText.setOnEditorActionListener(this);
        percentUpButton.setOnClickListener(this);
        percentDownButton.setOnClickListener(this);
        screen.setOnClickListener(this);
        calculateAndDisplay();
    }
    @Override
    public boolean onEditorAction(TextView v, int ActionId, KeyEvent event){
        calculateAndDisplay();
        return true;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.percentDownButton:
                tipPercent -= .01f;
                calculateAndDisplay();
                break;
            case R.id.percentUpButton:
                tipPercent += .01f;
                calculateAndDisplay();
                break;
        }
    }

    public void calculateAndDisplay(){
        Log.d(TAG, "Entering Calculate and Display");

        String billAmountString = billAmountEditText.getText().toString();
        float billAmount = 0;
        try{
            billAmount = Float.parseFloat(billAmountString);
        }
        catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            //Toast t = Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT);
            //t.show();
            e.printStackTrace();
        }
        //calculate
        Log.d(TAG, "Bill Amount = " + billAmountString);
        float tipAmount = tipPercent * billAmount;
        float totalAmount = billAmount + tipAmount;
        //display
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));
        NumberFormat percent = NumberFormat.getPercentInstance();
        tipPercentTextView.setText(percent.format(tipPercent));
    }
}
