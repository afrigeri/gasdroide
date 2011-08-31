package alf.gasdroide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class GasdroideActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
   	    MenuInflater mInflater = getMenuInflater();
   	    mInflater.inflate(R.menu.menu, menu);
        // return true to get the menu displayed
   	    return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item selection
        switch (item.getItemId()) {
        case R.id.preferences:
        	startActivity(new Intent(this, EditPreferences.class));
            return true;
        case R.id.show_settings:
        	startActivity(new Intent(this, ShowSettingsActivity.class));            
            return true;
        case R.id.quit:
            //showHelp();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
  	
}
