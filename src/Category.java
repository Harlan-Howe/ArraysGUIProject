public class Category implements PanelManager<Item>
{
    private Item[] items;
    private String title, subtitle;

    public Category()
    {
        items = new Item[1];
        items[0] = new Item();
        title = "Generic";
        subtitle = "No info yet.";
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String s)
    {
        title = s;
    }

    public String toString()
    {
        return title;
    }

    @Override
    public void handleShiftUp(int index)
    {
        System.out.println(STR."User would like to shift the Item at \{index} up.");
    }

    @Override
    public void handleShiftDown(int index)
    {
        System.out.println(STR."User would like to shift the Item at \{index} down.");
    }

    @Override
    public void handleAdd()
    {
        System.out.println("User would like to add another Item.");
    }

    @Override
    public void handleRemove(int index)
    {
        System.out.println(STR."User would like to remove the item at \{index}.");
    }

    @Override
    public Item[] getListData()
    {
        return items;
    }

    public Item getItemAtIndex(int index)
    {
        if (index == -1)
            return null;
        return items[index];
    }
}
