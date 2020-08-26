package library.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import library.model.dto.Book;
public class BookModelDB {
	private static BookModelDB instance = new BookModelDB();
	private ArrayList<Book> remainBookL = new ArrayList<Book>();
	private ArrayList<Book> rentBookL = new ArrayList<Book>();
	private ArrayList<ArrayList<Book>> allBookL = new ArrayList<>();

	BufferedReader in=null;
	String data=null;
	
	//정제된 데이터가 들어올 배열 변수를 선언한 것
	String[] splitData = null;
	
  //싱글톤
  public static BookModelDB getInstance(){
    return instance;
  }

	//읽어서 도서관 소장 리스트에 추가
	//bookList3에 list1,list2 추가
	/*
	 * 1. 데이터를 정제
	 * 2. str 공간에 넣은 것이고
	 * 3. remainBookL 에다가 남은 도서 정보 넣기
	 * 4. rentBookL - 0으로 생각
	 * 5. allBookL에 넣기 = reamin  + rent

   * 싱글톤이여서 getInstance 생성
	 */
	private BookModelDB() {
		//FileReader로 이용
		try {
			in = new BufferedReader(new FileReader("library_data.csv"));
			while((data=in.readLine()) != null) { //책을 하나 생성
				splitData = data.split(",");
        remainBookL.add(new Book(splitData[0],splitData[1],splitData[2],splitData[3],splitData[5], true)); 
        //splitData가 하나의 객체라고 생각하면 되는데, Book에서 필요한 파라미터는 5개, 그 하나하나를 구분해서 넣어줘야지 하나로 인식이 가능.
        //ture : 미대여/ false : 대여
			}

      //전체 도서 = 대여x + 대여o 
      //allBookL에 추가된 것이다.
      allBookL.add(remainBookL);
      allBookL.add(rentBookL);
      
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) { // null이 아닌 경우에만 close 즉 자원 반환 필수
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//도서관 대여 중이 아닌 리스트 반환
	public ArrayList<Book> getRemainBookList(){
		return remainBookL;
	}
	
	//대여중 리스트 반환
	public ArrayList<Book> getRentList(){
		return rentBookL;
	}
	
	//전체리스트 반환
	public ArrayList<ArrayList<Book>> getAllBookList(){
		return allBookL;
	}
}

