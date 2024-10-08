public class CategoryCollection implements PanelManager<Category>
{
    private Category[] categories;

    public CategoryCollection()
    {
        categories = new Category[1];
        categories[0] = new Category();
    }

    public Category getCategoryAtIndex(int index)
    {
        if (index == -1)
            return null;
        return categories[index];
    }

    public String getSaveString()
    {
        StringBuilder builder = new StringBuilder();
        int numCategories = categories.length;
        builder.append(numCategories);
        builder.append("\n");
        for (int i=0; i<numCategories; i++)
            builder.append(categories[i].getSaveString());
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
    public Category[] getListData()
    {
        return categories;
    }

}
