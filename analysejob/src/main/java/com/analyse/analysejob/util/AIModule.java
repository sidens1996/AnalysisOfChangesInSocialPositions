package com.analyse.analysejob.util;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.analyse.analysejob.entity.CityData;
import com.analyse.analysejob.entity.DegreeData;
import com.analyse.analysejob.entity.TotalData;
import org.apache.commons.math3.linear.*;
import org.apache.commons.math3.util.Pair;
public class AIModule {
	private final int totalTimeSpan=366;
	private final int timeSpan=15;
	private Map tagMap=new HashMap();
	DAO dao=new DAO("root","123456");
	private Map wordMap=new HashMap();
	private Map userMap=new HashMap();
	private Map jobVecStrMap=new HashMap();
	private Map jobVecMap=new HashMap();
	//2-25 pm
	private Map jobNumTypeMap=new HashMap();
	//2-26 am
	private Map<Integer,List> typeJobMap=new HashMap();
	private final int typeCount=20;
	private int featureDim;
	public AIModule() {
		readTags();
		//readWords();
		featureDim=tagMap.size()+wordMap.size();
		readJobVec();
		getJobVecMap(jobVecStrMap);
		//2-25 pm
		jobNumTypeMap.put(0, "OTHERS");
		jobNumTypeMap.put(1,"后端" );
		jobNumTypeMap.put(2, "大数据");
		jobNumTypeMap.put(3,"产品" );
		jobNumTypeMap.put(4, "前端");
		jobNumTypeMap.put(5,"运营" );
		jobNumTypeMap.put(6, "游戏");
		jobNumTypeMap.put(7, "移动互联网");
		jobNumTypeMap.put(8, "安卓");
		jobNumTypeMap.put(9,"Web前端" );
		jobNumTypeMap.put(10, "运维");
		jobNumTypeMap.put(11,"后端(PHP)" );
		jobNumTypeMap.put(12, "数据分析");
		jobNumTypeMap.put(13,"营销" );
		jobNumTypeMap.put(14, "行政");
		jobNumTypeMap.put(15,"产品经理(B)" );
		jobNumTypeMap.put(16, "财务");
		jobNumTypeMap.put(17,"广告营销" );
		jobNumTypeMap.put(18, " UI设计");
		jobNumTypeMap.put(19, "平面设计");
		//2-26 am
		readTypes();

	}
	/*
	public Job recommend(String[] tags,String[] words,int count) {

	}
	*/
	//2-26 am
	private void readTypes() {
		for(int i=0;i<typeCount;i++) {
			String sql=String.format("SELECT * FROM job_%d", i);
			List list=new ArrayList();
			ResultSet rs=dao.execute(sql);
			try {
				while(rs.next()){
					int id  = rs.getInt("id");
					list.add(id);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			typeJobMap.put(i,list);
		}
	}
	//2-25 am
	public void readTags() {
		ResultSet rs=dao.execute("select * from tags");
		try {
			while(rs.next()){
				//System.out.println("enter while");
				// 通过字段检索
				int id  = rs.getInt("id");
				String name = rs.getString("tag");
				// 输出数据
				//System.out.println(id+name);
				tagMap.put(name,id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	public void readWords() {
		ResultSet rs=dao.execute("select * from words");
		try {
			while(rs.next()){
				//System.out.println("enter while");
			    // 通过字段检索
			    int id  = rs.getInt("id");
			    String name = rs.getString("name");
			    // 输出数据
			    //System.out.println(id+name);
			    wordMap.put(name,id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/*
	public void readUsers() {
		ResultSet rs=dao.execute("select * from users");
		try {
			while(rs.next()){
				//System.out.println("enter while");
			    // 通过字段检索
			    int id  = rs.getInt("id");
			    String vector = rs.getString("vector");
			    // 输出数据
			    //System.out.println(id+name);
			    wordMap.put(id,vector);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	//2-25 am
	public void readJobVec() {
		ResultSet rs=dao.execute("select * from jobVectors");
		try {
			while(rs.next()){
				//System.out.println("enter while");
				// 通过字段检索
				int id  = rs.getInt("id");
				String vector = rs.getString("vector");
				// 输出数据
				//System.out.println(id+name);
				jobVecStrMap.put(id,vector);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//2-25 am
	private RealVector str2vec(String str) {
		RealVector vec=new ArrayRealVector(tagMap.size());
		String[] vec_str=str.split(",");
		int i=0;
		//List indexes=new ArrayList();
		for(String entry : vec_str){
			if(entry.equals("1")) {
				vec.setEntry(i, 1);
				//indexes.add(i);
			}
			i++;
		}
		//System.out.println(indexes.size());
		return vec;

	}
	private void getJobVecMap(Map map) {
		for(int i=0;i<map.size();i++) {
			RealVector vec=str2vec((String)map.get(i));
			jobVecMap.put(i,vec);
			//delete the same ele in str map
		}
	}

	private RealVector getVecFromStr(String[] tagStr,String[] wordStr) {
		RealVector tagVec=new ArrayRealVector(tagMap.size());
		RealVector wordVec=new ArrayRealVector(wordMap.size());
		for(String tag : tagStr) {
			int index=(int)tagMap.get(tag);
			tagVec.setEntry(index, 1);
		}
		for(String word : wordStr) {
			int index=(int)wordMap.get(word);
			wordVec.setEntry(index, 1);
		}
		RealVector feature=tagVec.append(wordVec);
		return feature;
	}
	//2/25 am
	private RealVector getVecFromStr(String[] tagStr) {
		RealVector tagVec=new ArrayRealVector(tagMap.size());
		for(String tag : tagStr) {
			int index=(int)tagMap.get(tag);
			tagVec.setEntry(index, 1);
		}
		return tagVec;
	}

	private double getConsineSim(RealVector v1,RealVector v2) {
		return v1.dotProduct(v2)/(v1.getNorm()*v2.getNorm());
	}
	private List<Pair<Integer,Double>> calConsineSim(RealVector vec,Map candidateMap) {

		//Map<Integer,Double> jobSimMap=new TreeMap<Integer,Double>();
		List<Pair<Integer,Double>> jobSimPairs=new ArrayList<>();
		for(Object job : candidateMap.keySet()) {
			int jobID=(int)job;
			RealVector jobVec=(RealVector)candidateMap.get(jobID);
			double sim=getConsineSim(vec,jobVec);
			//System.out.print(jobID+": ");
			//System.out.println(sim);
			if(!Double.isNaN(sim))
				jobSimPairs.add(new Pair(jobID,sim));
		}
		//List<Map.Entry<Integer,Double>> list = new ArrayList<Map.Entry<Integer,Double>>(jobSimMap.entrySet());
		//然后通过比较器来实现排序
		Collections.sort(jobSimPairs,new Comparator<Pair<Integer,Double>>() {
			//升序排序
			public int compare(Pair<Integer, Double> o1,
							   Pair<Integer, Double> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}

		});
		//System.out.println(jobSimPairs.get(0));
		return jobSimPairs;
	}
	//2-25 pm
	private MyJob fillJob(int id) {
		//return null if the id dont exist
		String sql=String.format("select * from rawJobs where id=%d", id);
		ResultSet rs=dao.execute(sql);
		MyJob job=null;
		try {
			while(rs.next()) {
				String name=rs.getString("name");
				int salary_low=rs.getInt("salary_low");
				int salary_high=rs.getInt("salary_high");
				String city=rs.getString("city").trim();
				String exp=rs.getString("exp");
				String edu=rs.getString("edu");
				String tags=rs.getString("tags");
				String description=rs.getString("description");
				String company_field=rs.getString("company_field");
				String company_stage=rs.getString("company_stage");
				String company_scale=rs.getString("company_scale");
				Date time=rs.getDate("time");
				String url=rs.getString("url");
				job=new MyJob(id,name,city,salary_low,salary_high,exp,edu,tags,description,company_field,company_stage,company_scale,url,time);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql2=String.format("SELECT * FROM job_words WHERE id=%d", id);
		ResultSet rs2=dao.execute(sql2);
		try {
			while(rs2.next()) {
				String words=rs2.getString("words");
				if(job!=null)
					job.setWords(words);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return job;
	}
	//2-25 am
	public List<MyJob> recommendJobs(RealVector vec) {
		List<Pair<Integer,Double>> list=calConsineSim(vec, jobVecMap);
		List<Pair<Integer,Double>> target=list.subList(0, 1000);
		List<MyJob> jobs=new ArrayList<MyJob>();
		for(Pair<Integer,Double> x:target) {
			MyJob job=fillJob(x.getKey());
			if(job!=null)
				jobs.add(job);
		}
		return jobs;

	}
	//2-25 am
	//2-26 am
	public TotalData recommend(String[] tagStr) {
		RealVector vec=getVecFromStr(tagStr);
		List<MyJob> jobs=recommendJobs(vec);
		Statistic s=new Statistic(jobs);
		//2-26 am
		int id=jobs.get(0).id;
		String type=getJobTypeById(id);
		return s.getTotalData(type);
	}
	//2-26 am
	private String getJobTypeById(int id) {
		String type="";
		for(int i : typeJobMap.keySet()) {
			List list=typeJobMap.get(i);
			if(list.contains(id))
				type=(String) jobNumTypeMap.get(i);
		}
		return type;
	}
	//2-22 am
	public Date dateShift(Date date,int days) {
		Calendar calendar=new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE, days);
		java.util.Date utilDate=(java.util.Date)calendar.getTime();
		return new Date(utilDate.getTime());
	}
	public JobTrendData getJobTrend(int jobType,Date date) {
		Date startTime=dateShift(date,-timeSpan);
		Date endTime=dateShift(date,timeSpan);
		String sql=String.format("select * from job_%d_trend where time<'%s' and time >'%s'",jobType,endTime,startTime);
		ResultSet rs=dao.execute(sql);
		String[] timeSeq=new String[2*timeSpan-1];
		double[] heatSeq=new double[2*timeSpan-1];
		int i=0;
		try {
			while(rs.next()){
				//System.out.println("enter while");
				// 通过字段检索
				Date time = rs.getDate("time");
				double heat = rs.getDouble("heat");
				// 输出数据
				//System.out.println(id+name);
				timeSeq[i]=time.toString();
				heatSeq[i]=heat;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new JobTrendData(timeSeq,heatSeq);
	}
	//2-25 pm
	public TotalTrendData getTotalTrendData(int[] jobs,Date date) {
		TotalTrendData total=new TotalTrendData();
		for(int type : jobs) {
			JobTrendData trend=getJobTrend(type,date);
			String name=(String) jobNumTypeMap.get(type);
			total.addJobTrend(trend, name);
		}
		return total;
	}
	public static void main(String[] args) {
		AIModule ai=new AIModule();
		//DAO dao=new DAO("song","");
		//dao.execute("select * from tags limit 10");
		//RealVector rv1=new ArrayRealVector(new double[] {1,2,3});
		//RealVector rv2=new ArrayRealVector(new double[] {1,2,3});
		//System.out.println(rv1.getNorm());
		//System.out.println(rv1.dotProduct(rv2));
		//ai.readWords();
		//System.out.println(ai.wordMap);
		//System.out.println(ai.wordMap.size());

		//System.out.println(ai.jobVecStrMap.size());
		//String testVec=(String)ai.jobVecStrMap.get(1);
		//System.out.println(testVec);
		//RealVector vec=ai.str2vec(testVec);
		//System.out.println(vec);
		//System.out.println(ai.jobVecStrMap.get(1).toString().length());
		//System.out.println(ai.jobVecMap.size());
		//System.out.println(ai.jobVecMap.get(1));
		/*
		System.out.println(ai.tagMap);
		System.out.println(ai.wordMap);
		*/

//		String[] testTagStr=new String[] {"人工智能"};
//		//String[] testWordStr=new String[] {"员工福利"};
//		TotalData total=ai.recommend(testTagStr);
//
//		for(CityData city:total.getCityData()) {
//			System.out.println(city.getName()+city.getValue());
//		}
//		for(String city:total.getDataAxis()) {
//			System.out.println(city);
//		}
//		for(int sa:total.getSalarydata()) {
//			System.out.println(sa);
//		}
//		for(DegreeData degree:total.getDegreeData()) {
//			System.out.println(degree.getName()+degree.getValue());
//		}
//		System.out.println(total.getJobname());
//		System.out.println(total.getKeywords());
		/*
		RealVector vec=ai.getVecFromStr(testTagStr,testWordStr);
		System.out.println(vec);
		List<Job> jobs=ai.recommendJobs(vec);
		System.out.println(jobs.size());
		System.out.println(jobs.get(0).job_name);
		Statistic s=new Statistic(jobs);
		Map<String,Integer> city_count=s.city_count;
		Map city_salary=s.city_salary;
		Map degree_count=s.degree_count;
		System.out.println(city_count);
		System.out.println(city_salary);
		System.out.println(degree_count);
		TotalData total=s.getTotalData();
		for(CityData city:total.getCityData()) {
			System.out.println(city.getName()+city.getValue());
		}
		for(String city:total.getDataAxis()) {
			System.out.println(city);
		}
		for(int sa:total.getSalarydata()) {
			System.out.println(sa);
		}
		for(DegreeData degree:total.getDegreeData()) {
			System.out.println(degree.getName()+degree.getValue());
		}
		/*
		List<Pair<Integer,Double>> list=ai.calConsineSim(vec, ai.jobVecMap);
		Pair<Integer,Double> simVec=list.get(0);
		List<Pair<Integer,Double>> target=list.subList(0, 100);
		for(Pair<Integer,Double> pair : target) {
			System.out.println(pair);
		}
		*/
		/*
		System.out.println(simVec);
		//System.out.println(ai.jobVecMap.get(197));
		Job job=ai.fillJob(1137);
		System.out.println(job.job_name);
		System.out.println(job.tags);
		System.out.println(job.job_description);
		*/
		/*
		Date sqlDate=java.sql.Date.valueOf("2019-1-5");

		JobTrendData trend=ai.getJobTrend(12, sqlDate);
		for(int i=0;i<29;i++) {
			System.out.println(trend.time[i]+':'+trend.heat[i]);
		}
		*/

		int[] testArray=new int[]{0,1,2,3,4,5,6,7,8,9};
		Date sqlDate=java.sql.Date.valueOf("2019-1-5");
		TotalTrendData total=ai.getTotalTrendData(testArray,sqlDate);
		for(int i=0;i<10;i++) {
			System.out.println(total.jobNames[i]);
			System.out.println(total.trends[i].time[0]+":"+total.trends[i].heat[0]);
		}

	}
}
