package service;

import domain.publications.Publication;
import repository.PublicationRepository;
import util.Pair;

import java.util.ArrayList;

public abstract class UserService {
    private int chosenPubId;
    protected final PublicationRepository publications;
    protected final IssueService issueService;

    public UserService(PublicationRepository publications, IssueService issueService) {
        this.publications = publications;
        this.issueService = issueService;
    }

    public PublicationRepository getPublicationRepository() {
        return publications;
    }

    public Pair getPublication(String query, PublicationRepository publications) {
        try {
            int index = Integer.parseInt(query);
            if (publications.hasId(index)) {
                setChosenPubId(index);
                return new Pair(true, "Chosen publication: " + publications.getByID(index));
            }
            return new Pair(false, "Could not get publication with ID: " + index);
        } catch (NumberFormatException e) {
            ArrayList<Publication> publications1 = publications.find(query);
            if (publications1.isEmpty()) {
                return new Pair(false, "Could not find a publication with such a title: " + query);
            } else if (publications1.size() != 1) {
                for (Publication publication : publications1) {
                    System.out.println(publication);
                }
                return new Pair(false, "Found several publications with such a title: " + query);
            }
            setChosenPubId(publications1.getFirst().getId());
            return new Pair(true, "Chosen publication: " + publications1.getFirst());
        }
    }

    public int getChosenPubId() {
        return chosenPubId;
    }

    public void setChosenPubId(int chosenPubId) {
        this.chosenPubId = chosenPubId;
    }
}
