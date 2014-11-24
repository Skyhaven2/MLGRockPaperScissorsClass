package ctec.mlgrockpaperscissors.model;

import android.app.Application;

public class MLGAppState extends Application
{

	private int overallScore = 0;
	private int highestKillStreak = 0;
	private int highestScore = 0;
	private int overallKills = 0;
	
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
}
