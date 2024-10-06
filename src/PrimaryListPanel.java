import javax.swing.*;

public class PrimaryListPanel extends JPanel implements PanelManager<Item>
{
    private ListSubPanel<Item> listGUI;
    private Item[] items;
    private ItemPanel theItemPanel;


    public PrimaryListPanel()
    {
        super();
        listGUI = new ListSubPanel<Item>(this);
        items = new Item[1];
        items[0] = new Item();
        buildGUI();
    }

    public void buildGUI()
    {

        add(listGUI);
    }

    public void setItemPanel(ItemPanel ip)
    {
        theItemPanel = ip;
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
    public Item[] getListData()
    {
        return new Item[0];
    }

    @Override
    public void handleUserPickedIndex(int index)
    {
        System.out.println(STR."The user just changed the selection in the list to #\{index}. I should probably do something about that.");
        // Recommended: tell the item panel to update with the selected item in the list.
    }
}
