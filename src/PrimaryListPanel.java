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
}
