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
    private String title, subtitle; // TODO: edit this list to reflect the design of your Category.

    /**
     * default constructor - makes a new Category object with default attributes and one default Item.
     */
    public Category()
    {
        items = new Item[1];
        items[0] = new Item();
        // TODO: set default values for the attributes of your category.
        // you'll probably replace the following:
        title = "Generic";
        subtitle = "No info yet.";
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
        System.out.println(STR."I'm constructing a Category and need to turn '\{descriptionInfoToParse} into the variables for the Category class.");
        System.out.println(STR."And I need to make 'items' be an array of \{numItems} null values.");
    }

    /**
     * place the given item into the "items" list at the given "index."
     * @param it - the item to put into "items"
     * @param index - the location it should go, in range [0, items.length)
     */
    public void setItem(Item it, int index)
    {
        // TODO: write this code for setItem, putting "it" into the items array at position 'index.'
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
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    /**
     * a string description of this Category instance; this is what determines how this category is represented in the
     * list in the first panel. It does not have to include all the information from all the attributes.
     * @return - a string description of this instance, possibly partial or abbreviated.
     */
    public String toString()
    {
        // TODO: you write code for toString, based on _your_ attributes and preferences.
        // you do not have to use all of them... just what you want to appear in the list of Categories.
        return title;
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
        // TODO: you write this code for getSaveString.


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
        // TODO: you write this code for handleShiftUp.
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
        // TODO: you write this code for handleShiftDown.
        //This should NOT necessitate remaking "items" -- just editing it.
    }

    /**
     * responds to the request from the user to add an item to the end of the items list.
     */
    @Override
    public void handleAdd()
    {
        System.out.println("User would like to add another Item.");
        // Because this changes the size of the array, this method should replace items with a new, slightly larger,
        // array AFTER copying the data from the old items first.
        // TODO: write this code for handleAdd().
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
        // Because this changes the size of the array, this method should replace items with a new, slightly smaller,
        // array AFTER copying the data from the old items first.
        // TODO: write this code for handleRemove().
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
