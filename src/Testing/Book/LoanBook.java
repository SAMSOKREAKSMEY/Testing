package Testing.Book;

public class LoanBook {

	private String user;
    private String password;
    private String title;
    private String author;
    private String year;
    private String loand;
    private String cate;
    private String bookid;

    public LoanBook(String username, String password, String title ,String author,String year, String loandate, String category, String id){
        this.user = username;
        this.password = password;
    	this.title=title;
        this.author = author;
        this.year = year;
        this.loand = loandate;
        this.cate = category;
        this.bookid = id;


    }

    public String getUsername() {
    	return user;
    }
    public String getPassword(){
        return password;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getYear() {
        return year;
    }
    public String getLoandate() {
        return loand;
    }
    public String getCategory(){
        return cate;
    }
    public String getBookid(){
        return bookid;
    }
}
