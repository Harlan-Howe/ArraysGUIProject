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
    JTextField titleField, albumField;
    JSpinner minutesSpinner, secondsSpinner;
//    JCheckBox statusCheckBox;
//    JTextArea detailTextArea;
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
//        Box content = Box.createVerticalBox();
//
//        this.add(content);
//        this.setLayout(new GridLayout(1,1));
//        JPanel smallItemsPanel = new JPanel(new GridBagLayout());
        this.setLayout(new GridBagLayout());
        buildContentInSmallItemsPanel(this);

        // set up a larger text area, if desired.
//        detailTextArea = new JTextArea(20,20);
//        detailTextArea.setLineWrap(true);
//        detailTextArea.setWrapStyleWord(true);
//        JScrollPane detailScroller = new JScrollPane(detailTextArea,
//                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        content.add(detailScroller);

        // add a button at the bottom to allow the user to update the data.



    }

    /**
     * a submethod of buildGUI, this assembles the table of labels and small GUI controls.
     * @param panel
     */
    public void buildContentInSmallItemsPanel(JPanel panel)
    {
        GridBagConstraints constraints = new GridBagConstraints();
        titleField = new JTextField();
        albumField = new JTextField();
        minutesSpinner = new JSpinner();
        secondsSpinner = new JSpinner();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.2;
        constraints.anchor = GridBagConstraints.LINE_END;
        panel.add(new JLabel("Title"), constraints);
        constraints.gridy = 1;
        panel.add(new JLabel("Album"), constraints);
        constraints.gridy = 2;
        panel.add(new JLabel("Duration"), constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.8;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 3;
        panel.add(titleField, constraints);
        constraints.gridy = 1;
        panel.add(albumField, constraints);
        constraints.gridy = 2;
        constraints.gridwidth =1;
        panel.add(minutesSpinner, constraints);
        constraints.gridx = 2;
        panel.add(new JLabel(":"),constraints);
        constraints.gridx = 3;
        panel.add(secondsSpinner, constraints);
        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 4;
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        panel.add(updateButton,constraints);
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
            titleField.setText("");
            albumField.setText("");
        }
        else
        {
            titleField.setText(currentItem.getTitle());
            albumField.setText(currentItem.getAlbum());
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
//            System.out.println(STR."Name should now be '\{nameField.getText()}', status should be \{statusCheckBox.isSelected()}, and the detail should be '\{detailTextArea.getText()}'");
            if (currentItem != null)
            {
                currentItem.setTitle(titleField.getText());
                currentItem.setAlbum(albumField.getText());
                getParent().repaint();
            }
        }
    }
}
