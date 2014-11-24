package ctec.mlgrockpaperscissors.controller;

import ctec.mlgrockpaperscissors.model.MLGAppState;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	private Button saveButton;
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String OverallScore = "OverallScoreKey";
	public static final String HighestKillStreak= "highestKillStreakKey";
	public static final String HighestScore = "highestScoreKey";
	public static final String OverallKills = "overallKillsKey";
	
	SharedPreferences sharedpreferences;

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
		saveButton = (Button) findViewById(R.id.saveButton);
		
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		
		appState = (MLGAppState) getApplication();
		
		setTextViewsStart();
		
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
		
		saveButton.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View currentView)
			{
				Editor editor = sharedpreferences.edit();
				editor.putInt(OverallScore, appState.overallScore);
				editor.putInt(OverallKills, appState.overallKills);
				editor.putInt(HighestScore, appState.highestScore);
				editor.putInt(HighestKillStreak, appState.highestKillStreak);
				
				editor.commit();
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
	
	public void setTextViewsStart()
	{
		overallScoreTextView.setText("Overall Score: " + Integer.toString(sharedpreferences.getInt(OverallScore, 0)));
		overallKillsTextView.setText("Overall Kills: " + Integer.toString(sharedpreferences.getInt(OverallKills, 0)));
		highestScoreTextView.setText("High Score: " + Integer.toString(sharedpreferences.getInt(HighestScore, 0)));
		highestKillStreakTextView.setText("Highest Kill Streak: " + Integer.toString(sharedpreferences.getInt(HighestKillStreak, 0)));
	}
	
}