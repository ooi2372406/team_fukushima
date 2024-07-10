package bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Map;

public class StudentAnalysis implements Serializable {
    private String no;
    private String name;
    private String subjectCd;
    private String subjectName;
    private Map<String, Integer> points;
    private int rank;
    private String comment;
    private Date date;
    private int index; // フィールドとしてindexを定義する場合
    private School school;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Map<String, Integer> getPoints() {
        return points;
    }

    public void setPoints(Map<String, Integer> points) {
        this.points = points;
    }

    public int getPoint(String key) {
        return points.getOrDefault(key, 0);
    }

    public void putPoint(String key, int value) {
        this.points.put(key, value);
    }

    public int getRank(){
    	return rank;
    }

    public void setRank(int rank){
    	this.rank = rank;
    }

    public String getComment(){
    	return comment;
    }

    public void setComment(String comment){
    	this.comment = comment;
    }

    public Date getDate(){
    	return date;
    }

    public void setDate(Date date){
    	this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

}
