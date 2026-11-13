
package cinema.main.review;

import java.time.LocalDateTime;

public class Review {

    private int id;
    private String movieTitle;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public Review() {
    }

    public Review(int id, String movieTitle,
                  String username, String content) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.username = username;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
