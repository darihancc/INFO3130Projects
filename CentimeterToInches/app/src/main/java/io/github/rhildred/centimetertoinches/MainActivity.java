package io.github.rhildred.centimetertoinches;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText oTextInches;
    private EditText oTextCentimeters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oTextInches = (EditText)findViewById(R.id.inches);
        oTextInches.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int ActionId, KeyEvent event){
                Float nCentimeters = 0f;
                try{
                    nCentimeters = Float.parseFloat(oTextInches.getText().toString()) * 2.54f;
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                oTextCentimeters.setText(nCentimeters.toString());
                return true;
            }

        });
        oTextCentimeters = (EditText)findViewById(R.id.centimeters);
        oTextCentimeters.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int ActionId, KeyEvent event){
                Float nInches = 0f;
                try{
                    nInches = Float.parseFloat(oTextCentimeters.getText().toString())/ 2.54f;
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                oTextInches.setText(nInches.toString());
                return true;
            }

        });

    }
}
