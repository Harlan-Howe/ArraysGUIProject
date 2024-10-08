import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void buildGUI()
    {
        Box content = Box.createVerticalBox();
        this.add(content);
        JPanel smallItemsPanel = new JPanel(new GridBagLayout());
        buildContentInSmallItemsPanel(smallItemsPanel);
        content.add(smallItemsPanel);
        detailTextArea = new JTextArea(20,20);
        detailTextArea.setLineWrap(true);
        detailTextArea.setWrapStyleWord(true);
        JScrollPane detailScroller = new JScrollPane(detailTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        content.add(detailScroller);
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        content.add(updateButton);

    }

    public void buildContentInSmallItemsPanel(JPanel panel)
    {
        GridBagConstraints constraints = new GridBagConstraints();
        nameField = new JTextField();
        statusCheckBox = new JCheckBox();
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

    public void setCurrentItem(Item item)
    {
        currentItem = item;
        if (currentItem == null)
        {
            nameField.setText("");
        }
        else
        {
            nameField.setText(currentItem.getName());
        }
    }

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
                getParent().repaint();
            }
        }
    }
}
