/** 
 * PROJECT  : ?��?��기�? ?��로젝?��
 * NAME  :  NotExistException.java
 * DESC  :  중복?��?�� 처리?�� ?��?��?�� ?��?�� ?��?�� ?��?��?��
 * 
 * @author  
 * @version 1.0
*/

package library.exception;

public class DuplicateException extends Exception{

	public DuplicateException() {}
	public DuplicateException(String message) {
		super(message);
	}
}
