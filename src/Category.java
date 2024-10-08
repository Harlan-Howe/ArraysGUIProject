public class Category
{
    private Item[] items;
    private String title, subtitle;

    public Category()
    {
        items = null;
        title = "Generic";
        subtitle = "No info yet.";
    }

    public Item[] getDetails()
    {
        return items;
    }

    public String toString()
    {
        return title;
    }
}
