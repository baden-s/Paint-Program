import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame {
    public MainGui(){
        super("Paint Program");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1600, 1000));
        //creating default program
        pack();
        setLocationRelativeTo(null);
        //initializing the Jpanel
        addGuiComponents();
    }

    private void addGuiComponents(){
        //JPanel configs
        JPanel canvasPanel = new JPanel();
        SpringLayout springLayout = new SpringLayout();
        canvasPanel.setLayout(springLayout);

        //Canvas creator
        Canvas canvas = new Canvas(1600, 950);
        canvasPanel.add(canvas);
        springLayout.putConstraint(SpringLayout.NORTH, canvas, 50, SpringLayout.NORTH, canvasPanel);

        //Color choosing algorithm
        JButton selectionB = new JButton("Choose Color");
        selectionB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(null, "Select a color", Color.BLACK);
                selectionB.setBackground(c);
                canvas.setColor(c);
                //selection menu
            }
        });
        canvasPanel.add(selectionB);
        springLayout.putConstraint(SpringLayout.NORTH, selectionB, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, selectionB, 25, SpringLayout.WEST, canvasPanel);

        //Resetting workplace
        JButton resetB = new JButton("Reset");
        resetB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.resetCanvas();
            }
        });
        canvasPanel.add(resetB);
        springLayout.putConstraint(SpringLayout.NORTH, resetB, 10, SpringLayout.NORTH, canvasPanel);
        springLayout.putConstraint(SpringLayout.WEST, resetB, 150, SpringLayout.WEST, canvasPanel);


        this.getContentPane().add(canvasPanel);
    }
}