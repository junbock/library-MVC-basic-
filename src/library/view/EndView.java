package library.view;

import java.util.ArrayList;

import library.model.dto.Book;

public class EndView {

	// 책 정보 출력
	public static void bookView(Book book) {
		System.out.println(book);
	}

	// 받아온 리스트 안의 책 정보 출력
	public static void bookListView(ArrayList<Book> bookList) {
		for (Book book: bookList) {
			System.out.println(book);
		}
	}

	// 예외가 아닌 단순 메세지 출력
	public static void messageView(String message) {
		System.out.println(message);
	}

}