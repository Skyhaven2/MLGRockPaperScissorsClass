package ctec.mlgrockpaperscissors.controller;

import java.lang.reflect.Array;

import ctec.mlgrockpaperscissors.model.MLGAppState;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
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
	private TextView streakTextView;
	private ImageView opponentChoiceImage;
	private int userScore = 0;
	private int killStreak = 0;
	private int numberOfLives = 3;
	private String botChoice = "";
	private String[] botAi;
	private MediaPlayer soundEffectPlayer;
	private MLGAppState appState;
	private TitleActivity titleActivity;

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
		streakTextView = (TextView) findViewById(R.id.streakTextView);
		
		appState = (MLGAppState) getApplication();
		
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
					soundEffectPlayer = MediaPlayer.create(getBaseContext(), R.raw.hitmarkerlooped);
					soundEffectPlayer.start();
				}
				else if(botChoice.equals("paper"))
				{
					updateHighestKillStreak();
					killStreak = 0;
					streakTextView.setText("Kill Streak: " + Integer.toString(killStreak));
					numberOfLives--;
					livesTextView.setText("Lives: " + Integer.toString(numberOfLives));
					loseSoundEffectPlayer();
					if(isDead(numberOfLives))
					{
						updateAchievements();
						Intent currentIntent = new Intent();
						setResult(RESULT_OK, currentIntent);
						finish();
					}
					resultTextView.setText("You lose!");
				}
				else if(botChoice.equals("scissors"))
				{
					killStreak++;
					streakTextView.setText("Kill Streak: " + Integer.toString(killStreak));
					userScore = userScore + 100;
					scoreTextView.setText("Score: " + Integer.toString(userScore));
					resultTextView.setText("You win!");
					winSoundEffectPlayer();
					
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
					killStreak++;
					streakTextView.setText("Kill Streak: " + Integer.toString(killStreak));
					userScore = userScore + 100;
					scoreTextView.setText("Score: " + Integer.toString(userScore));
					resultTextView.setText("You win!");
					winSoundEffectPlayer();
				}
				else if(botChoice.equals("paper"))
				{
					resultTextView.setText("You tie!");
					soundEffectPlayer = MediaPlayer.create(getBaseContext(), R.raw.hitmarkerlooped);
					soundEffectPlayer.start();
				}
				else if(botChoice.equals("scissors"))
				{
					updateHighestKillStreak();
					killStreak = 0;
					streakTextView.setText("Kill Streak: " + Integer.toString(killStreak));
					numberOfLives--;
					livesTextView.setText("Lives: " + Integer.toString(numberOfLives));
					loseSoundEffectPlayer();
					if(isDead(numberOfLives))
					{
						updateAchievements();
						Intent currentIntent = new Intent();
						setResult(RESULT_OK, currentIntent);
						finish();
					}
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
					updateHighestKillStreak();
					killStreak = 0;
					streakTextView.setText("Kill Streak: " + Integer.toString(killStreak));
					numberOfLives--;
					livesTextView.setText("Lives: " + Integer.toString(numberOfLives));
					loseSoundEffectPlayer();
					if(isDead(numberOfLives))
					{
						updateAchievements();
						Intent currentIntent = new Intent();
						setResult(RESULT_OK, currentIntent);
						finish();
					}

					resultTextView.setText("You lose!");
				}
				else if(botChoice.equals("paper"))
				{
					userScore = userScore + 100;
					scoreTextView.setText("Score: " + Integer.toString(userScore));
					killStreak++;
					streakTextView.setText("Kill Streak: " + Integer.toString(killStreak));
					resultTextView.setText("You win!");
					winSoundEffectPlayer();
				}
				else if(botChoice.equals("scissors"))
				{
					resultTextView.setText("You tie!");
					soundEffectPlayer = MediaPlayer.create(getBaseContext(), R.raw.hitmarkerlooped);
					soundEffectPlayer.start();
				}
				BotAiSystem();
			}
		});

		menuButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View currentView)
			{
				updateAchievements();
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
	
	private void winSoundEffectPlayer()
	{
		int randomPosition = (int) (Math.random() * 3);
		
		if((killStreak % 3) == 0)
		{
			soundEffectPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.ohbabytriple);
		}
		else if(randomPosition == 0)
		{
			soundEffectPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.hitmarker);
		}
		else if(randomPosition == 1)
		{
			soundEffectPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.hitmarker);
		}
		else if(randomPosition == 2)
		{
			soundEffectPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.hitmarker);
		}
		soundEffectPlayer.start();
	}
	private void loseSoundEffectPlayer()
	{
		soundEffectPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.wrongbuzzer);
		soundEffectPlayer.start();
	}
	
	private void updateAchievements()
	{
		appState.setOverallKills(appState.getOverallKills() + (userScore/100));
		appState.setOverallScore(appState.getOverallScore() + userScore);
		if(appState.getHighestScore() < userScore)
		{
			appState.setHighestScore(userScore);
		}
	}
	
	private void updateHighestKillStreak()
	{
		if(appState.getHighestKillStreak() < killStreak )
		{
			appState.setHighestKillStreak(killStreak);
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