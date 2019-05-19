package com.analyse.analysejob.util;

public class TotalTrendData {
	public final int job_count=10;
	public JobTrendData[] trends;
	public String[] jobNames;
	private int i;
	public TotalTrendData() {
		i=0;
		trends=new JobTrendData[job_count];
		jobNames=new String[job_count];
	}
	public void addJobTrend(JobTrendData trend,String jobName) {
		trends[i]=trend;
		jobNames[i]=jobName;
		i++;
	}
	
}
