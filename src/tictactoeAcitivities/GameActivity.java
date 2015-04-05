/**
 * @author Yuxi Jin 
 * school: CMU
 * the date submitted: Oct 30, 2014
 */
package tictactoeAcitivities;

import com.example.tictactoe.R;

import util.FontsOverride;
import util.ImageAdapter;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import core.Player;
import core.TicTacToe;
import core.TicTacToeImpl;

public class GameActivity extends Activity {
	private static Context mContext;
	Button backButton;
	Button restartButton;
	TextView stateLabel;
	TextView scoreLabel;
	GridView gridView;
	static Player player1;
	static Player player2;
	private static int gridWidth;
	static TicTacToe game;
	private static final String PIECE_O="O";
	private static final String PIECE_X="X";
	private static final int PVE_MODE=2;
	private String choosePiece;
	private int chooseMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		FontsOverride.setDefaultFont(this,"MONOSPACE", "Lato-Black.ttf");
		//set a custom layout for actionBar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		actionBar.setCustomView(R.layout.game_actionbar);
		backButton=(Button) findViewById(R.id.back_button);
		restartButton=(Button) findViewById(R.id.restart_button);
		stateLabel=(TextView)findViewById(R.id.stateLabel);
		scoreLabel=(TextView)findViewById(R.id.scoreLabel);
		//default mode
		choosePiece="X";
		//get mode from GameModeActivity
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
		    choosePiece = extras.getString("PIECE");
		    chooseMode = extras.getInt("MODE");
		}
		createGameAndSetPlayer(choosePiece,chooseMode);
		backButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent gameIntent = new Intent(GameActivity.this,
						WelcomeActivity.class);
				startActivity(gameIntent);
			}	
		});
		
		restartButton.setOnClickListener(new OnClickListener(){		
			@Override
			public void onClick(View v) {
				rematchGame();
				stateLabel.setText("");
				restartButton.setVisibility(View.GONE);
				gridView.setAdapter(new ImageAdapter(getApplicationContext()));
			}		
		});
		gridView = (GridView) findViewById(R.id.gridview);
		gridView.setAdapter(new ImageAdapter(this));
		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// CHANGE IMAGE HERE
	            int x=position/gridWidth;
	            int y=position%gridWidth;
	            //if player play valid move
	            if(game.playMove(x, y)){
	            	Player player = game.getSquare(x, y);
		            if(player!=null){
		            	//set image view for the clicked position
		            	ImageView imageview=(ImageView)v;
		            	if(player.getSymbol().equals(PIECE_O)){
		            		imageview.setImageResource(R.drawable.pieceo_2x);
			            }else if(player.getSymbol().equals(PIECE_X)){
			            	imageview.setImageResource(R.drawable.piecex_2x);
			            }
		            	//use toast to debug
		            	//Toast.makeText(getApplicationContext()," "+position,Toast.LENGTH_SHORT).show();
		            }  
		            //check whether this round of game is ended
		            if(game.hasWon() || game.isFull()){
		            	if(game.hasWon()){
		            		Player winner=game.getCurrentPlayer();
			            	if (winner != null) {
			            		//display state label 
			            		stateLabel.setText(winner.getName()+" Wins!!!");
			            		//update the winner's scores
			        			winner.setScore(winner.getScore()+1);
			                }
		            	}else if(game.isFull()){
			            	stateLabel.setText("Tie State");
			            }
		            	//make rematch button visible
		            	restartButton.setVisibility(View.VISIBLE);
		            	//display players' scores
		            	scoreLabel.setText(player1.getName()+":"+player1.getScore()+"   "+player2.getName()+":"+player2.getScore());
		            	
		            }
		            //switch player
		            game.switchPlayers();
		            
		            //used when choose PVE_MODE
		            if(chooseMode==PVE_MODE){
		            	int computer_position=((TicTacToeImpl)game).computerMove();
		            	//Toast.makeText(getApplicationContext(),"position "+computer_position ,Toast.LENGTH_SHORT).show();
			            ImageView computer_view=(ImageView)gridView.getChildAt(computer_position);
			            if(player!=null){
			            	if(player.getSymbol().equals(PIECE_O)){
			            		computer_view.setImageResource(R.drawable.piecex_2x);
				            }else if(player.getSymbol().equals(PIECE_X)){
				            	computer_view.setImageResource(R.drawable.pieceo_2x);
				            }
			            	computer_view.setClickable(false);
			            	computer_view.setEnabled(false);
			            } 
			            //check whether this round of game is ended
			            if(game.hasWon() || game.isFull()){
			            	if(game.hasWon()){
			            		Player winner=game.getCurrentPlayer();
				            	if (winner != null) {
				            		//display state label 
				            		stateLabel.setText(winner.getName()+" Wins!!!");
				            		//update the winner's scores
				        			winner.setScore(winner.getScore()+1);
				        			//use Toast to debug
				        			//Toast.makeText(getApplicationContext(),player1.getScore()+" "+player2.getScore() ,Toast.LENGTH_SHORT).show();
				                }
			            	}else if(game.isFull()){
				            	stateLabel.setText("Tie State");
				            }
			            	//make rematch button visible
			            	restartButton.setVisibility(View.VISIBLE);
			            	//display players' scores
			            	scoreLabel.setText(player1.getName()+":"+player1.getScore()+"   "+player2.getName()+":"+player2.getScore());
			            	
			            }
			            game.switchPlayers();
		            }    
		            
	            }	            
			}
		});
		
		
 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	public static Context getContext() {
		return mContext;
	}

	private static void createGameAndSetPlayer(String piece, int mode) {
		//create a new game
		game=new TicTacToeImpl();
		//player setup
		if(piece.equals(PIECE_X)){
			player1=new Player("P1", PIECE_X);
			player2=new Player("P2", PIECE_O);
		}else{
			player1=new Player("P1", PIECE_O);
			player2=new Player("P2", PIECE_X);
		}
		game.addPlayer(player1);
		game.addPlayer(player2);
		
		gridWidth = game.getGridWidth();
		//start the game
		game.startNewGame();
		

	}
	//rematch the game
	private static void rematchGame() {
		game.startNewGame();
	}

}
