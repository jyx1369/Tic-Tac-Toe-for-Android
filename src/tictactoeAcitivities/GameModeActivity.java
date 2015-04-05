/**
 * @author Yuxi Jin 
 * school: CMU
 * the date submitted: Oct 30, 2014
 */
package tictactoeAcitivities;

import com.example.tictactoe.R;

import util.FontsOverride;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class GameModeActivity extends Activity {
	Button cancelButton;
	Button createButton;
	Button pvpButton;
	Button pveButton;
	Button oButton;
	Button xButton;
	private String startPiece=null;
	private int gameMode;
	private static final int PVP_MODE=1;
	private static final int PVE_MODE=2;
	private static final String PIECE_O="O";
	private static final String PIECE_X="X";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_mode);
		FontsOverride.setDefaultFont(this,"MONOSPACE", "Lato-Black.ttf");
		cancelButton=(Button) findViewById(R.id.cancel_button);
		createButton=(Button) findViewById(R.id.create_button);
		pvpButton=(Button) findViewById(R.id.pvp_button);
		pveButton=(Button) findViewById(R.id.pve_button);
		oButton=(Button) findViewById(R.id.o_button);
		xButton=(Button) findViewById(R.id.x_button);
		pvpButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				gameMode=PVP_MODE;
			}
		});
		pveButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				gameMode=PVE_MODE;
			}		
		});
		oButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				startPiece=PIECE_O;
			}		
		});
		xButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				startPiece=PIECE_X;
			}		
		});	
		cancelButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent gameIntent = new Intent(GameModeActivity.this,
						WelcomeActivity.class);
				startActivity(gameIntent);
			}		
		});
		createButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(startPiece==null){
					Toast.makeText(getApplicationContext(),"please choose one piece to start" ,Toast.LENGTH_SHORT).show();
				}else if(gameMode==0){
					Toast.makeText(getApplicationContext(),"please choose the game mode to start" ,Toast.LENGTH_SHORT).show();
				}else {
					Intent gameIntent = new Intent(GameModeActivity.this,
							GameActivity.class);
					gameIntent.putExtra("PIECE",startPiece);
					gameIntent.putExtra("MODE",gameMode);				
					startActivity(gameIntent);
				}
			}		
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_mode, menu);
		return true;
	}

}
