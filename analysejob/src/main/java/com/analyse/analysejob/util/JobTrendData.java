package com.analyse.analysejob.util;

public class JobTrendData {
	public String[] time;
	public JobTrendData(String[] time, double[] heat) {
		super();
		this.time = time;
		this.heat = heat;
	}
	public double[] heat;
}
