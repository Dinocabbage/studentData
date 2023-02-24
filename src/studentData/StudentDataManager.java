package studentData;

import java.sql.*;
import java.util.ArrayList;

class StudentDataManager {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	StudentData x;

	StudentDataManager() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "scott";
		String pwd = "tiger";

		try {
			Class.forName(jdbc_driver);
			this.conn = DriverManager.getConnection(jdbc_url, user, pwd);
			conn.setAutoCommit(false);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	int addList(int id, String name, String gender, int [] score) {
		int rowCount = 0;
		this.sql = "insert into student_info values(?, ?, ?, ?, ?, ?, ?)";

		try {
			this.pstmt = conn.prepareStatement(this.sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, gender);
			pstmt.setInt(4, score[0]);
			pstmt.setInt(5, score[1]);
			pstmt.setInt(6, score[2]);
			pstmt.setInt(7, score[3]);

			rowCount = pstmt.executeUpdate();

			if(rowCount == 1) {
				this.conn.commit();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!this.pstmt.isClosed()) {
					this.pstmt.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}


	void selectedStudent(int id) {
		this.sql = "select * from student_info where student_id = ?";

		try {
			this.pstmt = conn.prepareStatement(sql);
			this.pstmt.setInt(1, id);

			this.rs = this.pstmt.executeQuery();

			if(this.rs.next()) {
				studentInfoHead();
				this.x = new StudentData(this.rs.getInt("STUDENT_ID"), this.rs.getString("STUDENT_NAME"),this.rs.getString("STUDENT_GENDER"),
						new int[] {this.rs.getInt("KOREAN_SCORE"), this.rs.getInt("ENGLISH_SCORE"), this.rs.getInt("MATH_SCORE"), this.rs.getInt("SCIENCE_SCORE")});
				studentInfo();
				studentInfoTail();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!this.pstmt.isClosed()) {
					this.pstmt.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	void allStudent() {
		this.sql = "select * from student_info";
		ArrayList<StudentData> studentList = new ArrayList<>();

		try {
			this.stmt = conn.createStatement();
			this.rs = this.stmt.executeQuery(sql);

			while(this.rs.next()) {
				studentList.add(new StudentData(this.rs.getInt("STUDENT_ID"), this.rs.getString("STUDENT_NAME"),this.rs.getString("STUDENT_GENDER"),
						new int[] {this.rs.getInt("KOREAN_SCORE"), this.rs.getInt("ENGLISH_SCORE"), this.rs.getInt("MATH_SCORE"), this.rs.getInt("SCIENCE_SCORE")}));
			}
			studentInfoHead();
			for(int i = 0; i < studentList.size(); i++) {
				this.x = studentList.get(i);
				studentInfo();
			}
			studentInfoTail();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!this.stmt.isClosed()) {
					this.stmt.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	int removeList(int id) {
		int rowCount = 0;

		try {
			this.sql = "delete from student_info where student_id = ?";

			this.pstmt = conn.prepareStatement(this.sql);
			pstmt.setInt(1, id);

			rowCount = pstmt.executeUpdate();

			if(rowCount == 1) {
				this.conn.commit();
			}
		}
		catch (SQLException e) {
			try {
				this.conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			try {
				if(!this.pstmt.isClosed()) {
					this.pstmt.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	int changeScore(int id, int subject, int score) {
		int rowCount = 0;
		String subjectName;
		
		switch(subject) {
		case 1: subjectName = "KOREAN_SCORE"; break;
		case 2: subjectName = "ENGLISH_SCORE"; break;
		case 3: subjectName = "MATH_SCORE"; break;
		case 4: subjectName = "SCIENCE_SCORE"; break;
		default : throw new InputInvalidRangeException("잘못된 과목을 입력하였습니다.");
		}
		
		try {
			this.stmt = conn.createStatement();
			this.sql = "update student_info set " + subjectName + " = " + score + " where student_id = " + id;
			rowCount = stmt.executeUpdate(this.sql);
			if(rowCount == 1) {
				this.conn.commit();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!this.stmt.isClosed()) {
					this.stmt.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}

	void studentInfoHead() {
		System.out.printf("\n%s%s", "[       인 적 사 항      ]","[        점  수        ]\n");
		System.out.printf("%5s%7s%7s%6s%5s%5s%5s\n","학 번", "이 름", "성 별", "국어", "영어", "수학", "과학");
		System.out.println("===============================================");
	}

	void studentInfo() {
		System.out.printf("%7s%6s%5s%8s%6s%6s%6s\n", this.x.getId(), this.x.getName(), this.x.getGender(), this.x.getScore()[0], this.x.getScore()[1], this.x.getScore()[2], this.x.getScore()[3]);
	}

	void studentInfoTail() {
		System.out.println("===============================================");
	}

	public void closeConnection() {
		try {
			if(!this.conn.isClosed()) {
				this.conn.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

