package ui;

import ObserverPattern.RecipeConsumers;
import model.FamousRecipes;
import model.RecipeBook;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RecipeFrame extends JFrame implements ActionListener {

    ArrayList<RecipeBook> recipeBook = new ArrayList<>();
    ArrayList<FamousRecipes> fRecipe = new ArrayList<>();
    private JLabel label;
    private JLabel jlImage;
    private JLabel label2;
    private JTextField field;
    private JButton btn;
    private JButton allRecipe;
    private JButton famousRecipe;
    private JButton search;
    private JButton finish;
    private JButton add;
    private Font font = new Font("Helvetica", Font.BOLD, 15);
    private Font bigFont = new Font("Helvetica", Font.BOLD, 30);
    private int WIDTH = 400;
    private int HEIGHT = 850;


    public RecipeFrame() {
        super("Recipe App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());
        btn = new JButton("Enter");
        btn.setActionCommand("enter");
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.CENTER);
        btn.setFont(font);
        btn.addActionListener(this);
        allRecipe = new JButton("All Recipe");
        allRecipe.setActionCommand("allrecipe");
        allRecipe.setFont(font);
        allRecipe.addActionListener(this);
        famousRecipe = new JButton("Famous Recipe");
        famousRecipe.setActionCommand("famousRecipe");
        famousRecipe.setFont(font);
        famousRecipe.addActionListener(this);
        search = new JButton("Search");
        search.setActionCommand("search");
        search.setFont(font);
        search.addActionListener(this);
        finish = new JButton("Finish");
        finish.setActionCommand("finish");
        finish.setFont(font);
        finish.addActionListener(this);
       //add = new JButton("Add Recipe!");
       // add.setActionCommand("add");
        //add.addActionListener(this);
        //add.setFont(font);
        label = new JLabel("<html>Hello! Welcome to my Recipe App!<br>" +
                "Click enter to start </html>", JLabel.CENTER);
        label2 = new JLabel("An ingredient has been added!");
        label2.setFont(font);
        label.setFont(font);
        field = new JTextField(5);
        add(btn);
        add(label);
        jlImage = new JLabel(new ImageIcon("tenor.gif"));
        add(jlImage);
        pack();
        //setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        try {
            load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("enter")) {
            remove(jlImage);
            remove(label);
            add(label);
            add(allRecipe);
            add(famousRecipe);
            add(search);
            //add(add);
            add(field);
            add(finish);
            remove(btn);
            jlImage = new JLabel(new ImageIcon("food.gif"));
            add(jlImage);
            label.setText("Please select an option:");
        }
        if (e.getActionCommand().equals("allrecipe")) {
            remove(jlImage);
            jlImage.setIcon(new ImageIcon("girleating.gif"));
            add(jlImage);
            String all = "<html>";
            for(RecipeBook r : recipeBook) {
                all += r.toSimpleString() + "<br><br>";
            }
            all += "</html>";
            label.setText(all);
        }
        /*if (e.getActionCommand().equals("add")) {
            RecipeBook r = new RecipeBook("","", new ArrayList<>(), "");
            recipeBook.add(r);
            r.addIngredient(field.getText());
            add(label2);
        }*/
        if (e.getActionCommand().equals("famousRecipe")) {
            remove(jlImage);
            jlImage.setIcon(new ImageIcon("girleating.gif"));
            add(jlImage);
            String all = "<html> What's good right now! <br><br>";
            for(FamousRecipes r : fRecipe) {
                all += r.toSimpleString() + "<br><br>";
            }
            all += "</html>";
            label.setText(all);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        if (e.getActionCommand().equals("search")) {
            String all = "<html>";
            for (RecipeBook r : recipeBook) {
                if (r.hasIngredient(field.getText())) {
                    all += r.toSimpleString() + "<br><br>";
                }
            }
            all += "</html>";
            label.setText(all);
        }
        if (e.getActionCommand().equals("finish")) {
            label.setText("Enjoy your meal!");
            try {
                save();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            label.setFont(bigFont);
            remove(allRecipe);
            remove(famousRecipe);
            remove(search);
            remove(field);
            remove(finish);
            remove(add);
            jlImage.setIcon(new ImageIcon("giphy.gif"));
        }
    }

    public void load() throws IOException {
        List<String> recipes = Files.readAllLines(Paths.get("Recipes.txt"));
        for(String s : recipes) {
            RecipeBook r = new RecipeBook(null, null, new ArrayList<>(), null);
            r.deserialize(s);
            recipeBook.add(r);
        }

        List<String> fRecipes = Files.readAllLines(Paths.get("Famous.txt"));
        for(String s : fRecipes) {
            FamousRecipes fr = new FamousRecipes(null,null, null);
            fr.deserialize(s);
            fRecipe.add(fr);
        }
    }

    public void save() throws IOException {
        List<String> recipes = new ArrayList<>();
        for(RecipeBook r : recipeBook) {
            recipes.add(r.serialize());
        }
        Files.write(Paths.get("Recipes.txt"), recipes);

        List<String> fRecipes = new ArrayList<>();
        for(FamousRecipes fr : fRecipe) {
            fRecipes.add(fr.serialize());
        }
        Files.write(Paths.get("Famous.txt"), fRecipes);
    }


    public static void main (String[]args)
    {
        new RecipeFrame();
    }
}

