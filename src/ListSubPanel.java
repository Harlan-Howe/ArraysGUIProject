import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListSubPanel<ContentType> extends JPanel implements ActionListener
{
    private PanelManager<ContentType> myParent;
    private JList<ContentType> guiList;
    private JButton shiftUpButton, shiftDownButton, addButton, removeButton;

    public ListSubPanel(PanelManager<ContentType> parent)
    {
        super();
        myParent = parent;
        buildGUI();
    }

    /**
     * creates the GUI elements and links them so that this class will pay attention when the user interacts with them.
     */
    public void buildGUI()
    {
        setLayout(new BorderLayout());
        guiList = new JList<ContentType>();
        this.add(guiList, BorderLayout.CENTER);
        Box buttonPanel = Box.createVerticalBox();
        buttonPanel.add(Box.createVerticalGlue());
        shiftUpButton = new JButton("⬆︎");
        buttonPanel.add(shiftUpButton);
        buttonPanel.add(Box.createVerticalGlue());
        shiftUpButton.addActionListener(this);

        shiftDownButton = new JButton("⬇︎");
        buttonPanel.add(shiftDownButton);
        buttonPanel.add(Box.createVerticalGlue());
        shiftDownButton.addActionListener(this);

        addButton = new JButton("+");
        buttonPanel.add(addButton);
        buttonPanel.add(Box.createVerticalGlue());
        addButton.addActionListener(this);

        removeButton = new JButton("-");
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createVerticalGlue());
        removeButton.addActionListener(this);

        this.add(buttonPanel, BorderLayout.EAST);
        this.setBorder(LineBorder.createGrayLineBorder());
    }

    @Override
    /**
     * if the user clicks one of the buttons, this will call the corresponding command in the parent PanelManager.
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == shiftUpButton)
            myParent.handleShiftUp(guiList.getSelectedIndex());
        if (e.getSource() == shiftDownButton)
            myParent.handleShiftDown(guiList.getSelectedIndex());
        if (e.getSource() == addButton)
            myParent.handleAdd();
        if (e.getSource() == removeButton)
            myParent.handleRemove(guiList.getSelectedIndex());

    }
}
