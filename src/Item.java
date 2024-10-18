/**
 * this class represents an item, an example of something belonging to a category, consisting of several
 * attributes. It also is responsible for generating a tab-delimited description ot the attributes for the purposes
 * of saving, as well as interpreting a tab-delimited description of the attributes for a new Item.
 */
public class Item
{
    // TODO: edit this to reflect the design of your Item class.
    private String title, album;
    private int minutes, seconds;

    /**
     * default constructor
     */
    public Item()
    {
        // TODO: rewrite this to set the default values of new Item instances
        title = STR."Song #\{(int)(1000*Math.random())}"; // temp code: currently generating a randomized title.
        album = "None";
        minutes = 0;
        seconds = 0;
    }

    /**
     * overloatded constructor - makes a new Item object, initializing its values from the tab-delimited description of
     * them.
     * @param lineToDecode - a single-line, tab-delimited string holding the values to put into this Item's attributes.
     */
    public Item(String lineToDecode)
    {
        // TODO: parse the data in lineToDecode to fill in the variables for this class.
        String parts[] = lineToDecode.split("\t");
        title = parts[0];
        album = parts[1];
        minutes = Integer.parseInt(parts[2]);
        seconds = Integer.parseInt(parts[3]);
    }

    // TODO: write accessors and modifiers for your attributes, possibly replacing mine.


    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAlbum()
    {
        return album;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public int getSeconds()
    {
        return seconds;
    }

    public void setSeconds(int seconds)
    {
        this.seconds = seconds;
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
        return STR."\{title} (\{album})";
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
        return STR."\{title}\t\{album}\t\{minutes}\t\{seconds}\n";
    }
}
