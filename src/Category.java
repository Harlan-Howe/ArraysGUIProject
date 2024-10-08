public class Category
{
    private Detail[] details;
    private String title, subtitle;

    public Category()
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
