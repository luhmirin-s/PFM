package main.pfmandroid;

import main.pfmandroid.data.Source;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class SourceListener implements OnItemSelectedListener{
	
	private Source source;
	private int id;
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		
		source = (Source) parent.getItemAtPosition(pos);
		this.id = source.getId();
		Log.d("Source", String.valueOf(this.id));
    }

	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }
	
	public Source returnObject(){
		return source;
	}
	
	public int returnId(){
		return id;
	}
}
