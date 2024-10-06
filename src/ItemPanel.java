import javax.swing.*;
import java.util.List;

public class ItemPanel extends JPanel
{
    private ListSubPanel detailList;
    private Item currentItem;
    private DetailPanel theDetailPanel;

    public ItemPanel()
    {
        super();
        detailList = new ListSubPanel();
        currentItem = null;
    }

    public void setDetailPanel(DetailPanel dp)
    {
        theDetailPanel = dp;
    }
}
