package studentData;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentDataTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int mainMenu, menu, id, saveGender, count;
		String name, trashcan, gender;
		int [] score = new int[4];
		StudentDataManager manager = new StudentDataManager();

		do {
			System.out.printf("%n### 학생 성적 관리 프로그램 ### %n%n1. 학생 정보 입력%n2. 학생 정보 조회%n3. 성적 변경%n4. 학생 정보 삭제%n5. 프로그램 종료%n>>>");
			try {
				mainMenu = sc.nextInt();
			} catch(InputMismatchException e) {
				trashcan = sc.nextLine();
				System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
				mainMenu = -1;
				continue;
			}
			if(mainMenu == 5) {
				break;
			}

			switch(mainMenu) {
			case 1:
				do {
					System.out.print("\n[ 학생 정보 입력 ]\n[0] 메인메뉴로 돌아가기\n[1] 학생 정보 입력하기\n>>>");
					try {
						menu = sc.nextInt();
					} catch(InputMismatchException e) {
						trashcan = sc.nextLine();
						System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
						menu = -1;
					}
					if(menu == 0) {
						continue;
					}
					if(menu == 1) {
						try {
							System.out.print("\n6자리 학번을 입력하세요.: ");
							id = sc.nextInt();
							System.out.print("이름을 입력하세요.: ");
							name = sc.next();
							System.out.print("성별을 선택하세요.\n[1] 남, [2] 여 >>> ");
							saveGender = sc.nextInt();
							if (saveGender == 1) {
								gender = "남";
							}
							else if (saveGender == 2) {
								gender = "여";
							}
							else {
								gender = " ";
							}
							System.out.print("국어 점수를 입력하세요.(0~100): ");
							score[0] = sc.nextInt();
							System.out.print("영어 점수를 입력하세요.(0~100): ");
							score[1] = sc.nextInt();
							System.out.print("수학 점수를 입력하세요.(0~100): ");
							score[2] = sc.nextInt();
							System.out.print("과학 점수를 입력하세요.(0~100): ");
							score[3] = sc.nextInt();
						} catch(InputMismatchException e) {
							trashcan = sc.nextLine();
							System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
							continue;
						}
						try {
							count = manager.addList(id, name, gender, score);
						} catch(InputInvalidRangeException e1) {
							System.out.println(e1.getMessage());
							continue;
						} catch(InputWrongIdException e2) {
							System.out.println(e2.getMessage());
							continue;
						} catch(InputWrongGenderException e3) {
							System.out.println(e3.getMessage());
							continue;
						}
						System.out.println(count + "개의 학생 정보가 입력되었습니다.");
					}
					else {
						System.out.println("올바른 번호를 입력해주세요.");
					}
				} while(menu != 0);
				break;
			case 2:
				do {
					System.out.print("\n[ 학생 정보 조회 ]\n[0] 메인메뉴로 돌아가기\n[1] 전체 학생 조회 [2] 개별 학생 조회\n>>>");
					try {
						menu = sc.nextInt();
					} catch(InputMismatchException e) {
						trashcan = sc.nextLine();
						System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
						menu = -1;
						continue;
					}
					if(menu == 0) {
						continue;
					}
					if(menu == 1 || menu == 2) {
						switch(menu) {
						case 1: 
							try {
								System.out.print("전체 학생을 조회합니다.");
								try {
									manager.allStudent();
								} catch(InputInvalidRangeException e1) {
									System.out.println(e1.getMessage());
									continue;
								}
							} catch(InputMismatchException e) {
								trashcan = sc.nextLine();
								System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
								continue;
							}
							break;
						case 2: 
							System.out.print("\n조회할 학생의 학번을 입력하세요.:");
							try {
								id = sc.nextInt();
							} catch(InputMismatchException e) {
								trashcan = sc.nextLine();
								System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
								continue;
							}

							try {
								manager.selectedStudent(id);
							} catch (IndexOutOfBoundsException e) {
								System.out.println("잘못된 학번을 입력하였습니다.");
							}
						}
					}
					else {
						System.out.println("올바른 번호를 입력해주세요.");
					}
				}while(menu != 0);
				break;
			case 3:
				do {
					int subject, changeScore;
					System.out.print("\n[ 성적 변경 ]\n[0] 메인메뉴로 돌아가기\n[1] 성적 변경하기\n>>>");
					try {
						menu = sc.nextInt();
					} catch(InputMismatchException e) {
						System.out.print("잘못된 타입의 값을 입력하였습니다.\n");
						trashcan = sc.nextLine();
						menu = -1;
					}
					if(menu == 0) {
						continue;
					}
					if(menu == 1) {
						System.out.print("\n성적을 변경할 학생의 학번을 입력하세요.:");
						try {
							id = sc.nextInt();
						} catch(InputMismatchException e) {
							trashcan = sc.nextLine();
							System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
							id = -1;
							continue;

						}
						System.out.print("\n해당 학생의 정보입니다.\n");
						try {
							manager.selectedStudent(id);
						} catch (IndexOutOfBoundsException e) {
							System.out.println("잘못된 학번을 입력하였습니다.");
							continue;
						}
						System.out.print("\n성적을 변경할 과목을 입력하세요.\n[1] 국어, [2] 영어, [3] 수학, [4] 과학:");
						subject = sc.nextInt();

						System.out.print("몇 점으로 변경하시겠습니까?:");
						changeScore = sc.nextInt();

						try {
							count = manager.changeScore(id, subject, changeScore);
						} catch(InputInvalidRangeException e) {
							System.out.println(e.getMessage());
							continue;
						}
						System.out.println(count + "개의 학생 정보가 변경되었습니다.");
					}
					else {
						System.out.println("올바른 번호를 입력해주세요.");
					}
				}while(menu != 0);
				break;
			case 4:
				do {
					System.out.print("\n[ 학생 정보 삭제 ]\n[0] 메인메뉴로 돌아가기\n[1] 학생 정보 삭제하기.\n>>>");
					try {
						menu = sc.nextInt();
					} catch(InputMismatchException e) {
						trashcan = sc.nextLine();
						System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
						menu = -1;
					}
					if(menu == 0) {
						continue;
					}

					if(menu == 1) {
						System.out.print("\n삭제할 학생의 학번을 입력해주세요.:");
						try {
							id = sc.nextInt();
						} catch(InputMismatchException e) {
							trashcan = sc.nextLine();
							System.out.printf("잘못된 타입의 값 \"%s\" 을(를) 입력하였습니다.\n", trashcan);
							id = -1;
							continue;
						}
						System.out.print("해당 학생의 정보입니다.\n");
						try {
							manager.selectedStudent(id);
						} catch (IndexOutOfBoundsException e) {
							System.out.println("잘못된 학번을 입력하였습니다.");
							continue;
						}

						int yesOrNo;
						System.out.print("정말로 삭제하시겠습니까?\n[1] 삭제, [2] 취소\n>>>");
						yesOrNo = sc.nextInt();

						if(yesOrNo == 1) {
							count = manager.removeList(id);
							System.out.println(count + "개의 학생 정보가 삭제되었습니다.");
						}
						else if(yesOrNo == 2) {
							System.out.println("취소하였습니다.");
							continue;
						}
					}
					else {
						System.out.println("올바른 번호를 입력해주세요.");
					}

				}while(menu != 0);
				break;
			default : System.out.println("올바른 번호를 입력해주세요.");
			}
		} 
		while(mainMenu != 5);

		sc.close();
		System.out.println("프로그램을 종료합니다.");
		manager.closeConnection();
	}
}
