package ctec.mlgrockpaperscissors.controller;

import java.lang.reflect.Array;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity
{
	private Button rockButton;
	private Button paperButton;
	private Button scissorsButton;
	private Button menuButton;
	private TextView resultTextView;
	private TextView scoreTextView;
	private TextView livesTextView;
	private ImageView opponentChoiceImage;
	private int numberOfWins = 0;
	private int killStreak = 0;
	private int numberOfLives = 3;
	private int userScore;
	private String botChoice = "";
	private String[] botAi;
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		rockButton = (Button) findViewById(R.id.rockButton);
		paperButton = (Button) findViewById(R.id.paperButton);
		scissorsButton = (Button) findViewById(R.id.scissorsButton);
		menuButton = (Button) findViewById(R.id.menuButton);
		resultTextView = (TextView) findViewById(R.id.resultTextView);
		opponentChoiceImage = (ImageView) findViewById(R.id.opponentChoiceImage);
		scoreTextView = (TextView) findViewById(R.id.scoreTextView);
		livesTextView = (TextView) findViewById(R.id.livesTextView);
		
		botAIArrayList();

		setupListners();
		BotAiSystem();
		
	}

	private void setupListners()
	{
		rockButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				if(botChoice.equals("rock"))
				{
					resultTextView.setText("You tie!");
				}
				else if(botChoice.equals("paper"))
				{
					resultTextView.setText("You lose!");
				}
				else if(botChoice.equals("scissors"))
				{
					numberOfWins++;
					scoreTextView.setText("Wins: " + Integer.toString(numberOfWins));
					resultTextView.setText("You win!");
				}
				BotAiSystem();
			}
		});

		paperButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				if(botChoice.equals("rock"))
				{
					numberOfWins++;
					scoreTextView.setText("Wins: " + Integer.toString(numberOfWins));
					resultTextView.setText("You win!");
				}
				else if(botChoice.equals("paper"))
				{
					resultTextView.setText("You tie!");
				}
				else if(botChoice.equals("scissors"))
				{
					resultTextView.setText("You lose!");
				}
				BotAiSystem();
			}
		});

		scissorsButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				if(botChoice.equals("rock"))
				{
					resultTextView.setText("You lose!");
				}
				else if(botChoice.equals("paper"))
				{
					numberOfWins++;
					scoreTextView.setText("Wins: " + Integer.toString(numberOfWins));
					resultTextView.setText("You win!");
				}
				else if(botChoice.equals("scissors"))
				{
					resultTextView.setText("You tie!");
				}
				BotAiSystem();
			}
		});

		menuButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View currentView)
			{
				Intent currentIntent = new Intent();
				setResult(RESULT_OK, currentIntent);
				finish();
			}
		});

	}

	private void botAIArrayList()
	{
		botAi = new String[3];
		botAi[0] = "rock";
		botAi[1] = "paper";
		botAi[2] = "scissors";
	}
	
	private void BotAiSystem()
	{
		int randomPosition = (int) (Math.random() * 3);
		
		if(randomPosition == 0)
		{
			botChoice = botAi[0];
			opponentChoiceImage.setImageResource(R.drawable.rock);
		}
		else if(randomPosition == 1)
		{
			botChoice = botAi[1];
			opponentChoiceImage.setImageResource(R.drawable.paper);
		}
		else if(randomPosition == 2)
		{
			botChoice = botAi[2];
			opponentChoiceImage.setImageResource(R.drawable.scissors);
		}
	}
	
	private boolean isDead(int numberOfLives)
	{
		boolean isDead = false;
		if(numberOfLives <= 1)
		{
			isDead = true;
		}
		return isDead;
	}
	
}