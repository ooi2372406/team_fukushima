package bean;

public class Subject implements java.io.Serializable{

	private School school;
	private String cd;
	private String name;
	private String schoolcd;


	//Subjectbean
	//ゲッター
	public School getSchool(){
		return school;
	}

	public String getCd(){
		return cd;
	}

	public String getName(){
		return name;
	}

	public String getSchoolCd(){
		return schoolcd;
	}


	 //セッター
	public void setSchool(School school){
		this.school = school;
	}

	public void setCd(String cd){
		this.cd = cd;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setSchoolCd(String schoolcd){
		this.schoolcd = schoolcd;
	}

}
