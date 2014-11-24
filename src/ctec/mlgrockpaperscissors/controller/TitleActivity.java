package ctec.mlgrockpaperscissors.controller;

import ctec.mlgrockpaperscissors.model.MLGAppState;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TitleActivity extends Activity
{
	private Button playButton;
	private TextView overallScoreTextView;
	private TextView overallKillsTextView;
	private TextView highestKillStreakTextView;
	private TextView highestScoreTextView;
	private MLGAppState appState;
	private Button updateButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);

		playButton = (Button) findViewById(R.id.playButton);
		overallScoreTextView = (TextView) findViewById(R.id.overallScoreTextView);
		overallKillsTextView = (TextView) findViewById(R.id.overallKillsTextView);
		highestScoreTextView = (TextView) findViewById(R.id.highestScoreTextView);
		highestKillStreakTextView = (TextView) findViewById(R.id.highestKillStreakTextView);
		updateButton = (Button) findViewById(R.id.updateButton);
		
		appState = (MLGAppState) getApplication();
		
		setTextViews();
		
		setupListners();
	}

	private void setupListners()
	{
		playButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View currentView)
			{
				Intent myIntent = new Intent(currentView.getContext(), GameActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		updateButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View currentView)
			{
				setTextViews();
			}
		});

	}
	
	public void setTextViews()
	{
		overallScoreTextView.setText("Overall Score: " + Integer.toString(appState.getOverallScore()));
		overallKillsTextView.setText("Overall Kills: " + Integer.toString(appState.getOverallKills()));
		highestScoreTextView.setText("High Score: " + Integer.toString(appState.getHighestScore()));
		highestKillStreakTextView.setText("Highest Kill Streak: " + Integer.toString(appState.getHighestKillStreak()));
	}
	
}