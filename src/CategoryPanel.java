import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the middle third of the screen.
 */
public class CategoryPanel extends JPanel implements ActionListener, ListSelectionListener
{
    // GUI elements shown in this panel
    private ListSubPanel<Item> itemList;
    private JTextField titleField, subtitleField;
    private JButton updateButton;

    // the category that is currently being shown, which serves as the datasource for the GUI elements.
    private Category currentCategory;

    // a pointer to the next panel.
    private ItemPanel theItemPanel;


    public CategoryPanel()
    {
        super();
        itemList = new ListSubPanel<Item>(null, this);
        currentCategory = null;
        buildGUI();
    }

    /**
     * sets up the parts of the panel that the user sees.
     */
    public void buildGUI()
    {
        Box contents = Box.createVerticalBox();
        this.add(contents);
        titleField = new JTextField();
        subtitleField = new JTextField();
        JPanel itemFieldsPanel = fillItemFieldsPanel(); // puts the fields and update button into the top of the panel.
        contents.add(itemFieldsPanel);

        contents.add(itemList); // add the GUI list of categories to the bottom of the panel.

    }

    /**
     * arranges the labels and GUI inputs to allow the user to see and edit the properties of this category.
     * @return itemFieldsPanel - the panel in which to lay this content out.
     */
    private JPanel fillItemFieldsPanel()
    {
        JPanel itemFieldsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.33;
        constraints.weighty = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        itemFieldsPanel.add(new JLabel("Title"), constraints);
        constraints.gridy = 1;
        itemFieldsPanel.add(new JLabel("Subtitle"), constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.67;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        itemFieldsPanel.add(titleField, constraints);
        constraints.gridy = 1;
        itemFieldsPanel.add(subtitleField, constraints);
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        itemFieldsPanel.add(updateButton, constraints);

        return itemFieldsPanel;
    }

    /**
     * change which category should serve as the datasource for the GUI elements. This is typically called by the left
     * panel when the user changes which category is selected in the first list.
     * @param cat - the category to reveal, or null if no category should be shown.
     */
    public void setCurrentCategory(Category cat)
    {
        currentCategory = cat;
        if (currentCategory == null)
        {
            titleField.setText("");
            itemList.setMyManager(null);
        }
        else
        {
            titleField.setText(currentCategory.getTitle());
            itemList.setMyManager(currentCategory);
        }
        theItemPanel.setCurrentItem(null);
    }

    /**
     * establishes the link to the right panel, so that this panel can tell that one when it needs to update which item
     * it is showing.
     * @param ip - the ItemPanel... i.e., the rightmost panel.
     */
    public void setItemPanel(ItemPanel ip)
    {
        theItemPanel = ip;
    }


    /**
     * respond to the user pressing the update button.
     * @param e the event to be processed - info about which button was pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == updateButton)
        {
            System.out.println("User just pressed update. I should probably update the non-array parts of the current Category.");
            System.out.println(STR."The new title should be '\{titleField.getText()}' and the subtitle should be '\{subtitleField.getText()}.'");
            if (currentCategory != null)
            {
                currentCategory.setTitle(titleField.getText());
                getParent().repaint();
            }
        }
    }

    /**
     * respond to the user changing which row in the listGUI is selected, which should pass along that information to
     * the rightmost panel.
     * @param e the event that characterizes the change -- includes info about which row is now selected.
     */
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        System.out.println(STR."Hey, the user just picked a different item, #\{e.getFirstIndex()} on the list. I should do something about that.");
        theItemPanel.setCurrentItem(currentCategory.getItemAtIndex(e.getFirstIndex()));
    }
}
