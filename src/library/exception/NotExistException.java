/** 
 * PROJECT  : ?��?��기�? ?��로젝?��
 * NAME  :  NotExistException.java
 * DESC  :  �??�� ?��?��?���? ?��?�� 경우 발생?��?��?�� ?��?��?�� ?��?�� ?��?�� ?��?��?��
 * 
 * @author  
 * @version 1.0
*/
package library.exception;

public class NotExistException extends Exception{
	public NotExistException(){}
	public NotExistException(String message){
		super(message);
	}
}
