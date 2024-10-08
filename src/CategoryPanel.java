import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryPanel extends JPanel implements ActionListener, ListSelectionListener
{
    private ListSubPanel<Item> itemList;
    private Category currentCategory;
    private ItemPanel theItemPanel;
    private JTextField titleField, subtitleField;
    JButton updateButton;

    public CategoryPanel()
    {
        super();
        itemList = new ListSubPanel<Item>(null, this);
        currentCategory = null;
        buildGUI();
    }

    public void buildGUI()
    {
        Box contents = Box.createVerticalBox();
        this.add(contents);
        titleField = new JTextField();
        subtitleField = new JTextField();
        JPanel itemFieldsPanel = new JPanel(new GridBagLayout());
        fillItemFieldsPanel(itemFieldsPanel);

        contents.add(itemFieldsPanel);
        contents.add(itemList);

    }

    private void fillItemFieldsPanel(JPanel itemFieldsPanel)
    {
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
    }

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
    }
    public void setItemPanel(ItemPanel ip)
    {
        theItemPanel = ip;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == updateButton)
        {
            System.out.println("User just pressed update. I should probably update the non-array parts of the current Category.");
            System.out.println(STR."The new title should be '\{titleField.getText()}' and the subtitle should be '\{subtitleField.getText()}.'");
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        System.out.println(STR."Hey, the user just picked a different item, #\{e.getFirstIndex()} on the list. I should do something about that.");
        theItemPanel.setCurrentItem(currentCategory.getItemAtIndex(e.getFirstIndex()));
    }
}
