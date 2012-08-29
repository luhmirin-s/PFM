package main.pfmandroid;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class TimeframeListener implements OnItemSelectedListener {
	private int pos;
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		this.pos = pos;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public int getPos(){
		return pos;
	}
}
