package bean;

public class ClassNum implements java.io.Serializable{

	private String schoolcd;
	private String classnum;

	public String getSchoolCd(){
		return schoolcd;
	}

	public String getClassNum(){
		return classnum;
	}

	public void setSchoolCd(String schoolcd){
		this.schoolcd = schoolcd;
	}

	public void setClassNum(String classnum){
		this.classnum = classnum;
	}

}
