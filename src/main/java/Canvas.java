import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel {
    private final static int STROKE_SIZE = 8;

    // holds all the paths created in the canvas
    private List<List<ColorPoint>> allPaths;

    // used to draw a line between points
    private List<ColorPoint> currentPath;

    // color of the dots
    private Color color;

    // canvas width and height
    private int canvasWidth, canvasHeight;

    // location of the dots
    private int x, y;

    public Canvas(int targetWidth, int targetHeight){
        super();
        setPreferredSize(new Dimension(targetWidth, targetHeight));
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //referencing other class

        //initializing variables
        allPaths = new ArrayList<>(25);
        canvasWidth = targetWidth;
        canvasHeight = targetHeight;

        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //getting the mouse location
                x = e.getX();
                y = e.getY();

                //drawing in the location
                Graphics g = getGraphics();
                g.setColor(color);
                g.fillRect(x, y, STROKE_SIZE, STROKE_SIZE);
                g.dispose();

                //create path
                currentPath = new ArrayList<>(25);
                currentPath.add(new ColorPoint(x, y, color));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //add to all paths
                allPaths.add(currentPath);

                //reset path
                currentPath = null;
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                //get location
                x = e.getX();
                y = e.getY();

                //draw a line
                Graphics2D g2d = (Graphics2D) getGraphics();
                g2d.setColor(color);
                if(!currentPath.isEmpty()){
                    ColorPoint previous = currentPath.get(currentPath.size() - 1);
                    g2d.setStroke(new BasicStroke(STROKE_SIZE));

                    //connect to previous point
                    g2d.drawLine(previous.getX(), previous.getY(), x, y);
                }
                g2d.dispose();

                //add to the path
                ColorPoint next = new ColorPoint(e.getX(), e.getY(), color);
                currentPath.add(next);            }
        };

        addMouseListener(ma);
        addMouseMotionListener(ma);
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void resetCanvas(){
        //clears board
        Graphics g = getGraphics();
        g.clearRect(0, 0, canvasWidth, canvasHeight);
        g.dispose();

        //reset
        allPaths = new ArrayList<>(25);
        currentPath = null;

        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //redraws paths
        for(List<ColorPoint> path : allPaths){
            ColorPoint from = null;
            for(ColorPoint point : path){
                g2d.setColor(point.getColor());

                //create dot
                if(path.size() == 1){
                    g2d.fillRect(point.getX(), point.getY(), STROKE_SIZE, STROKE_SIZE);
                }

                if(from != null){
                    g2d.setStroke(new BasicStroke(STROKE_SIZE));
                    g2d.drawLine(from.getX(), from.getY(), point.getX(), point.getY());
                }
                from = point;
            }
        }
    }
}