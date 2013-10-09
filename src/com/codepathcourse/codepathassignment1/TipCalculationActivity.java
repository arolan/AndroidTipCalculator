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
				int currentlySelectedTipButtonId = getSelectedTipButtonId();
				handleSpecificTipButtonPress(currentlySelectedTipButtonId);
			}
		});
        
        etTipAmount = (TextView) findViewById(R.id.etTipAmount);
        btnTip10 = (Button) findViewById(R.id.btn10PercentTip);
        btnTip15 = (Button) findViewById(R.id.btn15PercentTip);
        btnTip20 = (Button) findViewById(R.id.btn20PercentTip);
    }

    public int getSelectedTipButtonId()
    {
    	if(btnTip10.isPressed())
    	{
    		return R.id.btn10PercentTip;
    	}
    	else if(btnTip15.isPressed())
    	{
    		return R.id.btn15PercentTip;
    	}
    	else if(btnTip20.isPressed())
    	{
    		return R.id.btn20PercentTip;
    	}
    	else 
    	{
    		return 0;
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip_calculation, menu);
        return true;
    }
    
    public void tipButtonPressed(View v) {
    	this.handleSpecificTipButtonPress(v.getId());
    }
    
    public void handleSpecificTipButtonPress(int buttonId)
    {
    	String subTotalAmountStr = etSubTotalAmount.getText().toString();
    	double tipPercentage = 0;
    	switch (buttonId) {
    	case R.id.btn10PercentTip:
    		tipPercentage = 0.1;
    		btnTip10.setSelected(true);
    		btnTip15.setSelected(false);
    		btnTip20.setSelected(false);
    		break;
    	case R.id.btn15PercentTip:
    		tipPercentage = 0.15;
    		btnTip10.setSelected(false);
    		btnTip15.setSelected(true);
    		btnTip20.setSelected(false);
    		break;
    	case R.id.btn20PercentTip:
    		tipPercentage = 0.2;
    		btnTip10.setSelected(false);
    		btnTip15.setSelected(false);
    		btnTip20.setSelected(true);
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
