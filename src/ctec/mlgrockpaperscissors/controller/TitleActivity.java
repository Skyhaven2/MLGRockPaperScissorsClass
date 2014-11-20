package ctec.mlgrockpaperscissors.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TitleActivity extends Activity
{
	private Button playButton;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);

		playButton = (Button) findViewById(R.id.playButton);
		
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
	}
}