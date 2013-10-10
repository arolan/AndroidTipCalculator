package com.codepathcourse.codepathassignment1;

import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Locale;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculationActivity extends Activity {

	public EditText etSubTotalAmount;
	public TextView etTipAmount;
	public Button btnTip10;
	public Button btnTip15;
	public Button btnTip20;
	public int currentlySelectedButtonId;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculation);
        etSubTotalAmount = (EditText) findViewById(R.id.etSubTotalAmount);
        etSubTotalAmount.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				handleSpecificTipButtonPress(currentlySelectedButtonId);
			}
		});
        
        etTipAmount = (TextView) findViewById(R.id.etTipAmount);
        btnTip10 = (Button) findViewById(R.id.btn10PercentTip);
        btnTip15 = (Button) findViewById(R.id.btn15PercentTip);
        btnTip20 = (Button) findViewById(R.id.btn20PercentTip);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculation, menu);
        return true;
    }
    
    public void tipButtonPressed(View v) {
    	currentlySelectedButtonId = v.getId();
    	this.handleSpecificTipButtonPress(v.getId());
    }
    
    public void handleSpecificTipButtonPress(int buttonId)
    {
    	String subTotalAmountStr = etSubTotalAmount.getText().toString();
    	double tipPercentage = 0;
    	switch (buttonId) {
    	case R.id.btn10PercentTip:
    		tipPercentage = 0.1;    		
    		break;
    	case R.id.btn15PercentTip:
    		tipPercentage = 0.15;
    		break;
    	case R.id.btn20PercentTip:
    		tipPercentage = 0.2;
    		break;
    	default:
    		break;
    	}
    	
    	
    	
    	if(!subTotalAmountStr.isEmpty()) {
    		double tipAmount = tipPercentage * Integer.valueOf(subTotalAmountStr);
    		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.getDefault());
    		formatter.setMaximumFractionDigits(2);
    		etTipAmount.setText("Tip is: "+ formatter.format(tipAmount));
    	}
    }
}
