package main.pfmandroid;

import main.pfmandroid.data.Category;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/*
 * Class performs listening to the category spinner change, allows us to track which option is chosen.
 */

public class CategoryListener implements OnItemSelectedListener{
	
	private Category category;
	private int pos;
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		//Get the category name
		category = (Category) parent.getItemAtPosition(pos);
		this.pos = category.getId();
    }

	@Override
    public void onNothingSelected(AdapterView<?> parent) {
        
    }
	
	public Category returnCategory(){
		return category;
	}
	
	public int returnId(){
		return pos;
	}
}
