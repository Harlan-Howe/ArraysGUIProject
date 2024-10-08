import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PrimaryListPanel extends JPanel implements ListSelectionListener
{
    private ListSubPanel<Category> listGUI;
    private CategoryCollection theCategoryCollection;
    private CategoryPanel theCategoryPanel = null;


    public PrimaryListPanel()
    {
        super();
        theCategoryCollection = new CategoryCollection();
        listGUI = new ListSubPanel<Category>(theCategoryCollection, this);

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
    public void valueChanged(ListSelectionEvent e)
    {
        System.out.println(STR."The user just changed the selection in the list to #\{e.getFirstIndex()}. I should probably do something about that.");
        theCategoryPanel.setCurrentCategory(theCategoryCollection.getCategoryAtIndex(e.getFirstIndex()));
    }
}
