import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * This class sets up the overall window, dividing up the screen into the three panels and establishing the menus.
 */
public class ArraysGUIProjectFrame extends JFrame
{
    // variables for the three panels on the page.
    private PrimaryListPanel leftPanel;
    private CategoryPanel centerPanel;
    private ItemPanel rightPanel;
    // menu setup variables
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newMI, openMI, saveMI, saveAsMI;

    public ArraysGUIProjectFrame()
    {
        super("Window title here.");
        setSize(800,600);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildGUI();
    }

    /**
     * sets up all the GUI elements in this window.
     */
    public void buildGUI()
    {
        this.getContentPane().setLayout(new GridLayout(1,3));
        leftPanel = new PrimaryListPanel();
        centerPanel = new CategoryPanel();
        rightPanel = new ItemPanel();

        this.getContentPane().add(leftPanel);
        this.getContentPane().add(centerPanel);
        this.getContentPane().add(rightPanel);

        // tell the two left panes about the ones just to the right of them. - they have methods for this.
        leftPanel.setCategoryPanel(centerPanel);
        centerPanel.setItemPanel(rightPanel);

        makeMenus();
    }

    public void makeMenus()
    {
        // build the menubar and the File menu.
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // create the four items that will (eventually) go in the file menu
        newMI = new JMenuItem("New");
        openMI = new JMenuItem("Open...");
        saveMI = new JMenuItem("Save");
        saveAsMI = new JMenuItem("Save As...");

        // add keyboard shortcuts... I got fancy.
        newMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, java.awt.event.InputEvent.META_DOWN_MASK));
        openMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, java.awt.event.InputEvent.META_DOWN_MASK));
        saveMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, java.awt.event.InputEvent.META_DOWN_MASK));
        saveAsMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, java.awt.event.InputEvent.META_DOWN_MASK+ InputEvent.SHIFT_DOWN_MASK));

        // if the user selects one of these menu items, the left panel should be told about it.
        newMI.addActionListener(leftPanel);
        openMI.addActionListener(leftPanel);
        saveMI.addActionListener(leftPanel);
        saveAsMI.addActionListener(leftPanel);

        // put the menu items into the File menu.
        fileMenu.add(newMI);
        fileMenu.add(openMI);
        fileMenu.add(saveMI);
        fileMenu.add(saveAsMI);
    }
}
