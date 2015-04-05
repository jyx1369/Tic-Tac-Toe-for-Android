/**
 * @author Yuxi Jin 
 * school: CMU
 * the date submitted: Oct 30, 2014
 */
package tictactoeAcitivities;

import com.example.tictactoe.R;

import util.FontsOverride;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends Activity {
	Typeface type;
	Button createGameButton;
	Button gameModeButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//set a custom font 
		FontsOverride.setDefaultFont(this,"MONOSPACE", "Lato-Black.ttf");
		//set a custom layout for actionBar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		actionBar.setCustomView(R.layout.welcome_actionbar);
		
		createGameButton=(Button) findViewById(R.id.create_game_button);
		gameModeButton=(Button) findViewById(R.id.game_mode_button);
		//Default creating game: pvp mode, p1 is X
		createGameButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent gameIntent = new Intent(WelcomeActivity.this,
						GameActivity.class);
				startActivity(gameIntent);
			}
			
		});
		//button to choose game mode
		gameModeButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent gameIntent = new Intent(WelcomeActivity.this,
						GameModeActivity.class);
				startActivity(gameIntent);
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//Default creating game: pvp mode, p1 is X
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.create_game_menu:
			Intent gameIntent = new Intent(this, GameActivity.class);
			startActivity(gameIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
