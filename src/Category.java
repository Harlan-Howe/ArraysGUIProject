/**
 * this class represents one category of the program you are writing, which includes several attributes and a list of Items.
 * it serves as the datasource for the GUI list in the middle panel, and is responsible for generating a string description
 * of this category and its items for the purposes of saving, as well as interpreting a tab-delimited string to fill in
 * the attributes for a new Category.
 */
public class Category implements PanelManager<Item>
{
    private Item[] items;
    // attributes
    private String artist; // TODO: edit this list to reflect the design of your Category.
    private int whichGenre;

    /**
     * default constructor - makes a new Category object with default attributes and one default Item.
     */
    public Category()
    {
        items = new Item[1];
        items[0] = new Item();
        // TODO: set default values for the attributes of your category.
        artist = "Generic";
        whichGenre = 0;
    }

    /**
     * overloaded constructor - makes a new Category object with an array large enough to hold "numItems" Items, all
     * initially null. Also interprets a tab-delimited String of values to fill the initial values of the attributes for
     * this Category.
     * @param descriptionInfoToParse - a single-line, tab-delimited string holding values to put into attributes.
     * @param numItems - the size of the array to build.
     */
    public Category(String descriptionInfoToParse, int numItems)
    {
        items = new Item[numItems];
        System.out.println(STR."I'm constructing a Category and need to turn '\{descriptionInfoToParse} into the variables for the Category class.");
        String[] parts = descriptionInfoToParse.split("\t");
        artist = parts[0];
        whichGenre = Integer.parseInt(parts[1]);
    }

    /**
     * place the given item into the "items" list at the given "index."
     * @param it - the item to put into "items"
     * @param index - the location it should go, in range [0, items.length)
     */
    public void setItem(Item it, int index)
    {
        // TODO: put "it" into the items array at position 'index.'
        items[index] = it;
    }

    /**
     * accessor for the item stored at a given location in the array.
     * @param index - a number within the range [-1, items.length)
     * @return - the item at the given index, or null, if "index" is -1.
     */
    public Item getItemAtIndex(int index)
    {
        if (index == -1)
            return null;
        return items[index];
    }

    // TODO: write accessors and modifiers for the attributes in your class, possibly replacing the ones here:
    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String s)
    {
        artist = s;
    }

    public int getWhichGenre()
    {
        return whichGenre;
    }

    public void setWhichGenre(int whichGenre)
    {
        this.whichGenre = whichGenre;
    }

    /**
     * a string description of this Category instance; this is what determines how this category is represented in the
     * list in the first panel. It does not have to include all the information from all the attributes.
     * @return - a string description of this instance, possibly partial or abbreviated.
     */
    public String toString()
    {
        // TODO: you write this, based on your attributes and preferences.
        return artist;
    }

    /**
     * generates a multiline description of this Category instance in a specific format:
     * line 0: a single-line, tab-delimited description of all the attributes for this class (in a format to be read by
     *         the overloaded constructor.)
     * line 1: The number of items, n, in this Category instance
     * lines 2 to n+1: one line each describing each item in "items," in tab-delimited form.
     * @return - the afformentioned string.
     */
    public String getSaveString()
    {
        StringBuilder builder = new StringBuilder();
        // ----------------------------
        // TODO: you write this.
        builder.append(STR."\{artist}\t\{whichGenre}\n");
        builder.append(STR."\{items.length}\n");
        for (int i=0; i<items.length; i++)
            builder.append(items[i].getSaveString());
        // ---------------------------
        return builder.toString();
    }

    /**
     * responds to the request from the user to swap the item at location "index" with the one at location "index-1"
     * in items.
     * @param index - the selected index in the list, or -1 if nothing is selected.
     */
    @Override
    public void handleShiftUp(int index)
    {
        System.out.println(STR."User would like to shift the Item at \{index} up.");
        // TODO: you write this.
        //This should NOT necessitate remaking "items" -- just editing it.
    }

    /**
     * responds to the request from the user to swap the item at location "index" with the one at location "index+1"
     * in items.
     * @param index - the selected index in the list, or -1 if nothing is selected.
     */
    @Override
    public void handleShiftDown(int index)
    {
        System.out.println(STR."User would like to shift the Item at \{index} down.");
        // TODO: you write this.
        //This should NOT necessitate remaking "items" -- just editing it.
    }

    /**
     * responds to the request from the user to add an item to the end of the items list.
     */
    @Override
    public void handleAdd()
    {
        System.out.println("User would like to add another Item.");
        // TODO: since this would change the size of the array, you will need to do the following:
        // a) create a new array of Items that is the correct size
        // b) copy over the existing data in "items" to the new array
        // c) make a new Item object and put it into the new array
        // d) update "items" to be this new array, replacing the old one.
    }

    /**
     * responds to the request from the user to remove the item at "index" and shift any later items in to fill the
     * hole, thus reducing the size of the array.
     * @param index - the index of the item to remove, or -1 if there is no selection.
     */
    @Override
    public void handleRemove(int index)
    {
        System.out.println(STR."User would like to remove the item at \{index}.");
        // TODO: since this would change the size of the array, you will need to do the following:
        // a) check that "index" is not -1 -- if it is, then cancel.
        // b) make a new array of Items that is the proper size
        // c) copy all the Item objects from the existing "items" array into this one -- except the one at "index."
        // d) update "items" to be this new array, replacing the old one.
    }

    /**
     * an accessor for the "lists" array, used to generate the entries for the GUI list.
     * @return
     */
    @Override
    public Item[] getListData()
    {
        return items;
    }


}
