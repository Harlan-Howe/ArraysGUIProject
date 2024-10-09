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

    public Category(String descriptionInfoToParse, int numItems)
    {
        items = new Item[numItems];
        System.out.println(STR."I'm constructing a Category and need to turn '\{descriptionInfoToParse} into the variables for the Category class.");
    }

    public void setItem(Item it, int index)
    {
        // TODO: put "it" into the items array at position 'index.'
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

    public String getSaveString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(STR."\{title}\t\{subtitle}\n");
        builder.append(STR."\{items.length}\n");
        for (int i=0; i<items.length; i++)
            builder.append(items[i].getSaveString());
        return builder.toString();
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
