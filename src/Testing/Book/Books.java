package Testing.Book;

public class Books {

	private String id;
    private String title;
    private String author;
    private String year;
    private String category;

    public Books(String Id, String Title, String Author, String Year, String Category){
        this.id = Id;
    	this.title=Title;
        this.author = Author;
        this.year=Year;
        this.category=Category;
    }

    public String getId() {
    	return id;
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

    public String getCategory() {
        return category;
    }
}
