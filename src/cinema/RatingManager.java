
package cinema;

import java.util.HashMap;
import java.util.Map;

public class RatingManager {

    private static final String CURRENT_USER = "guest";

    private static final Map<String, Map<String, Integer>> ratings
            = new HashMap<>();

    private static String normalize(String judul) {
        return judul.trim().toLowerCase();
    }

    public static void beriRating(String judul, int nilai) {

        if (nilai < 1 || nilai > 5) {
            throw new IllegalArgumentException(
                    "Rating harus antara 1 sampai 5."
            );
        }

        String key = normalize(judul);

        ratings.putIfAbsent(key, new HashMap<>());

        // Satu pengguna hanya punya satu rating per film.
        // Jika memberi rating lagi, nilainya diperbarui.
        ratings.get(key).put(CURRENT_USER, nilai);
    }

    public static int getRatingPengguna(String judul) {

        Map<String, Integer> data = ratings.get(normalize(judul));

        if (data == null) {
            return 0;
        }

        return data.getOrDefault(CURRENT_USER, 0);
    }

    public static double getRataRataKomunitas(String judul) {

        Map<String, Integer> data = ratings.get(normalize(judul));

        if (data == null || data.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (int nilai : data.values()) {
            total += nilai;
        }

        return (double) total / data.size();
    }

    public static int getJumlahRatingKomunitas(String judul) {

        Map<String, Integer> data = ratings.get(normalize(judul));

        return data == null ? 0 : data.size();
    }

    public static String formatRatingKomunitas(String judul) {

        int jumlah = getJumlahRatingKomunitas(judul);

        if (jumlah == 0) {
            return "Belum ada rating";
        }

        return String.format(
                "%.1f / 5 (%d rating)",
                getRataRataKomunitas(judul),
                jumlah
        );
    }
}
