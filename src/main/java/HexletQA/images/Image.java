package HexletQA.images;

import ij.IJ;
import ij.ImagePlus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * работа с изображеними - библиотеки swing, awt, ij
 */

public class Image {

    /**
     * задание верификации для поля ввода
     */
    public static void verifyInputField() {
        InputVerifier verifier = new InputVerifier() {
            public boolean verify(JComponent input) {
                JTextField textField = (JTextField) input;
                return Pattern.matches("[a-zA-Z]+", textField.getText());
            }
            public boolean shouldYieldFocus(JComponent source, JComponent target) {
                return verify(source);
            }
        };

        JTextField field1 = new JTextField("only a-z and A-Z");
        JTextField field2 = new JTextField();
        field1.setInputVerifier(verifier);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(field1, BorderLayout.NORTH);
        frame.getContentPane().add(field2, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * вывод картинки из файла
     * @throws IOException
     */
    public static void getAndShowImage() throws IOException {
        String fileName = "rose.bmp";
        // считаем изображение из файла
        BufferedImage myPicture = ImageIO.read(new File(fileName));

        // выведем изображение на экран
        JLabel myLabel = new JLabel(new ImageIcon(myPicture));
        JPanel myPanel = new JPanel();
        myPanel.add(myLabel);
        JFrame myFrame = new JFrame();
        myFrame.setSize(new Dimension(myPicture.getWidth(), myPicture.getHeight()));
        myFrame.add(myPanel);
        myFrame.setVisible(true);

        ImagePlus myImage = IJ.openImage(fileName);
        myImage.show();
    }

    /**
     * рисование треугольника
     */
    public static void triangle() {
        class DrawATriangle extends JPanel {
            public void paintComponent(Graphics g) {
                int[] xPoints = {50, 100, 0};
                int[] yPoints = {0, 100, 100};
                g.drawPolygon(xPoints, yPoints, 3);
            }
        }

        DrawATriangle drawATriangle = new DrawATriangle();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        frame.setSize(300, 200);
        frame.add(drawATriangle);
        frame.setVisible(true);
    }

    /**
     * рисование круга
     */
    public static void circle() {
        class DrawCircle extends JPanel {
            public void paintComponent(Graphics g) {
                g.drawOval(50, 50, 100, 100);
            }
        }
        DrawCircle circle = new DrawCircle();
        JFrame frame = new JFrame();
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(circle, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * получение текста от пользователя
     */
    public static void dialog() {
        String text = JOptionPane.showInputDialog(null, "Введите сообщение:");
        System.out.println("Получено сообщение : " + text);
    }

    /**
     * создание и обработка нажатий для кнопок ввода
     */
    public static void button() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем две кнопки
        JButton button1 = new JButton("Кнопка 1");
        JButton button2 = new JButton("Кнопка 2");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка 1");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Нажата кнопка 2");
            }
        });

        // Добавляем кнопки на панель содержимого
        frame.getContentPane().add(BorderLayout.NORTH, button1);
        frame.getContentPane().add(BorderLayout.SOUTH, button2);

        // Устанавливаем размер и отображаем окно
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

}
