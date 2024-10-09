import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PrimaryListPanel extends JPanel implements ListSelectionListener, ActionListener
{
    private ListSubPanel<Category> listGUI;
    private CategoryCollection theCategoryCollection;
    private CategoryPanel theCategoryPanel;
    private File latestFile;


    public PrimaryListPanel()
    {
        super();
        theCategoryCollection = new CategoryCollection();
        listGUI = new ListSubPanel<Category>(theCategoryCollection, this);

        latestFile = null;
        theCategoryPanel = null;

        buildGUI();
        listGUI.update();
        repaint();
    }

    public void buildGUI()
    {
        this.add(listGUI);
    }

    public void setCategoryPanel(CategoryPanel cp)
    {
        theCategoryPanel = cp;
    }

    public void handleOpenRequest()
    {
        JFileChooser openDialog = new JFileChooser();
        openDialog.setSelectedFile(latestFile);
        int response = openDialog.showOpenDialog(this);
        if (response == JFileChooser.CANCEL_OPTION)
            return;
        latestFile = openDialog.getSelectedFile();

        try
        {
            BufferedReader reader = new BufferedReader((new FileReader(latestFile)));
            String latestLine = reader.readLine();
            int numCategories = Integer.parseInt(latestLine);
            theCategoryCollection = new CategoryCollection(numCategories);
            for (int catIndex = 0; catIndex < numCategories; catIndex++)
            {
                String categoryDescription = reader.readLine();
                String numItemsInCategoryString = reader.readLine();
                int numItemsInCategory = Integer.parseInt(numItemsInCategoryString);
                Category cat = new Category(categoryDescription, numItemsInCategory);
                for (int itemIndex = 0; itemIndex < numItemsInCategory; itemIndex++)
                {
                    itemDescription = reader.readLine();
                    Item nextItem = new Item(itemDescription);
                    cat.setItem(nextItem, itemIndex);
                }
                theCategoryCollection.setCategory(cat, catIndex);
            }
            reader.close();
            System.out.println("File opened.");
            listGUI.setMyManager(theCategoryCollection);
            theCategoryPanel.setCurrentCategory(null);
            repaint();
        } catch (FileNotFoundException fnfe)
        {
            throw new RuntimeException("Could not open the file.");
        } catch (IOException e)
        {
            throw new RuntimeException("Problem reading the file once open.");
        }
    }

    public void handleSaveRequest()
    {
        System.out.println("Saving.");
        if (latestFile == null)
        {
            handleSaveAsRequest();
            return;
        }
        String stringToSave = theCategoryCollection.getSaveString();
        System.out.println(stringToSave);
        try (PrintWriter fileOut = new PrintWriter(latestFile)) {
            fileOut.println(stringToSave);
            System.out.println("Saved.");
        } catch (FileNotFoundException e)
        {
            System.out.println(STR."Error opening file: \{latestFile.getPath()}");
        }

    }

    public void handleSaveAsRequest()
    {
        JFileChooser saveDialog = new JFileChooser();
        saveDialog.setCurrentDirectory(latestFile);
        int confirmed = saveDialog.showSaveDialog(this);
        File selectedFile = saveDialog.getSelectedFile();
        if (JFileChooser.CANCEL_OPTION == confirmed || selectedFile == null)
            return;
        latestFile = selectedFile;
        handleSaveRequest();
    }



    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        System.out.println(STR."The user just changed the selection in the list to #\{e.getFirstIndex()}. I should probably do something about that.");
        theCategoryPanel.setCurrentCategory(theCategoryCollection.getCategoryAtIndex(e.getFirstIndex()));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("Save"))
            handleSaveRequest();

    }
}
