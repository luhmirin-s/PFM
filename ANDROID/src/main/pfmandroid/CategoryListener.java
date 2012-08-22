package main.pfmandroid;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class CategoryListener implements OnItemSelectedListener{

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		String test = (String) parent.getItemAtPosition(pos);
    	Log.d("Category", test);
    }

	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }

}
