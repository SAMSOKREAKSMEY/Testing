package Testing.Book;

public class Loanbook1 {

	private String user;
    private String password;
    private String title;
    private String author;
    private String year;
    private String loand;
    private String cate;
    private String bookid;

    public Loanbook1 (String Id, String username, String password, String title ,String author,String year, String loandate, String category){
        this.bookid = Id;
        this.user = username;
        this.password = password;
    	this.title=title;
        this.author = author;
        this.year = year;
        this.loand = loandate;
        this.cate = category;

    }
    public String getId(){
        return bookid;
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
    
}
