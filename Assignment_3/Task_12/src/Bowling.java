import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Bowling {
    private final Queue<Track> tracks;
    private final Queue<PairOfShoes> shoesShelf;

    public Bowling(int tracksNumber) {
        tracks = new ArrayDeque<>();
        shoesShelf = new ArrayDeque<>();
        for (int i = 1; i <= tracksNumber; i++) {
            tracks.add(new Track(i));
        }
        for (int i = 38; i <= 45; i++) {
            for (int j = 0; j < 9; j++) {
                shoesShelf.add(new PairOfShoes(i));
            }
        }
    }

    public synchronized Track acquireTrack() {
        Track track = tracks.peek();
        if (track == null){
            return null;
        }
        tracks.poll();
        track.setPrice(100 - tracks.size() * 10);
        return track;
    }

    public synchronized void releaseTrack(Track track) {
        System.out.printf("C дорожки №%d сняли бронь\n", track.getNumber());
        tracks.add(track);
    }

    public synchronized Set<PairOfShoes> acquireShoes(int number) {
        Set<PairOfShoes> shoes = new HashSet<>();
        if (number > shoesShelf.size()){
            return null;
        }
        for (int i = 0; i < number; i++) {
            shoes.add(shoesShelf.peek());
            shoesShelf.poll();
        }
        return shoes;
    }

    public synchronized void releaseShoes(Set<PairOfShoes> shoes) {
        System.out.printf("В гардероб вернули %d пар обуви\n", shoes.size());
        shoesShelf.addAll(shoes);
    }
}


