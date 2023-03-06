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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * работа с изображеними - библиотеки swing, awt, ij
 */

public class Image {

    public static void drawWeatherGrafic() {
        // создадим список с данными погоды
        class Weather {
            LocalDateTime time;
            int temperature;

            public Weather(LocalDateTime time, int temperature) {
                this.time = time;
                this.temperature = temperature;
            }
        }
        LocalDateTime time = LocalDateTime.now();
        List<Weather> list = new ArrayList<>(List.of(new Weather(time, 0),
                new Weather(time.minusDays(1), 5), new Weather(time.minusDays(2), 0),
                new Weather(time.plusDays(1), 10), new Weather(time.plusDays(2), 5)));

        int cX = 20; // смещение для рисования осей
        int cY = 350;
        int points = list.size(); // количество точек на графике

        // создадим массив координат х
        list.sort((t1,t2) -> t1.time.compareTo(t2.time));
        LocalDateTime minTime = list.get(0).time;
        int[] xData = new int[points];
        for(int i = 0; i < points; i++) {
            xData[i] = Period.between(minTime.toLocalDate(), list.get(i).time.toLocalDate()).getDays() * 50 + cX;
        }
        // создадим массив координат y
        int[] yData = list.stream()
                .mapToInt(y -> cY - y.temperature * 10)
                .toArray();

        class PaintWeather extends JPanel {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D graphics2D = (Graphics2D)graphics;
                graphics2D.drawLine(cX, cY, cX, cX); // рисуем оси
                graphics2D.drawLine(cX, cY, cY + 100, cY);
                graphics2D.drawPolyline(xData, yData, points); // рисуем график
            }
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new PaintWeather(), BorderLayout.CENTER);

        JFrame frame = new JFrame("График температур");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

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

        int n = JOptionPane.showConfirmDialog(null, "Подтверждаете ?");
        System.out.println(n);
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

    public static void pressedKey() {
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                char key = keyEvent.getKeyChar();
                System.out.println("Нажата и отпущена клавиша - " + key);
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                char key = keyEvent.getKeyChar();
                System.out.println("Нажата клавиша - " + key);
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                char key = keyEvent.getKeyChar();
                System.out.println("Отпущена клавиша - " + key);
            }
        };

        JTextField textField = new JTextField(20);
        textField.addKeyListener(listener);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(textField, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void drawTable() {
        String[] columnNames = {
                "Name",
                "Last modified",
                "Type",
                "Size"};
        String[][] data = {
                {"addins", "02.11.2006 19:15", "Folder", ""},
                {"AppPatch", "03.10.2006 14:10", "Folder", ""},
                {"assembly", "02.11.2006 14:20", "Folder", ""},
                {"Boot", "13.10.2007 10:46", "Folder", ""},
                {"Branding", "13.10.2007 12:10", "Folder", ""},
                {"Cursors", "23.09.2006 16:34", "Folder", ""},
                {"Debug", "07.12.2006 17:45", "Folder", ""},
                {"Fonts", "03.10.2006 14:08", "Folder", ""},
                {"Help", "08.11.2006 18:23", "Folder", ""},
                {"explorer.exe", "18.10.2006 14:13", "File", "2,93MB"},
                {"helppane.exe", "22.08.2006 11:39", "File", "4,58MB"},
                {"twunk.exe", "19.08.2007 10:37", "File", "1,08MB"},
                {"nsreg.exe", "07.08.2007 11:14", "File", "2,10MB"},
                {"avisp.exe", "17.12.2007 16:58", "File", "12,67MB"},};

        JFrame frame = new JFrame("Test frame");
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
