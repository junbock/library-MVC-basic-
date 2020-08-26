/** 
 * PROJECT  : 재능기부 프로젝트
 * NAME  :  StartView.java
 * DESC  : 실행 클래스
 * 
 * @author  
 * @version 1.0
*/

package library.view;

import library.controller.LibraryController;
import library.model.dto.Book;

public class StartView {

	public static void main(String[] args) {
		LibraryController controller = LibraryController.getInstance();
		
		System.out.println("\n---------------------------------------------------");
		System.out.println("select : 검색하기");
		System.out.println("---------------------------------------------------");
		System.out.println("전체 도서 검색");
		controller.searchAll();
		System.out.println("---------------------------------------------------");
		System.out.println("일부 도서명을 입력하여 검색하기");
		controller.searchBooks("우");
		System.out.println("---------------------------------------------------");
		System.out.println("등록번호를 입력하여 출력하기");
		controller.printBookInfo("AP0000000005");

		
		System.out.println("\n---------------------------------------------------");
		System.out.println("rent : 대여하기");
		System.out.println("---------------------------------------------------");
		System.out.println("대여전");
		controller.remainList();
		System.out.println("---------------------------------------------------");
		System.out.println("대여수행");
		controller.rentBook("AP0000000025");
		System.out.println("---------------------------------------------------");
		System.out.println("대여후");
		controller.remainList();
		
		

		System.out.println("\n---------------------------------------------------");
		System.out.println("return : 반납하기");
		System.out.println("---------------------------------------------------");
		System.out.println("반납전");
		controller.rentList();
		System.out.println("---------------------------------------------------");
		System.out.println("반납수행");
		controller.returnBook("AP0000000025");
		System.out.println("---------------------------------------------------");
		System.out.println("반납후");
		controller.rentList();
		
		
		System.out.println("\n---------------------------------------------------");
		System.out.println("insert");
		System.out.println("---------------------------------------------------");
		System.out.println("등록번호 입력전");
		controller.printBookInfo("AP0000014243");
		System.out.println("---------------------------------------------------");
		System.out.println("insert : 새로운 값 입력");
		Book newBook = new Book ("AP0000014243","나는 그릴 수 있어요()","웅진미디어 편집부","웅진미디어","747-어298-", true);
		controller.insertBook(newBook);
		System.out.println("---------------------------------------------------");
		System.out.println("등록번호 입력후");
		controller.printBookInfo("AP0000014243");
		System.out.println("---------------------------------------------------");
		System.out.println("등록번호 중복 입력시");
		newBook = new Book ("AP0000014243","나는 그릴 수 있어요()","웅진미디어 편집부","웅진미디어","747-어298-", true);
		controller.insertBook(newBook);
		

		System.out.println("\n---------------------------------------------------");
		System.out.println("update");
		System.out.println("---------------------------------------------------");
		System.out.println("등록번호 수정전");
		controller.printBookInfo("AP0000000013");
		System.out.println("---------------------------------------------------");
		System.out.println("update : 일부 수정");
		controller.updateBook("AP0000000013", "720-가가가가가"); //원래 : 747-리63ㅋ- 
		System.out.println("---------------------------------------------------");
		System.out.println("등록번호 수정후");
		controller.printBookInfo("AP0000000013");
		
		
		System.out.println("\n---------------------------------------------------");
		System.out.println("delete & lost");
		System.out.println("---------------------------------------------------");
		System.out.println("삭제 전 정보");
		controller.printBookInfo("AP0000000028");
		System.out.println("---------------------------------------------------");
		System.out.println("delete & lost : 등록번호를 입력하여 삭제 수행");
		controller.lostBook("AP0000000028");
		System.out.println("---------------------------------------------------");
		System.out.println("삭제 후 정보 찾기");
		controller.printBookInfo("AP0000000028");
		System.out.println("---------------------------------------------------");
		System.out.println("없는 번호를 삭제할 경우");
		controller.lostBook("AP0000000028");
	}
}
