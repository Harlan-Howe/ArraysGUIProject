/**
 * this class represents an item, an example of something belonging to a category, consisting of several
 * attributes. It also is responsible for generating a tab-delimited description ot the attributes for the purposes
 * of saving, as well as interpreting a tab-delimited description of the attributes for a new Item.
 */
public class Item
{
    // TODO: edit this to reflect the design of your Item class.
    private String name;

    /**
     * default constructor
     */
    public Item()
    {
        // TODO: rewrite this to set the default values of new Item instances
        name = STR."Item #\{(int)(1000*Math.random())}"; // temp code: currently generating a randomized title.
    }

    /**
     * overloatded constructor - makes a new Item object, initializing its values from the tab-delimited description of
     * them.
     * @param lineToDecode - a single-line, tab-delimited string holding the values to put into this Item's attributes.
     */
    public Item(String lineToDecode)
    {
        // TODO: parse the data in lineToDecode to fill in the variables for this class.
        //Note: see the note in getSaveString(), below regarding tabs and carriage returns.
    }

    // TODO: write accessors and modifiers for your attributes, possibly replacing mine.
    public String getName()
    {
        return name;
    }

    public void setName(String s)
    {
        name = s;
    }

    /**
     * a string description of this Item instance; this is what determines how this category is displayed in the list
     * in the second panel. It does not have to include all the information from all the attributes.
     * @return - a string description of this instance, possibly partial or abbreviated.
     */
    @Override
    public String toString()
    {
        // TODO: you write this, based on your attributes and preferences.
        return name;
    }

    /**
     * generates a single-line, tab-delimited description of this Item that can be read by the overloaded
     * constructor to recreate this item later.
     * @return - a single-line string with tabs between each attribute.
     */
    public String getSaveString()
    {
        // TODO: you write this.
        //Note: if you are using a longer string, like the sort that is found in a TextArea, there might be tabs or line
        //      breaks in it. You will probably want to replace these with some other characters, or this will mess up
        //      the expectation that the fields are separated by tabs and this composes a single line.
        //      This time, you may make use of String's built-in replace() method.
        return STR."\{name}\n";
    }
}
