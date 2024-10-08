import javax.swing.*;
import java.awt.*;

public class ArraysGUIProjectFrame extends JFrame
{

    private PrimaryListPanel leftPanel;
    private CategoryPanel centerPanel;
    private DetailPanel rightPanel;

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
        rightPanel = new DetailPanel();

        this.getContentPane().add(leftPanel);
        this.getContentPane().add(centerPanel);
        this.getContentPane().add(rightPanel);

        leftPanel.setItemPanel(centerPanel);
        centerPanel.setDetailPanel(rightPanel);
    }
}
