package service;

import domain.publications.Publication;
import repository.PublicationRepository;

import java.util.ArrayList;

public abstract class UserService {
    private int chosenPubId;
    private final PublicationRepository publications;
//    public final IssueService issueService;

    public UserService(PublicationRepository publications) {
        this.publications = publications;
    }

    public PublicationRepository getPublications() {
        return publications;
    }

    public boolean getPublication(String query, PublicationRepository publications) {
        try {
            int index = Integer.parseInt(query);
            if (publications.hasId(index)) {
                setChosenPubId(index);
                System.out.println("Chosen publication: " + publications.getByID(index));
                return true;
            }
            System.out.println("Could not get publication with ID: " + index);
            return false;
        } catch (NumberFormatException e) {
            ArrayList<Publication> publications1 = publications.find(query);
            if (publications1.isEmpty()) {
                System.out.println("Could not find a publication with such a title: " + query);
                return false;
            } else if (publications1.size() != 1) {
                System.out.println("Found several publications with such a title: " + query);
                for (Publication publication : publications1) {
                    System.out.println(publication);
                }
                return false;
            }
            setChosenPubId(publications1.getFirst().getId());
            System.out.println("Chosen publication: " + publications1.getFirst());
            return true;
        }
    }

    public int getChosenPubId() {
        return chosenPubId;
    }

    public void setChosenPubId(int chosenPubId) {
        this.chosenPubId = chosenPubId;
    }
}
