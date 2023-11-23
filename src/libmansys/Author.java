package libmansys;

import libmansys.libItem.LibItem;
import libmansys.libItem.Media;

import java.util.ArrayList;

public class Author
{
    private String authorName;
    private ArrayList<LibItem> authoredItems;

    public Author() {
    }

    public Author(String AuthorName, ArrayList<LibItem> authoredItems) throws AuthorException {
        if (AuthorName.length() < 2 || AuthorName.length() > 30)
            throw new AuthorException("\n\tAuthor name should be between 2 and 30 characters");
        else
            this.authorName = AuthorName;
        this.authoredItems = authoredItems;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) throws AuthorException {
        if (this.authorName.length() < 2 || this.authorName.length() > 30)
            throw new AuthorException("\n\tAuthor name should be between 2 and 30 characters");
        else
            this.authorName = this.authorName;
    }

    public ArrayList<LibItem> getAuthoredItems() {
        return authoredItems;
    }

    public void setAuthoredItems(ArrayList<LibItem> authoredItems) {
        this.authoredItems = authoredItems;
    }

    public void addBook(LibItem item) throws AuthorException {
        if (item instanceof Media)
            throw new AuthorException("\n\tItem added should be a books or a thesis");
        else
            authoredItems.add(item);
    }

    public void removeBook(LibItem item) {
        authoredItems.remove(item);
    }
}

