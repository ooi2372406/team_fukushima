package bean;

<<<<<<< HEAD
public class School {

	private String cd;
	private String name;

	public String getCd(){
		return cd;
	}

	public String getName(){
		return name;
	}

	public void setCd(String cd){
		this.cd = cd;
	}

	public void setName(String name){
		this.name = name;
	}

}
=======
import java.io.Serializable;

public class School implements Serializable {
	private String cd;
	private String name;
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
>>>>>>> c995e8e067d2fd545d64151ba84b6e9dda605cdb
