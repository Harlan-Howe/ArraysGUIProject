import javax.swing.*;
import java.util.List;

public class ItemPanel extends JPanel implements PanelManager
{
    private ListSubPanel<Detail> detailList;
    private Item currentItem;
    private DetailPanel theDetailPanel;

    public ItemPanel()
    {
        super();
        detailList = new ListSubPanel<Detail>(this);
        currentItem = null;
    }

    public void setDetailPanel(DetailPanel dp)
    {
        theDetailPanel = dp;
    }

    @Override
    public void handleShiftUp(int index)
    {
        System.out.println(STR."User would like to shift the Detail at \{index} up.");
    }

    @Override
    public void handleShiftDown(int index)
    {
        System.out.println(STR."User would like to shift the Detail at \{index} down.");
    }

    @Override
    public void handleAdd()
    {
        System.out.println("User would like to add another Detail.");
    }

    @Override
    public void handleRemove(int index)
    {
        System.out.println(STR."User would like to remove the item at \{index}.");
    }

    @Override
    public Object[] getListData()
    {
        return currentItem.getDetails();
    }
}
