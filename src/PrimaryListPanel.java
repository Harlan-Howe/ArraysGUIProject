import javax.swing.*;

public class PrimaryListPanel extends JPanel implements PanelManager<Category>
{
    private ListSubPanel<Category> listGUI;
    private CategoryCollection categories;
    private CategoryPanel theCategoryPanel;


    public PrimaryListPanel()
    {
        super();
        categories = new CategoryCollection();
        listGUI = new ListSubPanel<Category>(this);

        buildGUI();
        listGUI.update();
        repaint();
    }

    public void buildGUI()
    {
        this.add(listGUI);
    }

    public void setCategoryPanel(CategoryPanel cp)
    {
        theCategoryPanel = cp;
    }

    @Override
    public void handleShiftUp(int index)
    {
        System.out.println(STR."User has requested we shift item \{index} up, towards start of list.");
    }

    @Override
    public void handleShiftDown(int index)
    {
        System.out.println(STR."User has requested we shift item \{index} down, towards bottom of list.");
    }

    @Override
    public void handleAdd()
    {
        System.out.println("User has requested we add another item.");
    }

    @Override
    public void handleRemove(int index)
    {
        System.out.println(STR."User has requested we remove item \{index}.");
    }

    @Override
    public Category[] getListData()
    {
        return categories.getCategories();
    }

    @Override
    public void handleUserPickedIndex(int index)
    {
        System.out.println(STR."The user just changed the selection in the list to #\{index}. I should probably do something about that.");
        // Recommended: tell the item panel to update with the selected item in the list.
    }
}
