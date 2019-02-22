package com.analyse.analysejob.util;
import java.sql.Date;

public class MyJob {
	public int id;
	public MyJob(int id, String job_name, String job_city, int salary_low, int salary_high, String work_years,
			String degree_need, String tags, String job_description, String company_field, String company_development,
			String company_scale, String url, Date public_time) {
		super();
		this.id = id;
		this.job_name = job_name;
		this.job_city = job_city;
		this.salary_low = salary_low;
		this.salary_high = salary_high;
		this.work_years = work_years;
		this.degree_need = degree_need;
		this.tags = tags;
		this.job_description = job_description;
		this.company_field = company_field;
		this.company_development = company_development;
		this.company_scale = company_scale;
		this.url = url;
		this.public_time = public_time;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public void setJob_city(String job_city) {
		this.job_city = job_city;
	}

	public void setSalary_low(int salary_low) {
		this.salary_low = salary_low;
	}

	public void setSalary_high(int salary_high) {
		this.salary_high = salary_high;
	}

	public void setWork_years(String work_years) {
		this.work_years = work_years;
	}

	public void setDegree_need(String degree_need) {
		this.degree_need = degree_need;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}

	public void setCompany_field(String company_field) {
		this.company_field = company_field;
	}

	public void setCompany_development(String company_development) {
		this.company_development = company_development;
	}

	public void setCompany_scale(String company_scale) {
		this.company_scale = company_scale;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPublic_time(Date public_time) {
		this.public_time = public_time;
	}
	public String job_name;
	public String job_city;
	public int salary_low;
	public int salary_high;
	public String work_years;
	public String degree_need;
	public String tags;
	public String job_description;
	public String company_field;
	public String company_development;
	public String company_scale;
	public String url;
	public Date public_time; 
	//private DAO dao;
	
	public MyJob() {
		
	}
}
