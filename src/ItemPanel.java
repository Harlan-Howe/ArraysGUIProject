import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ItemPanel extends JPanel implements PanelManager<Detail>, ActionListener
{
    private ListSubPanel<Detail> detailList;
    private Item currentItem;
    private DetailPanel theDetailPanel;
    private JTextField titleField, subtitleField;
    JButton updateButton;

    public ItemPanel()
    {
        super();
        detailList = new ListSubPanel<Detail>(this);
        currentItem = null;
        buildGUI();
    }

    public void buildGUI()
    {
        Box contents = Box.createVerticalBox();
        this.add(contents);
        titleField = new JTextField();
        subtitleField = new JTextField();
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

        contents.add(itemFieldsPanel);
        contents.add(detailList);

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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == updateButton)
        {
            System.out.println("User just pressed update. I should probably update the non-array parts of the current Item.");
            System.out.println(STR."The new title should be '\{titleField.getText()}' and the subtitle should be '\{subtitleField.getText()}.'");
        }
    }
}
