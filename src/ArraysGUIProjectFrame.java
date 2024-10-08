import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ArraysGUIProjectFrame extends JFrame
{

    private PrimaryListPanel leftPanel;
    private CategoryPanel centerPanel;
    private ItemPanel rightPanel;
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

        leftPanel.setCategoryPanel(centerPanel);
        centerPanel.setItemPanel(rightPanel);

        makeMenus();
    }

    public void makeMenus()
    {
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        newMI = new JMenuItem("New");
        openMI = new JMenuItem("Open...");
        saveMI = new JMenuItem("Save");
        saveAsMI = new JMenuItem("Save As...");

        newMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, java.awt.event.InputEvent.META_DOWN_MASK));
        openMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, java.awt.event.InputEvent.META_DOWN_MASK));
        saveMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, java.awt.event.InputEvent.META_DOWN_MASK));
        saveAsMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, java.awt.event.InputEvent.META_DOWN_MASK+ InputEvent.SHIFT_DOWN_MASK));

        newMI.addActionListener(leftPanel);
        openMI.addActionListener(leftPanel);
        saveMI.addActionListener(leftPanel);
        saveAsMI.addActionListener(leftPanel);

        fileMenu.add(newMI);
        fileMenu.add(openMI);
        fileMenu.add(saveMI);
        fileMenu.add(saveAsMI);


    }
}
