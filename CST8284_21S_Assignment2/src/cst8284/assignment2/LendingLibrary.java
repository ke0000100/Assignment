package cst8284.assignment2;
import java.util.ArrayList;

/**
 * Class Name: CST8284_21S_301
 * Author Name: Jie Ke
 * Professor Name: Samira Ouaaz
 * Date: July 16, 2021
 * Class Name:LendingLibrary
 * Description: This program solution the Assignment2 task. 
 */
/**
 * This class is the The add/find methods process the addition 
 * and searching of items in the corresponding arrays. 
 */
public class LendingLibrary {

	private static final int MAX_LOAN_PER_USER = 2;

	private ArrayList<Book> bookReg = new ArrayList<Book>();
	private ArrayList<User> userReg = new ArrayList<User>();
	private ArrayList<BookLoan> loanReg = new ArrayList<BookLoan>();

	public LendingLibrary() {

	}

	//add the user in the corresponding position
	public boolean addUser(User u) {

		for(int i = 0; i<userReg.size(); i++) {

			if(u.getFirstName()==userReg.get(i).getFirstName()
					&& u.getLastName()==userReg.get(i).getLastName()){
				return false;
			}
		}
		userReg.add(u);
		return true;
	}
	//add the book in the corresponding position
	public boolean addBook(Book b) {

		for(int i = 0; i<bookReg.size(); i++) {

			if(b.getIsbnNumber()==bookReg.get(i).getIsbnNumber()) {
				return false;
			}
		}
		bookReg.add(b);
		return true;
	}
	//Add the loan in the corresponding position
	public boolean addLoan(BookLoan l) {

		for(int i = 0; i<loanReg.size(); i++) {
			if(l.getBook() == null) {
				break;
			}	
			if(l.getUser() == null) {
				break;
			}
			String bookIsbn = l.getBook().getIsbnNumber();
			String loanIsbn = loanReg.get(i).getBook().getIsbnNumber();
			String userFirst = l.getUser().getFirstName();
			String userLast = l.getUser().getLastName();
			String loanFirst = loanReg.get(i).getUser().getFirstName();
			String loanLast = loanReg.get(i).getUser().getLastName();

			if(bookIsbn == loanIsbn && userFirst == loanFirst 
					&& userLast == loanLast ) {
				return false;
			}
		}
		loanReg.add(l);
		return true;
	}	
	//search a added user based on name
	public User findUser(String firstName, String lastName){

		for(int i = 0; i<userReg.size(); i++) {

			if (userReg == null)
				break;
			if ((firstName.equals(userReg.get(i).getFirstName())) 
					&& (lastName.equals(userReg.get(i).getLastName())))
				return userReg.get(i);
		}
		return null;
	}
	//search a added book based on ISBN
	public Book findBook(String isbnNumber) {

		for(int i = 0; i<bookReg.size(); i++) {
			if (bookReg == null) 
				break;

			if (isbnNumber.equals(bookReg.get(i).getIsbnNumber()))
				return bookReg.get(i);

		}
		return null;
	}	
	//Search a loan
	public BookLoan findLoan(String isbnNumber) {

		for(int i = 0; i<loanReg.size(); i++) {

			if (loanReg == null)
				break;
			if (isbnNumber.equals(loanReg.get(i).getBook().getIsbnNumber()))
				return loanReg.get(i);
		}
		return null;
	}
	//delete a user
	public boolean deleteUser(String firstName, String lastName) {
		int index = -1;
		for(int i = 0; i<userReg.size(); i++) {
			if(userReg == null) {
				break;
			}
			if(userReg.get(i).getFirstName().equals(firstName)
					&& userReg.get(i).getLastName().equals(lastName) ) {
				index = i;
				for(int j = 0; j<loanReg.size(); j++) {
					if(loanReg == null) {
						index = i;
						break;
					}
					if(!(firstName.equals(loanReg.get(j).getUser().getFirstName()))
							|| (!(lastName.equals(loanReg.get(j).getUser().getLastName())))){
						index = i;
						break;
					}
					return false;
				}
			}
		}
		userReg.remove(index);
		return true;
	}
	public boolean deleteBook(String isbnNumber) {
		int index = -1;
		for(int i = 0; i<bookReg.size(); i++) {
			if(bookReg == null) {
				break;
			}
			if(isbnNumber.equals(bookReg.get(i).getBook().getIsbnNumber())){
				index = i;
				for(int j = 0; j<loanReg.size(); j++) {
					if(loanReg == null) {
						index = i;
						break;
					}
					if(!(isbnNumber.equals(loanReg.get(j).getBook().getIsbnNumber()))){
						index = i;
						break;
					}
					return false;
				}
			}
		} 
		bookReg.remove(index);
		return true;
	}

	public boolean deleteLoan(String isbnNumber) {
		int index = -1;
		for(int i = 0; i<loanReg.size(); i++) {
			if(loanReg == null) {
				break;
			}
			if(!(loanReg.get(i).getBook().getIsbnNumber().equals(isbnNumber))){
				i++;
			}
			if(loanReg.get(i).getBook().getIsbnNumber().equals(isbnNumber)){
				index = i;
				break;
			}else {
				return false;
			}
		}
		loanReg.remove(index);
		return true;
	}
	//Verified that the user has not borrowed more than 2 books.
	public boolean userCanBorrow(User u) {
		int index = 0;
		for (int i = 0; i<loanReg.size(); i++) {
			if(loanReg == null) 
				break;
			if(loanReg.get(i).getUser().getId() == u .getId()) {
				index++;
			}
		}				
		if(index >= MAX_LOAN_PER_USER) {	
			return false;  
		}else {
			return true;  	
		}
	}
	//Verified if the book is loaned.
	public boolean isBookLoaned(Book b) {
		for(int i = 0; i<loanReg.size(); i++) {
			if(loanReg == null)
				break;
			if((loanReg.get(i).getBook().getIsbnNumber())
					.equals(b.getIsbnNumber())){
				return true;  
			}
		}	
		return false;
	}
	public ArrayList<Book> getBookReg() {
		return bookReg;
	}
	public void setBookReg(ArrayList<Book> bookReg) {
		this.bookReg = bookReg;
	}
	public ArrayList<User> getUserReg() {
		return userReg;
	}
	public void setUserReg(ArrayList<User> userReg) {
		this.userReg = userReg;
	}
	public ArrayList<BookLoan> getLoanReg() {
		return loanReg;
	}
	public void setLoanReg(ArrayList<BookLoan> loanReg) {
		this.loanReg = loanReg;
	}

}
