import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryPanel extends JPanel implements PanelManager<Detail>, ActionListener
{
    private ListSubPanel<Detail> detailList;
    private Category currentCategory;
    private DetailPanel theDetailPanel;
    private JTextField titleField, subtitleField;
    JButton updateButton;

    public CategoryPanel()
    {
        super();
        detailList = new ListSubPanel<Detail>(this);
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
        contents.add(detailList);

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
        if (currentCategory == null)
            return new Detail[0];
        return currentCategory.getDetails();
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
            System.out.println("User just pressed update. I should probably update the non-array parts of the current Category.");
            System.out.println(STR."The new title should be '\{titleField.getText()}' and the subtitle should be '\{subtitleField.getText()}.'");
        }
    }
}
