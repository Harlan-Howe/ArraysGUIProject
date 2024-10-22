/**
 * This class is the data source for the first panel -- it maintains an array of Category items, and is responsible
 * for manipulating the objects in that array, as requested by the GUI list in that panel. It also does the business
 * of generating the string representing the whole collection of data that will be sent to a saved file.
 */
public class CategoryCollection implements PanelManager<Category>
{
    private Category[] categories;

    public CategoryCollection()
    {
        // TODO: this code is here just so you have _something_ to see when the program runs. Consider making categories
        //     start as an empty list, once you have things rolling.
        categories = new Category[1];
        categories[0] = new Category();
    }

    /**
     * overloaded constructor that creates an array of the given length, but holding null values. The assumption is that
     * the categories will be filled via setCategory() very soon, before the next gui update.
     * (Used when loading a file full of categories.)
     * @param numCategories - the size of the array to generate.
     */
    public CategoryCollection(int numCategories)
    {
        //TODO: You write this overloaded constructor!
        System.out.println(STR."I need to make 'categories' become an array of \{numCategories} null values, to be filled in later."); // replace this line.
    }

    /**
     * accessor for a Category object stored in the array at the given location.
     * @param index - a number within the range of [-1, categories.length)
     * @return the value of the array at that index, or null, if index is -1.
     */
    public Category getCategoryAtIndex(int index)
    {
        if (index == -1)
            return null;
        return categories[index];
    }

    /**
     * places the given category into the categories list at the specified index. (Not an insert - does not move other categories.)
     * @param cat - the category object to add
     * @param index - the location within the list where the category should be placed, in range [0, categories.length).
     */
    public void setCategory(Category cat, int index)
    {
        categories[index] = cat;
    }

    /**
     * generates a single string representing all the categories and their data. The format should be:
     * line 0: the number of categories.
     * line 1: a tab-delimited string with the contents of the first category (other than the list of its items).
     * line 2: the number of items, n, belonging to this category.
     * lines 3 to n+2: tab-delimited strings with the contents of the first category's items, one line per item.
     * line n+3: a tab-delimited string with the contents of the second category (other than the list of its items).
     * etc.
     * @return the aformentioned string, representing all the data in this file.
     */
    public String getSaveString()
    {
        StringBuilder builder = new StringBuilder();
        //--------------------------------
        // TODO: you write this code for getSaveString().
        // Note that you can accomplish much of this by repeated calls to getSaveString() for each
        //       of the Category objects in "categories"

        //--------------------------------
        return builder.toString();
    }


    /**
     * responds to the request from the user to swap the category at location "index" with the one at location "index -1"
     * in categories.
     * @param index - the selected index in the list, or -1 if nothing is selected.
     */
    @Override
    public void handleShiftUp(int index)
    {
        System.out.println(STR."User would like to shift the Item at \{index} up.");
        // TODO: you write this code for handleShiftUp.
        //This should NOT necessitate remaking "categories" -- just editing it.

    }

    /**
     * responds to the request from the user to swap the category at location "index" with the one at location "index+1"
     * in categories.
     * @param index - the selected index in the list, or -1 if nothing is selected.
     */
    @Override
    public void handleShiftDown(int index)
    {
        System.out.println(STR."User would like to shift the Item at \{index} down.");
        // TODO: you write this code for handleShiftDown.
        //This should NOT necessitate remaking "categories" -- just editing it.

    }

    /**
     * responds to the request from the user ta add a category to the end of the categories list.
     */
    @Override
    public void handleAdd()
    {
        System.out.println("User would like to add another Item."); // replace this.
        // Because this changes the size of the array, this method should replace 'categories' with a new, slightly
        // larger, array AFTER copying the data from the old 'categories' first.
        // TODO: write this code for handleAdd().
    }

    /**
     * responds to the request from the user to remove the category at "index" and shift any later categories in to fill
     * the hole, thus reducing the size of the array.
     * @param index - the index of the item to remove, or -1 if there is no selection.
     */
    @Override
    public void handleRemove(int index)
    {
        System.out.println(STR."User would like to remove the item at \{index}."); // replace this.
        // Because this changes the size of the array, this method should replace 'categories' with a new, slightly
        // smaller, array AFTER copying the data from the old 'categories' first.
        // TODO: write this code for handleRemove().
    }

    /**
     * an accessor for the "categories" list, used to generate the entries in the GUI list.
     * @return - "categories"
     */
    @Override
    public Category[] getListData()
    {
        return categories;
    }

}
