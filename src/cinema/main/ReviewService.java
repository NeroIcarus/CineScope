
package cinema.main.review;

import java.util.List;

public class ReviewService {

    private ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review tambahReview(
            String movieTitle,
            String username,
            String content) {

        if (movieTitle == null || movieTitle.trim().isEmpty()
                || username == null || username.trim().isEmpty()
                || content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Semua data review harus diisi."
            );
        }

        Review review = new Review(
            0,
            movieTitle.trim(),
            username.trim(),
            content.trim()
        );

        return repository.save(review);
    }

    public List<Review> tampilkanReview(String movieTitle) {
        return repository.findByMovieTitle(movieTitle);
    }

    public boolean editReview(int id, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Review tidak boleh kosong."
            );
        }

        return repository.update(id, content.trim());
    }

    public boolean hapusReview(int id) {
        return repository.delete(id);
    }
}
