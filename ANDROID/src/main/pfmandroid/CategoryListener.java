package main.pfmandroid;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/*
 * Class performs listening to the category spinner change, allows us to track which option is chosen.
 */

public class CategoryListener implements OnItemSelectedListener{
	
	private String category;
	private int pos;
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		//Get the category name
		category = (String) parent.getItemAtPosition(pos);
		this.pos = pos;
    }

	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }
	
	public String returnCategory(){
		return category;
	}
	
	public int returnPos(){
		return pos;
	}
}
