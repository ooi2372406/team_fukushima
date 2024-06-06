package bean;

public class Student implements java.io.Serializable{

	private String no;
	private int entyear;
	private String name;
	private String classnum;
	private boolean isAttend;
	private School school;

	public String getNo(){
		return no;
	}

	public int getEntYear(){
		return entyear;
	}

	public String getName(){
		return name;
	}

	public boolean getIsAttend(){
		return isAttend;
	}

	public String getClassNum(){
		return classnum;
	}

	public School getSchoolCd(){
		return school;
	}

	public void setEntYear(int entyear){
		this.entyear = entyear;
	}

	public void setClassNum(String classnum){
		this.classnum = classnum;
	}

	public void setIsAttend(boolean isattend){
		this.isAttend = isattend;
	}

	public void setSchool(School school){
		this.school = school;
	}

	public void setNo(String no){
		this.no = no;
	}

	public void setName(String name){
		this.name = name;
	}

}
