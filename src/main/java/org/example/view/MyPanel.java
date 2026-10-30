package org.example.view;

import lombok.Getter;
import org.example.controller.Controller;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.model.fill.Fill;
import org.example.model.fill.NoFill;
import org.example.model.factory.ShapeType;
import org.example.utils.Observable;
import org.example.utils.Observer;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;


public class MyPanel extends JPanel implements Observer {
    private final Controller controller;
    @Getter
    private final JMenuBar menuBar;

    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private JButton undoButton;
    private JButton redoButton;


    public MyPanel(Controller controller) {
        this.controller = controller;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                controller.mousePressed(arg0.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                controller.mouseReleased();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                controller.mouseDragged(arg0.getPoint());
            }

        });
        menuBar = new JMenuBar();
        var shapes = getShapes(controller);
        var colors = getColors(controller);
        var fill = getFill(controller);
        var tools = getTools(controller);
        var file = getFile(controller);


        menuBar.add(shapes);
        menuBar.add(colors);
        menuBar.add(fill);
        menuBar.add(tools);
        menuBar.add(file);
        add(menuBar);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
        topPanel.add(createToolBar(),BorderLayout.CENTER);
        add(topPanel,BorderLayout.NORTH);
        updateActionState();


    }

    private JMenu getShapes(Controller controller) {
        JMenu shapes = new JMenu("Фигуры");

        JMenuItem ellipse = new JMenuItem("Эллипс");
        ellipse.addActionListener((ActionEvent e)->{
            controller.getModel().setShape(ShapeType.Ellipse);
        });
        JMenuItem rectangle = new JMenuItem("Прямоугольник");
        rectangle.addActionListener((ActionListener) ->{
            controller.getModel().setShape(ShapeType.Rectangle);
        });

        shapes.add(ellipse);
        shapes.add(rectangle);

        menuBar.add(shapes);
        add(menuBar);

        return shapes;
    }

    private JMenu getColors(Controller controller){
        JMenu colors = new JMenu("Цвета");

        JMenuItem green = new JMenuItem("Зеленый");
        green.addActionListener((ActionListener)->{
            controller.getModel().setColor(Color.GREEN);
        } );
        JMenuItem red = new JMenuItem("Красный");
        red.addActionListener((actionEvent)->{
            controller.getModel().setColor(Color.RED);
        });
        JMenuItem blue = new JMenuItem("Синий");
        blue.addActionListener((actionEvent)->{
            controller.getModel().setColor(Color.BLUE);
        });

        colors.add(green);
        colors.add(red);
        colors.add(blue);

        return colors;
    }

    private JMenu getFill(Controller controller){
        JMenu fill = new JMenu("Заливка");

        JMenuItem withFill = new JMenuItem("С заливкой");
        withFill.addActionListener((actionEvent)->{
            controller.getModel().setFb(new Fill());
        });
        JMenuItem withoutFill = new JMenuItem("Без заливки");
        withoutFill.addActionListener((actionEvent)->{
            controller.getModel().setFb(new NoFill());
        });

        fill.add(withFill);
        fill.add(withoutFill);

        return fill;
    }

    private JMenu getTools(Controller controller){
        var tools = new JMenu("Инструменты");

        undoMenuItem = new JMenuItem("Назад");
        undoMenuItem.addActionListener((actionEvent)->{
            controller.getHistory().executeUndo();
        });
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_DOWN_MASK));
        redoMenuItem = new JMenuItem("Вперед");
        redoMenuItem.addActionListener((actionEvent)->{
            controller.getHistory().executeRedo();
        });
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_DOWN_MASK));
        var draw = new JMenuItem("Рисовать");
        draw.addActionListener((actionEvent)->{
            controller.setCurrentAction(new ActionDraw(controller.getModel()));
        });
        var move = new JMenuItem("Двигать");
        move.addActionListener((actionEvent)->{
            controller.setCurrentAction(new ActionMove(controller.getModel()));
        });

        tools.add(undoMenuItem);
        tools.add(redoMenuItem);
        tools.add(draw);
        tools.add(move);

        return tools;
    }

    private JMenu getFile(Controller controller){
        JMenu file = new JMenu("Файл");
        JMenuItem save = new JMenuItem("Сохранить");
        save.addActionListener((actionEvent)->{
            controller.getFileUtil().save();
        });
        JMenuItem load = new JMenuItem("Загрузить");
        load.addActionListener((actionEvent)->{
            controller.getFileUtil().load();
            controller.getHistory().loadFromFile();
            controller.getModel().notifyObservers();
        });

        file.add(save);
        file.add(load);

        return file;
    }

    private JToolBar createToolBar(){
        JToolBar toolBar = new JToolBar("Панель инструментов");
        toolBar.setFloatable(true);

        ImageIcon undoIcon = loadIcon("/icons/undo_16x16.png");
        ImageIcon redoIcon = loadIcon("/icons/redo_16x16.png");
        ImageIcon drawIcon = loadIcon("/icons/draw_16x16.png");
        ImageIcon moveIcon = loadIcon("/icons/move_16x16.png");
        ImageIcon rectangularIcon = loadIcon("/icons/rectangular_16x16.png");
        ImageIcon ellipseIcon = loadIcon("/icons/ellipse_16x16.png");
        ImageIcon fillIcon = loadIcon("/icons/fill_16x16.png");
        ImageIcon noFillIcon = loadIcon("/icons/no_fill_16x16.png");


        undoButton = new JButton(undoIcon);
        undoButton.setToolTipText("Отменить действие");
        undoButton.addActionListener(e -> {controller.getHistory().executeUndo();});
        redoButton = new JButton(redoIcon);
        redoButton.setToolTipText("Восстановить действие");
        redoButton.addActionListener(e -> {controller.getHistory().executeRedo();});
        JButton drawButton = new JButton(drawIcon);
        drawButton.setToolTipText("Рисовать");
        drawButton.addActionListener(e -> {controller.setCurrentAction(new ActionDraw(controller.getModel()));});
        JButton moveButton = new JButton(moveIcon);
        moveButton.setToolTipText("Двигать");
        moveButton.addActionListener(e->{controller.setCurrentAction(new ActionMove(controller.getModel()));});
        JButton rectangularButton = new JButton(rectangularIcon);
        rectangularButton.setToolTipText("Прямоугольник");
        rectangularButton.addActionListener(e->{controller.getModel().setShape(ShapeType.Rectangle);});
        JButton ellipseButton = new JButton(ellipseIcon);
        ellipseButton.setToolTipText("Эллипс");
        ellipseButton.addActionListener(e->{controller.getModel().setShape(ShapeType.Ellipse);});
        JButton fillButton = new JButton(fillIcon);
        fillButton.setToolTipText("С заполнением");
        fillButton.addActionListener(e -> {controller.getModel().setFb(new Fill());});
        JButton noFillButton = new JButton(noFillIcon);
        noFillButton.setToolTipText("Без заполнения");
        noFillButton.addActionListener(e->controller.getModel().setFb(new NoFill()));

        toolBar.add(undoButton);
        toolBar.add(redoButton);
        toolBar.add(drawButton);
        toolBar.add(moveButton);
        toolBar.add(rectangularButton);
        toolBar.add(ellipseButton);
        toolBar.add(fillButton);
        toolBar.add(noFillButton);

        return toolBar;
    }

    private ImageIcon loadIcon(String pathToIcon) {
        URL imgURL = getClass().getResource(pathToIcon);
        if(imgURL!=null){
            ImageIcon imageIcon = new ImageIcon(imgURL);
            Image img = imageIcon.getImage();
            Image scaledImg = img.getScaledInstance(16,16,Image.SCALE_DEFAULT);
            return new ImageIcon(scaledImg);
        }
        else{
            System.err.println("Не удалось найти файл иконки по пути"+ pathToIcon);
            return null;
        }
    }

    private void updateActionState(){
        if(undoMenuItem !=null&& redoMenuItem !=null){
            undoMenuItem.setEnabled(controller.getHistory().isEnableUndo());
            redoMenuItem.setEnabled(controller.getHistory().isEnableRedo());
            undoButton.setEnabled(controller.getHistory().isEnableUndo());
            redoButton.setEnabled(controller.getHistory().isEnableRedo());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        controller.draw(g2);
    }

    @Override
    public void update(Observable observable, Object o) {
        updateActionState();
        repaint();
    }
}
