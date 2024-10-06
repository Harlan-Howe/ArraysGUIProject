import javax.swing.*;

public class ListSubPanel<ContentType> extends JPanel
{
    private PanelManager<ContentType> myParent;

    public ListSubPanel(PanelManager<ContentType> parent)
    {
        super();
        myParent = parent;
    }
}
