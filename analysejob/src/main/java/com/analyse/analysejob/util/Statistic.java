package com.analyse.analysejob.util;
import com.analyse.analysejob.entity.CityData;
import com.analyse.analysejob.entity.DegreeData;
import com.analyse.analysejob.entity.TotalData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Statistic {
	private List<MyJob> jobs;
	public Map<String,Integer> city_salary=new HashMap();
	public Map<String,Integer> city_count=new HashMap();
	public Map<String,Integer> degree_count=new HashMap();
	public Statistic(List<MyJob> jobs) {
		this.jobs=jobs;
		doStatistic();
	}
	private void doStatistic() {
		if(jobs==null)
			return;
		for(MyJob job : jobs) {
			int salary_low=job.salary_low;
			int salary_high=job.salary_high;
			int salary=(salary_low+salary_high)/2;
			String city=job.job_city;
			String degree=job.degree_need;
			if(city_count.containsKey(city)) {
				int count=(int)city_count.get(city);
				city_count.put(city,count+1);
			}else {
				city_count.put(city,1);
			}
			if(degree_count.containsKey(degree)) {
				int count=(int)degree_count.get(degree);
				degree_count.put(degree,count+1);
			}else {
				degree_count.put(degree,1);
			}
			if(city_salary.containsKey(city)) {
				int sa=(int)city_salary.get(city);
				city_salary.put(city,sa+salary);
			}else {
				city_salary.put(city,salary);
			}
			
		}
		for(String city:city_salary.keySet()) {
			int count=city_count.get(city);
			int sa=city_salary.get(city);
			city_salary.put(city,sa/count);
		}
	}
	public TotalData getTotalData() {
		TotalData total=new TotalData();
		List<CityData> cityData=new ArrayList();
		List<DegreeData> degreeData=new ArrayList();
		String[] axis=new String[city_salary.size()];
		Integer[] sa=new Integer[city_salary.size()];
		for(String city : city_count.keySet()) {
			cityData.add(new CityData(city,city_count.get(city)));
		}
		for(String degree : degree_count.keySet()) {
			degreeData.add(new DegreeData(degree,degree_count.get(degree)));
		}
		int i=0;
		for(String city : city_salary.keySet()) {
			axis[i]=city;
			sa[i]=city_salary.get(city);
			i++;
		}
		total.setCityData(cityData);
		total.setDegreeData(degreeData);
		total.setDataAxis(axis);
		total.setSalarydata(sa);
		return total;
	}
}
