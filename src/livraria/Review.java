package livraria;


import livraria.Reviewer;

public class Review {
    String text;
    Reviewer reviewer;

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }    
}
