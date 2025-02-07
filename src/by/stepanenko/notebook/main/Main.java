package by.stepanenko.notebook.main;

import by.stepanenko.notebook.controller.Controller;
import by.stepanenko.notebook.entity.ControllerParams;
import by.stepanenko.notebook.utils.YesNoValidator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String response;
        String menuItem = "";
        Scanner scanner = new Scanner(System.in);
        Scanner data = new Scanner(System.in);
        Controller controller = new Controller();
        YesNoValidator yesNoValidator = new YesNoValidator();
        ControllerParams controllerParams;
        String needSave = "";

        String menu = """
                1 - Создать Блокнот
                2 - Добавить запись в Блокнот
                3 - Найти записи в блокноте по содержимому
                4 - Найти записи в блокноте по дате создания
                5 - Показать записи в блокноте
                6 - Изменить запись
                7 - Сохранить блокнот
                0 - Выход""";

        while (!menuItem.equals("0")){
            System.out.println(menu);
            menuItem = scanner.nextLine();

            controllerParams = new ControllerParams();

            switch (menuItem){
                case ("1"):
                    controllerParams.setCommandName("CREATENEWFILE");
                    break;
                case ("2"):
                    controllerParams.setCommandName("ADD");

                    System.out.println("Введите данные для создания новой записи. Символ ';' не допускается.");
                    controllerParams.setContent(data.nextLine());
                    break;
                case ("3"):
                    controllerParams.setCommandName("GETNOTESBYCONTENT");

                    System.out.println("Введите данные для поиска записей по содержимому.");
                    controllerParams.setContent(data.nextLine());
                    break;
                case ("4"):
                    controllerParams.setCommandName("GETNOTEBYDATE");

                    System.out.println("Введите дату для поиска записей по дате в формате (гггг-мм-дд):");
                    controllerParams.setDate(data.nextLine());
                    break;
                case ("5"):
                    controllerParams.setCommandName("GETALLNOTES");
                    break;
                case ("6"):
                    controllerParams.setCommandName("UPDATENOTEBYID");

                    System.out.println("Введите id записи, которую хотите изменить");
                    controllerParams.setId(scanner.nextLine());

                    System.out.println("Введите новое значение");
                    controllerParams.setContent(scanner.nextLine());
                    break;
                case ("7"):
                    controllerParams.setCommandName("SAVE");
                    break;
                case ("0"):
                    controllerParams.setCommandName("EXIT");
                    do {
                        System.out.println("Желаете сохранить блокнот? (Y/N)");
                        needSave = scanner.nextLine();
                        if (needSave.equalsIgnoreCase("y")){
                            controllerParams.setCommandName("SAVE");
                        }
                    } while (!yesNoValidator.match(needSave));
                    break;
                default:
                    controllerParams.setCommandName("WRONG_REQUEST");
            }
            response = controller.doAction(controllerParams);
            System.out.println(response);
        }
    }
}
