package com.itproger.blog.generator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class imageGenerator {

    public static int imgWidth = 512;
    public static int imgHeight = 512;
    public static double rarity_background;
    public static ArrayList<Object> get_item(File folder_name, int percent){

        ArrayList<Object> result = new ArrayList<Object>();
        String pathToFolder = String.format("%s/%d/", folder_name, percent);
        File itemFolder = new File(pathToFolder);
        File[] item_list = itemFolder.listFiles();
        if((item_list == null) || (item_list.length == 0)){
            pathToFolder = String.format("%s/1.png", "src/items/none");
            File item = new File(pathToFolder);
            result.add(percent);
            result.add(item);
        }
        else{
            int chosen_item = 0 + (int) (Math.random() * item_list.length);
            result.add(percent);
            result.add(item_list[chosen_item]);
        }

        return result;
    }



    public static ArrayList<Object> percent_checker(File folder_name){
        int rare_percent = 1 + (int) (Math.random() * 100);
        //System.out.println("Выпал " + folder_name.getName() + " редкостью " + rare_percent + '%');
        boolean onepercent = false;
        boolean hundredpercent = false;
        //Используются вероятности выпадения 1%, 35%, 65%, 100% (для skin).

        String[] folderlist = folder_name.list();
        for (String s : folderlist) {
            if (s.equals("1")) {
                onepercent = true;
                break;
            }
        }
        for (String s : folderlist) {
            if (s.equals("100")) {
                hundredpercent = true;
                break;
            }
        }


        if( (rare_percent <= 1) && (onepercent) ){
            return get_item(folder_name, 1);
        }
        else if(rare_percent <= 35){
            return get_item(folder_name, 35);
        }
        else if(rare_percent <=65){
            return get_item(folder_name, 65);
        }
        else if(hundredpercent){
            return get_item(folder_name, 100);
        }
        else {
            //File none = new File("src/items/none");
            return get_item(folder_name, 100);
        }
    }

    public static Color GenerateColor(){
        int redc = 0 + (int) (Math.random() * 256);
        int greenc = 0 + (int) (Math.random() * 256);
        int bluec = 0 + (int) (Math.random() * 256);
        return new Color(redc, greenc, bluec);
    }

    public static File GenerateSkin(){
        File folder_name = new File ("src/items/skin");
        ArrayList<Object> data = new ArrayList<Object>();
        data = percent_checker(folder_name);
        //System.out.println("Скин: " + data.get(0) + '%');
        return (File) data.get(1);

    }

    public static File GenerateClothes(){
        File folder_name = new File ("src/items/clothes");
        ArrayList<Object> data = new ArrayList<Object>();
        data = percent_checker(folder_name);
        //System.out.println("Одежда: " + data.get(0) + '%');
        return (File) data.get(1);

    }

    public static File GenerateEyes(){
        File folder_name = new File ("src/items/eyes");
        ArrayList<Object> data = new ArrayList<Object>();
        data = percent_checker(folder_name);
        //System.out.println("Глаза: " + data.get(0) + '%');
        return (File) data.get(1);

    }

    public static File GenerateHair(){
        File folder_name = new File ("src/items/hair");
        ArrayList<Object> data = new ArrayList<Object>();
        data = percent_checker(folder_name);
        //System.out.println("Волосы: " + data.get(0) + '%');
        return (File) data.get(1);

    }

    public static File GenerateHat(){
        File folder_name = new File ("src/items/hat");
        ArrayList<Object> data = new ArrayList<Object>();
        data = percent_checker(folder_name);
        //System.out.println("Головной убор: " + data.get(0) + '%');
        return (File) data.get(1);

    }

    public static BufferedImage GenerateBackground(){
        BufferedImage background = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = background.createGraphics();
        int cases = 4;
        int choice_pattern = 0 + (int) (Math.random() * cases); //ds
        double rarity = 1.0/cases;

        switch (choice_pattern) {
            case 0 -> { //Заливка
                g2d.setColor(GenerateColor());
                //Заполняем квадрат цветом
                g2d.fillRect(0, 0, background.getWidth(), background.getHeight());
                rarity *= (1.0/256.0);
            }
            case 1 -> { // Двухцветный флаг
                //Первый цвет
                g2d.setColor(GenerateColor());
                g2d.fillRect(0, 0, background.getWidth(), background.getHeight() / 2);
                rarity *= (1.0/256.0);
                // Второй цвет
                g2d.setColor(GenerateColor());
                g2d.fillRect(0, 256, background.getWidth(), background.getHeight());
                rarity *= (1.0/256.0);
            }
            case 2 -> { //Трехцветный горизонтальный
                g2d.setColor(GenerateColor());
                g2d.fillRect(0, 0, background.getWidth(), background.getHeight() / 3);
                rarity *= (1.0/256.0);
                // Второй цвет
                g2d.setColor(GenerateColor());
                g2d.fillRect(0, background.getHeight() / 3, background.getWidth(), 2 * background.getHeight() / 3);
                rarity *= (1.0/256.0);
                // Третий цвет
                g2d.setColor(GenerateColor());
                g2d.fillRect(0, 2 * background.getHeight() / 3, background.getWidth(), background.getHeight());
                rarity *= (1.0/256.0);
            }
            case 3 -> { //Трехцветный вертикальный
                g2d.setColor(GenerateColor());
                g2d.fillRect(0, 0, background.getWidth()/3, background.getHeight());
                rarity *= (1.0/256.0);

                g2d.setColor(GenerateColor());
                g2d.fillRect(background.getWidth()/3, 0, 2 * background.getWidth() / 3, background.getHeight());
                rarity *= (1.0/256.0);

                g2d.setColor(GenerateColor());
                g2d.fillRect(2 * background.getWidth() / 3, 0, background.getWidth(), background.getHeight());
                rarity *= (1.0/256.0);
            }
        }
        rarity_background = rarity;
        return background;
    }





    public static int Generate() throws IOException {


        InputStream inputStream = System.in;

        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        // System.out.println("Сколько изображений вы хотите сгенерировать?");
        //int amount = Integer.parseInt(bufferedReader.readLine());
        int amount = new File("src/main/resources/results").listFiles().length;
        System.out.println("Файлов в папке:" + amount);
        //Открытие изображения со скином
        File fileSkin = GenerateSkin();
        BufferedImage skin = ImageIO.read(fileSkin);
        //Открытие изображения с одеждой
        File fileClothes = GenerateClothes();
        BufferedImage clothes = ImageIO.read(fileClothes);
        //Открытие изображения с глазами
        File fileEyes = GenerateEyes();
        BufferedImage eyes = ImageIO.read(fileEyes);
        //Открытие изображения с волосами
        File fileHair = GenerateHair();
        BufferedImage hair = ImageIO.read(fileHair);
        //Открытие изображения с головным убором
        File fileHat = GenerateHat();
        BufferedImage hat = ImageIO.read(fileHat);




        //Объединение изображений (По очереди накладываем каждый слой)
        BufferedImage result = new BufferedImage(skin.getWidth(), skin.getHeight(), BufferedImage.TYPE_INT_ARGB);

        result.getGraphics().drawImage(GenerateBackground(), 0, 0, null);
        result.getGraphics().drawImage(skin, 0, 0, null);
        result.getGraphics().drawImage(clothes, 0, 0, null);
        result.getGraphics().drawImage(hair,0, 0, null);
        result.getGraphics().drawImage(eyes, 0, 0, null);
        result.getGraphics().drawImage(hat, 0, 0, null);

        String outputPath = String.format("src/main/resources/results/%d.png", amount++);
        File output = new File(outputPath);
        ImageIO.write(result, "png", output);
        //Записываем текст в файл
            /*
            String rarityPath = String.format("results/%d.txt", i);
            FileWriter rarity_file = new FileWriter(rarityPath, false);
            String text = String.format("Редкость фона: %.10f", rarity_background*100);
            rarity_file.write(text);
            rarity_file.append('%');
            rarity_file.flush();
            */

        /*int amount = 5;
        String filePath = new File("").getAbsolutePath();
        System.out.println(filePath); // C/users/ASUS/Desktop/springboot/ft2

         */
        return amount;
    }

}
