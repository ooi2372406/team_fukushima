package bean;
public class Test {
    private String studentNo;
    private String subjectCd;
    private String schoolCd;
    private int no;
    private Integer point;
    private String classNum;

    // コンストラクタ
    public Test(String classNum, String studentNo, String subjectCd, String schoolCd, int no, Integer point) {
        this.studentNo = studentNo;
        this.subjectCd = subjectCd;
        this.schoolCd = schoolCd;
        this.no = no;
        this.point = point;
        this.classNum = classNum;
    }

    public Test() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	// ゲッターとセッター
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

	public String getStudentNumber() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public int getScore() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	public int getId() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	public void setId(int int1) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setStudentNumber(String string) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setName(String string) {
		// TODO 自動生成されたメソッド・スタブ

	}

	public void setScore(int int1) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
