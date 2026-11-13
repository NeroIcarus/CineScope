
package cinema.main.review;

import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private final List<Review> reviews = new ArrayList<>();
    private int nextId = 1;

    public Review save(Review review) {
        review.setId(nextId++);
        reviews.add(review);
        return review;
    }

    public List<Review> findByMovieTitle(String movieTitle) {
        List<Review> result = new ArrayList<>();

        for (Review review : reviews) {
            if (review.getMovieTitle()
                    .equalsIgnoreCase(movieTitle)) {
                result.add(review);
            }
        }

        return result;
    }

    public Review findById(int id) {
        for (Review review : reviews) {
            if (review.getId() == id) {
                return review;
            }
        }

        return null;
    }

    public boolean update(int id, String content) {
        Review review = findById(id);

        if (review == null) {
            return false;
        }

        review.setContent(content);
        return true;
    }

    public boolean delete(int id) {
        Review review = findById(id);

        if (review == null) {
            return false;
        }

        reviews.remove(review);
        return true;
    }
}
