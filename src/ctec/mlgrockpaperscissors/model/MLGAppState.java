package ctec.mlgrockpaperscissors.model;

import android.app.Application;
import android.content.SharedPreferences.Editor;
import android.content.*;

public class MLGAppState extends Application
{

	public int overallScore = 0;
	public int highestKillStreak = 0;
	public int highestScore = 0;
	public int overallKills = 0;
	
//	public void onCreate()
//	{
//		super.onCreate();
//	}
	
	public int getOverallScore()
	{
		return overallScore;
	}
	public int getHighestKillStreak()
	{
		return highestKillStreak;
	}
	public int getHighestScore()
	{
		return highestScore;
	}
	public int getOverallKills()
	{
		return overallKills;
	}
	public void setOverallScore(int overallScore)
	{
		this.overallScore = overallScore;
	}
	public void setHighestKillStreak(int highestKillStreak)
	{
		this.highestKillStreak = highestKillStreak;
	}
	public void setHighestScore(int highestScore)
	{
		this.highestScore = highestScore;
	}
	public void setOverallKills(int overallKills)
	{
		this.overallKills = overallKills;
	}
	
	public void saveData()
	{
		
	}
}
