package main.pfmandroid;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SourceListener implements OnItemSelectedListener{
	
	private Source source;
	private int pos;
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		
		source = (Source) parent.getItemAtPosition(pos);
		this.pos = pos;
    }

	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }
	
	public Source returnObject(){
		return source;
	}
	
	public int returnPosition(){
		return pos;
	}
}
