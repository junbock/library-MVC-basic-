package library.controller;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import library.exception.DuplicateException;
import library.exception.NotExistException;
import library.model.dto.Book;
import library.service.LibraryService;
import library.view.EndView;
import library.view.FailView;

public class LibraryController {
	// 싱글톤디자인
	// service 객체 받아옴
	private static LibraryController instance = new LibraryController();
	private LibraryService service = LibraryService.getInstance();

	Logger logger = Logger.getRootLogger();
	
	public static LibraryController getInstance() {
		return instance;
	}

	// service클래스의 searchAll 메소드로 list받아서 endview에
	// 출력 요청
	public void searchAll() {
		ArrayList<ArrayList<Book>> bookData = service.searchAll();

		if (bookData.size() != 0) {
			for (ArrayList<Book> list : bookData) {
				EndView.bookListView(list);
			}
		} else {
			EndView.messageView("도서 정보가 없습니다.");
		}
	}

	// service 클래스 searchBooks 메소드로 list 받아서 endview
	// 출력 요청
	public void searchBooks(String bookName) {
		if (bookName != null) {
			ArrayList<Book> bookData = service.searchBooks(bookName);

			if (bookData.size() != 0) {
				EndView.bookListView(bookData);
			} else {
				EndView.messageView(bookName + " : 도서 정보가 없습니다.");
			}
		} else {
			EndView.messageView(bookName + " : 도서 정보가 없습니다.");
		}
	}

	// 번호를 입력 받아서 출력하는 것
	// service 클래스 getBook 메소드 book객체 받아서 endview 출력
	public void printBookInfo(String bookNum) {
		if (bookNum != null) {
			Book bookData = service.getBook(bookNum); // 받아오는 변수의 타입에 맞춰서 가져와야한다.
			if (bookData != null) {
				EndView.bookView(bookData);
			} else {
				EndView.messageView(bookNum + " : 도서 정보가 없습니다.");
			}
		} else {
			EndView.messageView(bookNum + " : 도서 정보가 없습니다.");
		}
	}

	// service 클래스 rentBook메소드 이용
	// 성공, 실패 endview에 출력
	public void rentBook(String bookNum) {
		if (bookNum != null) {
			try {
				service.rentBook(bookNum);
				logger.info(bookNum+" : "+service.getBook(bookNum).getBookName()+"이 정상적으로 대출이 되었습니다.");
				EndView.messageView(bookNum + " " + ": 정상적으로 대출이 되었습니다.");
			} catch (NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}
		} else {
			EndView.messageView(bookNum + " : 도서 정보가 없습니다.");
		}
	}

	// service 클래스 returnBook 메소드 이용
	// 성공, 실패 endview에 출력
	public void returnBook(String bookNum) {
		if (bookNum != null) {
			try {
				service.returnBook(bookNum);
				EndView.messageView(bookNum + " : 정상적으로 반납되었습니다.");
				logger.info(bookNum+" : "+service.getBook(bookNum).getBookName()+"이 정상적으로 반납이 되었습니다.");
			} catch (NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}
		} else {
			EndView.messageView(bookNum + " : 도서 정보가 없습니다.");
		}
	}

	// service 클래스 lostBook 메소드
	// 성공 실패 endview 출력
	public void lostBook(String bookNum) {
		Book book = null;
		String bookName = null;
		if (bookNum != null) {
			try {
				book = service.getBook(bookNum);
				if(book != null) {
					bookName = book.getBookName();
				}
				//String bookname = service.getBook(bookNum).getBookName();
				service.lostBook(bookNum);
				EndView.messageView(bookNum + " : 해당 책은 분실되었습니다.");
				logger.info(bookNum+" : "+ bookName +"이 분실되었습니다.");
			} catch (NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}
		} else {
			EndView.messageView(bookNum + " : 도서 정보가 없습니다.");
		}
	}

	// service 클래스 insertBook
	// 성공 실패 endview 출력
	public void insertBook(Book newBook) {
		if (newBook != null) {
			try {
				service.insertBook(newBook);
				logger.info(newBook.getBookNum()+" : "+ newBook.getBookName()+"이 새로운 도서가 추가되었습니다.");
			} catch (DuplicateException e) {
				FailView.failMessageView(e.getMessage());
			}
		} else {
			EndView.messageView(newBook.getBookNum() + " : 도서 정보가 없습니다.");
		}
	}

	// service 클래스 updateBook
	// locateBook 을 수정하기
	public void updateBook(String bookNum, String bookLocate) {
		if (bookNum != null && bookLocate != null) {
			try {
				service.updateBook(bookNum, bookLocate);
				EndView.messageView(bookNum + " : 수정되었습니다.");
				logger.info(bookNum+" : "+ service.getBook(bookNum).getBookName()+"이 수정되었습니다.");
			} catch (NotExistException e) {
				FailView.failMessageView(e.getMessage());
			}
		} else {
			EndView.messageView(bookNum + " : 도서 정보가 없습니다.");
		}
	}

	// 대여 리스트 확인
	public void rentList() {
		EndView.bookListView(service.searchRentBook());
	}

	// 대여가능 리스트 확인
	public void remainList() {
		EndView.bookListView(service.searchRemainBook());
	}

}