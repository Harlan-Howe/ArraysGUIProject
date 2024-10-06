import javax.swing.*;

public class PrimaryListPanel extends JPanel
{
    private ListSubPanel listGUI;
    private Item[] items;
    private ItemPanel theItemPanel;


    public PrimaryListPanel()
    {
        super();
        listGUI = new ListSubPanel();
        items = null;
    }

    public void setItemPanel(ItemPanel ip)
    {
        theItemPanel = ip;
    }
}
