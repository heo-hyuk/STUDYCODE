package 콘솔게시판.model;

public class Post {
    private int id;
    private String title;
    private String content;
    private String author; // 작성자(아이디)

    public Post(int id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getAuthor() { return author; }

    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
}
