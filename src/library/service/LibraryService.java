package library.service;

import java.util.ArrayList;

import library.exception.DuplicateException;
import library.exception.NotExistException;
import library.model.BookModelDB;
import library.model.dto.Book;

public class LibraryService {
	// 싱글톤 디자인
	// DB 싱글톤 인스턴스 받아오기
	private BookModelDB bookDB = BookModelDB.getInstance();
	private static LibraryService instance = new LibraryService();

	public static LibraryService getInstance() {
		return instance;
	}

	private LibraryService() {
	}

	// 전체 book list DB에 받아와서 반환
	public ArrayList<ArrayList<Book>> searchAll() {
		return bookDB.getAllBookList();
	}

  public ArrayList<Book> searchRentBook() {
		return bookDB.getRentList();
	}

  public ArrayList<Book> searchRemainBook() {
		return bookDB.getRemainBookList();
	}

	// array생성 새로운 공간을 생성
	// 전체 book list 대여중 for, 대여 x for -> string.비교 에서 검색 후
	// 생성된 array 그 book 추가
	// 인자로 받아온 책 이름과 비교 후 일부가 일치하면 생성한 array에 추가
	public ArrayList<Book> searchBooks(String bookName) {
		ArrayList<Book> data = new ArrayList<>();
		ArrayList<Book> remainData = bookDB.getRemainBookList();
		ArrayList<Book> rentData = bookDB.getRentList();

		// 대여중일 때 비교
		for (int i = 0; i < rentData.size(); i++) {
			if (rentData.get(i).getBookName().indexOf(bookName) != -1) {
				// EndView에서 처리하기
				data.add(rentData.get(i));
			}
		}

		// 대여가 아닐 때 비교
		for (int i = 0; i < remainData.size(); i++) {
			if (remainData.get(i).getBookName().indexOf(bookName) != -1) {
				// EndView에서 처리하기
				data.add(remainData.get(i));
			}
		}

		return data;
	}

	// 전체 book list에서 등록번호 검색, 일치하는 book 반환
	public Book getBook(String bookNum) {
		Book book = null;
		ArrayList<Book> remainData = bookDB.getRemainBookList();
		ArrayList<Book> rentData = bookDB.getRentList();
		// 대여 x
		for (int i = 0; i < remainData.size(); i++) {
			book = remainData.get(i);
			
			if (book.getBookNum().equals(bookNum)) {
				// EndView처리
				return book;
			}
		}

		// 대여 o
		for (int i = 0; i < rentData.size(); i++) {
			book = rentData.get(i);
			if (book.getBookNum().equals(bookNum)) {
				// EndView처리
				
				return book;
			}
		}
		return null;
	}

	// 도서관 보유중인 list에서 bookNum 찾아서 대여중인 list로 이동
	// 보유중 list에서 책없으면 exception
	public void rentBook(String bookNum) throws NotExistException {
		Book book = null;
		ArrayList<Book> remainData = bookDB.getRemainBookList();
		ArrayList<Book> rentData = bookDB.getRentList();

		for (int i = 0; i < remainData.size(); i++) {
			book = remainData.get(i);
			if (book.getBookNum().equals(bookNum)) {
				rentData.add(book);
				remainData.remove(book);
				book.setBookState(false);
				return;
			}
		}

		throw new NotExistException("이미 대여중이거나 존재하지 않는 도서입니다.");
	}

	// 대여중 list에서 bookNum 찾아서 도서관 보유 list에 이동
	// 대여중 list에서 책이 없으면 exception
	public void returnBook(String bookNum) throws NotExistException {
		Book book = null;
		ArrayList<Book> remainData = bookDB.getRemainBookList();
		ArrayList<Book> rentData = bookDB.getRentList();

		for (int i = 0; i < rentData.size(); i++) {
			book = rentData.get(i);
			if (book.getBookNum().equals(bookNum)) {
				remainData.add(book);
				rentData.remove(book);
				book.setBookState(true);
				return;
			}
		}
		throw new NotExistException("해당 도서는 분실했거나 존재하지 않습니다.");
	}

	// DB에 있는 3개의 list에서 bookNum찾아서 삭제
	// 없으면 exception
	public void lostBook(String bookNum) throws NotExistException {
    Book book = null; 
    ArrayList<Book> remainData = bookDB.getRemainBookList();
	ArrayList<Book> rentData = bookDB.getRentList();

    book = getBook(bookNum);
    if(book != null){
      if(remainData.contains(book)){
        remainData.remove(book);
      }else{
        rentData.remove(book);
      }
    }else{
      throw new NotExistException("해당 도서는 존재하지 않습니다.");
    }
  }

	// 책 중복 검사 bookNum으로 후 중복 아니면 추가
	// 중복 exception
	public void insertBook(Book newBook) throws DuplicateException{
    Book book = null; 
    ArrayList<Book> remainData = bookDB.getRemainBookList();
	ArrayList<Book> rentData = bookDB.getRentList();
    
    book = getBook(newBook.getBookNum());
    if(book == null){ //null이면 새로운 데이터를 받을 수 있다.
      remainData.add(newBook);
    }else{
      throw new DuplicateException("해당 등록번호는 존재합니다.");
    }
  }

	// 책 수정
	// 책의 위치 수정
	public void updateBook(String bookNum, String bookLocate) throws NotExistException {
    Book book = null;

    book = getBook(bookNum);
    if(book != null){
      book.setBookLocate(bookLocate);
    }else{
      throw new NotExistException("해당 도서는 존재하지 않습니다.");
    }
  }
}