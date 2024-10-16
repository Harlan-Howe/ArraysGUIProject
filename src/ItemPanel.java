import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class represents the rightmost third of the window, where we show and edit the details about the currently
 * selected item.
 */
public class ItemPanel extends JPanel implements ActionListener
{
    JTextField nameField;
    JCheckBox statusCheckBox;
    JTextArea detailTextArea;
    JButton updateButton;
    Item currentItem;

    public ItemPanel()
    {
        super();
        currentItem = null;
        buildGUI();

    }

    /**
     * sets up the portions of this panel that the user sees.
     */
    public void buildGUI()
    {
        Box content = Box.createVerticalBox();
        this.add(content);
        JPanel smallItemsPanel = new JPanel(new GridBagLayout());
        buildContentInSmallItemsPanel(smallItemsPanel);
        content.add(smallItemsPanel);

        // set up a larger text area, if desired.
        detailTextArea = new JTextArea(20,20);
        detailTextArea.setLineWrap(true);
        detailTextArea.setWrapStyleWord(true);
        detailTextArea.setEnabled(false);
        JScrollPane detailScroller = new JScrollPane(detailTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        content.add(detailScroller);

        // add a button at the bottom to allow the user to update the data.
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        updateButton.setEnabled(false);
        content.add(updateButton);

    }

    /**
     * a submethod of buildGUI, this assembles the table of labels and small GUI controls.
     * @param panel
     */
    public void buildContentInSmallItemsPanel(JPanel panel)
    {
        GridBagConstraints constraints = new GridBagConstraints();
        nameField = new JTextField();
        nameField.setEnabled(false);
        statusCheckBox = new JCheckBox();
        statusCheckBox.setEnabled(false);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.33;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Name"), constraints);
        constraints.gridy = 1;
        panel.add(new JLabel("Status"), constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.67;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameField, constraints);
        constraints.gridy = 1;
        panel.add(statusCheckBox, constraints);
    }

    /**
     * changes which item's data is being displayed in this panel.
     * @param item - the item to which we are changing, or null if no item should be shown.
     */
    public void setCurrentItem(Item item)
    {
        currentItem = item;
        if (currentItem == null)
        {
            nameField.setText("");
            nameField.setEnabled(false);
            statusCheckBox.setEnabled(false);
            detailTextArea.setEnabled(false);
            updateButton.setEnabled(false);

        }
        else
        {
            nameField.setText(currentItem.getName());
            nameField.setEnabled(true);
            statusCheckBox.setEnabled(true);
            detailTextArea.setEnabled(true);
            updateButton.setEnabled(true);
        }
    }

    /**
     * respond to the user pressing the update button, changing the data stored in the current item.
     * @param e the event to be processed, which contains information about which button was pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == updateButton)
        {
            System.out.println("The user just pressed the update button. I should alter the current item's details based on the controls in this panel.");
            System.out.println(STR."Name should now be '\{nameField.getText()}', status should be \{statusCheckBox.isSelected()}, and the detail should be '\{detailTextArea.getText()}'");
            if (currentItem != null)
            {
                currentItem.setName(nameField.getText());
                getParent().repaint(); // tell the window to refresh at the next opportunity.
            }
        }
    }
}
