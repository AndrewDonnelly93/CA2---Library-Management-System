package libmansys;

import libmansys.libItem.LibItem;
import libmansys.libItem.Media;
import java.util.List;

public class Author
{
    private String authorName;
    private List<LibItem> authoredItems;

    public Author() {
    }

    public Author(String authorName, List<LibItem> authoredItems) throws AuthorException {
        if (authorName.length() < 2 || authorName.length() > 30)
            throw new AuthorException("\n\tAuthor name should be between 2 and 30 characters");
        else
            this.authorName = authorName;
        this.authoredItems = authoredItems;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) throws AuthorException {
        if (authorName.length() < 2 || authorName.length() > 30)
            throw new AuthorException("\n\tAuthor name should be between 2 and 30 characters");
        else
            this.authorName = authorName;
    }

    public List<LibItem> getAuthoredItems() {
        return authoredItems;
    }

    public void setAuthoredItems(List<LibItem> authoredItems) {
        this.authoredItems = authoredItems;
    }

    public void addItem(LibItem item) throws AuthorException {
        if (item instanceof Media)
            throw new AuthorException("\n\tItem added should be a book or a thesis");
        else
            authoredItems.add(item);
    }

    public void removeItem(LibItem item) {
        authoredItems.remove(item);
    }

    public void printAuthorDetails (){
        System.out.println("\n\tAuthor Name: " + authorName);
        System.out.println("\n\tList of authored assets\n");
        authoredItems.forEach(LibItem::printItemDetails);
    }
}

