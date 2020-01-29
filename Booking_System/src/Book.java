public class Book{
	
	private String isbn; // ISBN 13 : 9780545010221
	private String title; // Harry Potter and the Deathly Hallows
	private String author; // J.K.Rowling
	private char gender; // F
	private String publisher; // Arthur A. Levine Books
	private double price; // 1.00$
	
	// constructor
	public Book(String publisher)
	{
		this.publisher = publisher;
	}
	public Book(double price)
	{
		this.price = price;
	}
	public Book(String author, char gender)
	{
		this.author = author;
		this.gender = gender;
	}
	
	public Book(String isbn, String title)
	{
		this.isbn=isbn;
		this.title = title;
	}
	public Book(String isbn,String title,String publisher) {
		this.isbn=isbn;
		this.title = title;
		this.publisher = publisher;
	}
	public Book(String isbn,String title,String author,String publisher) {
		this.isbn=isbn;
		this.title = title;
		this.publisher = publisher;
		this.author = author;
	}
	public Book(String isbn, String title, String author, char gender, String publisher){
		this.isbn=isbn;
		this.title = title;
		this.author = author;
		this.gender = gender;
		this.publisher = publisher;
	}
	
	
	public Book(String isbn, String title, String author, char gender, String publisher, double price){
		this.isbn=isbn;
		this.title = title;
		this.author = author;
		this.gender = gender;
		this.publisher = publisher;
		this.price = price;
	}
	
	// getters and setters method
	
	// isbn
	public void setIsbn(String isbn){
		this.isbn = isbn;
	}
	public String getIsbn(){
		return isbn;
	}
	// title
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
	// author
	public void setAuthor(String author){
		this.author = author;
	}
	public String getAuthor(){
		return author;
	}
	// gender
	public void setGender(char gender){
		this.gender = gender;
	}
	public char getGender(){
		return gender;
	}
	// publisher
	public void setPublisher(String publisher){
		this.publisher = publisher;
	}
	public String getPublisher(){
		return publisher;
	}
	// price
	public void setPrice(double price){
		this.price = price;
	}
	public double getPrice(){
		return price;
	}
}
