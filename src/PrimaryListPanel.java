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
        items = null;
    }

    public void setItemPanel(ItemPanel ip)
    {
        theItemPanel = ip;
    }

    @Override
    public void handleShiftUp(int index)
    {

    }

    @Override
    public void handleShiftDown(int index)
    {

    }

    @Override
    public void handleAdd()
    {

    }

    @Override
    public void handleRemove(int index)
    {

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
