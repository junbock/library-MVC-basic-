package library.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book {
	// 등록번호
	private String bookNum;
	// 서명
	private String bookName;
	// 저자
	private String authorName;
	// 발행자
	private String publicName;
	// 청구기호
	private String bookLocate;
	// true 미대여, false 대여
	private boolean bookState;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("등록번호: ");
		builder.append(bookNum);
		builder.append(", 서명: ");
		builder.append(bookName);
		builder.append(", 저자: ");
		builder.append(authorName);
		builder.append(", 발행자: ");
		builder.append(publicName);
		builder.append(", 청구기호: ");
		builder.append(bookLocate);
		if (bookState) {
			builder.append(", 대여가능");
		} else {
			builder.append(", 대여중");
		}

		return builder.toString();
	}

}
