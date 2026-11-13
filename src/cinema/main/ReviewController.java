
package cinema.main.review;

import java.util.List;

public class ReviewController {

    private ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    public Review tambahReview(
            String movieTitle,
            String username,
            String content) {

        return service.tambahReview(
            movieTitle, username, content
        );
    }

    public List<Review> tampilkanReview(String movieTitle) {
        return service.tampilkanReview(movieTitle);
    }

    public boolean editReview(int id, String content) {
        return service.editReview(id, content);
    }

    public boolean hapusReview(int id) {
        return service.hapusReview(id);
    }
}
