package studentData;

public class StudentData {
	private int id;
	private String name;
	private String gender;
	private int [] score;
	
	public StudentData() {
		this(100000, "", "남", new int[4]);
	}
	
	public StudentData(int id) {
		this(id, "", "남", new int[4]);
		if(this.id > 999999 || this.id < 100000) {
			throw new InputInvalidRangeException("잘못된 학번을 입력하였습니다.");
		}
	}
	
	public StudentData(int id, String name, String gender, int [] score) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.score = new int[4];
		this.score[0] = score[0];
		this.score[1] = score[1];
		this.score[2] = score[2];
		this.score[3] = score[3];
		
		if(this.id > 999999 || this.id < 100000) {
			throw new InputInvalidRangeException("잘못된 학번을 입력하였습니다.");
		}
		if(!this.gender.equals("남") && !this.gender.equals("여")) {
			throw new InputWrongGenderException("성별을 잘못 입력하였습니다.");
		}
		for(int i = 0; i < 4; i++) {
			if(this.score[i] < 0 || this.score[i] > 100) {
				throw new InputInvalidRangeException("잘못된 점수를 입력하였습니다.");
			}
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public int[] getScore() {
		return this.score;
	}
	
}
