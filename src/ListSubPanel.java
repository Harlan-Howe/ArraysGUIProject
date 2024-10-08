import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListSubPanel<ContentType> extends JPanel implements ActionListener
{
    public static final String NULL_LIST = "Null List";
    public static final String FILLED_LIST = "List filled";
    private PanelManager<ContentType> myManager;
    private CardLayout myCardLayout;
    private JList<ContentType> guiList;
    private JButton shiftUpButton, shiftDownButton, addButton, removeButton;

    public ListSubPanel(PanelManager<ContentType> manager, ListSelectionListener listener)
    {
        super();
        myManager = manager;
        guiList = new JList<ContentType>();
        buildGUI(listener);
        update();
    }

    public void setMyManager(PanelManager<ContentType> mgr)
    {
        myManager = mgr;
        update();
    }

    /**
     * creates the GUI elements and links them so that this class will pay attention when the user interacts with them.
     */
    public void buildGUI(ListSelectionListener listener)
    {
        myCardLayout = new CardLayout();
        this.setLayout(myCardLayout);
        this.add(new JLabel("List empty", JLabel.CENTER), NULL_LIST);
        this.add(buildListLayout(listener), FILLED_LIST);
        this.setBorder(LineBorder.createGrayLineBorder());
    }
    public JPanel buildListLayout(ListSelectionListener listener)
    {
        JPanel result = new JPanel();
        result.setLayout(new BorderLayout());
        guiList.addListSelectionListener(listener);
        guiList.setPreferredSize(new Dimension(150,400));
        guiList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        result.add(guiList, BorderLayout.CENTER);
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

        result.add(buttonPanel, BorderLayout.EAST);

        return result;
    }

    public void update()
    {
        if (myManager == null)
        {
            myCardLayout.show(this,NULL_LIST);
        }
        else
        {
            ContentType[] newList = myManager.getListData();
            guiList.setListData(newList);
            guiList.setSelectedIndex(-1);
            guiList.repaint();
            System.out.println("Updated with items:");
            for (ContentType thing : newList)
                System.out.println(STR."\t\{thing}");
            myCardLayout.show(this,FILLED_LIST);
        }
    }

    public int getSelectedIndex()
    {
        return guiList.getSelectedIndex();
    }

    /**
     * if the user clicks one of the buttons, this will call the corresponding command in the parent PanelManager.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == shiftUpButton)
        {
            int oldIndex = guiList.getSelectedIndex();
            if (oldIndex>0)
            {
                myManager.handleShiftUp(oldIndex);
                update();
                guiList.setSelectedIndex(oldIndex - 1);
            }
        }
        if (e.getSource() == shiftDownButton)
        {
            int oldIndex = guiList.getSelectedIndex();
            if (oldIndex > -1 && oldIndex < myManager.getListData().length-1)
            {
                myManager.handleShiftDown(oldIndex);
                update();
                guiList.setSelectedIndex(oldIndex + 1);
            }
        }
        if (e.getSource() == addButton)
        {
            myManager.handleAdd();
            update();
        }
        if (e.getSource() == removeButton)
        {
            myManager.handleRemove(guiList.getSelectedIndex());
            update();
        }

    }


}
