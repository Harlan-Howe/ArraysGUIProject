public class Item
{
    private Detail[] details;
    private String title, subtitle;

    public Item()
    {
        details = null;
        title = "Generic";
        subtitle = "No info yet.";
    }

    public Detail[] getDetails()
    {
        return details;
    }

    public String toString()
    {
        return title;
    }
}
