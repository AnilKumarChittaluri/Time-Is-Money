package uk.co.gavd.timeismoney;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class TimeIsMoneyActivity extends Activity {
	
	private TextView textOutput;
	private Button btnAction;
	private CostTimer counter;
	private SeekBar seekBar;

	// Create an anonymous implementation of OnClickListener
	private OnClickListener startListener = new OnClickListener() {
		public void onClick(View v) {

	        int pencePerHour = 115 * 100;
	        int people = 4;
	        
	    	counter.setPencePerHour(pencePerHour);
	    	counter.setPeople(people);
	    	
			Log.println(Log.INFO, "BOX", "Counter started for " + counter.getPeople() + " people at " + counter.getPencePerHour() + "p an hour!");
			
			counter.start();
			btnAction.setText("Stop");
			btnAction.setOnClickListener(stopListener);
		}
	};
	
	private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			Log.println(Log.INFO, "BOX", "Seek to " + seekBar.getProgress());
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
	};
	
	// Create an anonymous implementation of OnClickListener
	private OnClickListener stopListener = new OnClickListener() {
		public void onClick(View v) {
			Log.println(Log.INFO, "BOX", "Counter stopped!");
			counter.reset();
			btnAction.setText("Start");
			btnAction.setOnClickListener(startListener);
			// TODO submit to Vince
		}
	};
	
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnAction = (Button) findViewById(R.id.btnAction);
        btnAction.setOnClickListener(startListener);
        
        textOutput = (TextView) findViewById(R.id.textOutput);
        textOutput.setText("tick: " );
        
        seekBar = (SeekBar) findViewById(R.id.peopleCount);
        
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        
    	counter = new CostTimer(this);
    }
    
    public void reportCost(double costInPounds) {
    	textOutput.setText("£" + Math.round(costInPounds));
    }

}