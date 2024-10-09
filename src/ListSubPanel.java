import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * a reusable class that manages a JList gui element along with buttons for shifting items up/down, and add/remove.
 * @param <ContentType> - the type of item that will be listed; the toString of this class is what will appear.
 */
public class ListSubPanel<ContentType> extends JPanel implements ActionListener
{
    // keys for the cards that are used to either show an empty pane or the JList.
    private static final String NULL_LIST = "Null List";
    private static final String FILLED_LIST = "List filled";

    private PanelManager<ContentType> myManager; // the class that will serve as the datasource for this list.

    // GUI sub-elements
    private CardLayout myCardLayout;
    private JList<ContentType> guiList;
    private JButton shiftUpButton, shiftDownButton, addButton, removeButton;

    /**
     * constructor
     * @param manager - a class implementing PanelManager that will serve as the data source.
     * @param listener - a class implementing ListSelectionListener that this ListSubPanel should inform if the
     *                 user changes what is selected in the list.
     */
    public ListSubPanel(PanelManager<ContentType> manager, ListSelectionListener listener)
    {
        super();
        myManager = manager;
        guiList = new JList<ContentType>();
        buildGUI(listener);
        update();
    }

    /**
     * modifier of the datasource; used when switching which Category is shown, or if we are loading a new file.
     * @param mgr - the new datasource.
     */
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

    /**
     * updates the appearance of the panel, based on the state of the PanelManager.
     */
    public void update()
    {
        if (myManager == null)  // if the manager is none, indicate that gracefully - no crashes.
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

    /**
     * indicates which row in the list is currently selected. (Possibly not needed.)
     * @return the index of the selected row, or -1 if none is selected.
     */
    public int getSelectedIndex()
    {
        return guiList.getSelectedIndex();
    }

    /**
     * if the user clicks one of the buttons, this will call the corresponding command in the parent PanelManager.
     * @param actEvt - the action event describing what the user did, including information about what button was pressed.
     */
    @Override
    public void actionPerformed(ActionEvent actEvt)
    {
        if (actEvt.getSource() == shiftUpButton)
        {
            int oldIndex = guiList.getSelectedIndex();
            if (oldIndex>0)
            {
                myManager.handleShiftUp(oldIndex);
                update();
                guiList.setSelectedIndex(oldIndex - 1);
            }
        }
        if (actEvt.getSource() == shiftDownButton)
        {
            int oldIndex = guiList.getSelectedIndex();
            if (oldIndex > -1 && oldIndex < myManager.getListData().length-1)
            {
                myManager.handleShiftDown(oldIndex);
                update();
                guiList.setSelectedIndex(oldIndex + 1);
            }
        }
        if (actEvt.getSource() == addButton)
        {
            myManager.handleAdd();
            update();
        }
        if (actEvt.getSource() == removeButton)
        {
            myManager.handleRemove(guiList.getSelectedIndex());
            update();
        }

    }


}
