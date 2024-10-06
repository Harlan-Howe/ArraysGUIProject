import javax.swing.*;
import java.util.List;

public class ItemPanel extends JPanel implements PanelManager<Detail>
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
    public Detail[] getListData()
    {
        if (currentItem == null)
            return new Detail[0];
        return currentItem.getDetails();
    }

    @Override
    public void handleUserPickedIndex(int index)
    {
        System.out.println(STR."Hey, the user just picked a different item, #\{index} on the list. I should do something about that.");
        // Recommended: tell the detail panel to update with the detail the user just picked.
    }
}
