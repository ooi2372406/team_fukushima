package bean;

<<<<<<< HEAD
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
=======
import java.io.Serializable;

public class Student implements Serializable {
	private String no;
	private String name;
	private int entYear;
	private String classNum;
	private boolean isAttend;
	private School school;
	public String getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEntYear() {
		return entYear;
	}
	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public boolean isAttend() {
		return isAttend;
	}
	public void setAttend(boolean isAttend) {
		this.isAttend = isAttend;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
}
>>>>>>> c995e8e067d2fd545d64151ba84b6e9dda605cdb
