import java.util.Scanner;

public class Main {
    public static String caseFlag = null;
    static char plus = '+';
    static char minus = '-';
    static char mul = '*';
    static char div = '/';
    static String[] newString = new String[0];
    public static void GetCaseFlagAndSplitString(String input) {
        if ((input.contains(String.valueOf(plus)) || input.contains(String.valueOf(minus)) || input.contains(String.valueOf(mul)) || input.contains(String.valueOf(div)))) {
            if (input.contains(String.valueOf(plus))) {
                newString = input.split("\\+");
                caseFlag = "+";
            } else if (input.contains(String.valueOf(minus))) {
                newString = input.split("-");
                caseFlag = "-";
            } else if (input.contains(String.valueOf(mul))) {
                newString = input.split("\\*");
                caseFlag = "*";
            } else if (input.contains(String.valueOf(div))) {
                newString = input.split("/");
                caseFlag = "/";
            }
        } else {
            try {
                throw new Exception();
            } catch (Exception e){
                System.out.println("Строка не является математической операцией");
                System.exit(0);
            }
        }
    }

    public static boolean CheckArabiсNum(String x){
        boolean b = true;
        for(int i = 0; i < x.length() && b; i++) {
            if(!Character.isDigit(x.charAt(i))) {
                b = false;
            }
        }
        return b;
    }

    public static boolean CheckRomanNum(String y){
        boolean b = false;
        String[] allRomansNum = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (String s: allRomansNum){
            if (s.equals(y)){
                b = true;
            }
        }
        return b;
    }

    public static int GetResultOfNums(String a, String b) {
        int result = 0;
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        switch (caseFlag) {
            case "+":
                result = x + y;
                break;
            case "-":
                result = x - y;
                break;
            case "*":
                result = x * y;
                break;
            case "/":
                result = x / y;
                break;
        }
        return result;
    }

    public static String GetIntForRomanNum(String str) {
        int i = 0;
        switch (str) {
            case "I":
                i = 1;
                break;
            case "II":
                i = 2;
                break;
            case "III":
                i = 3;
                break;
            case "IV":
                i = 4;
                break;
            case "V":
                i = 5;
                break;
            case "VI":
                i = 6;
                break;
            case "VII":
                i = 7;
                break;
            case "VIII":
                i = 8;
                break;
            case "IX":
                i = 9;
                break;
            case "X":
                i = 10;
                break;
        }
        return String.valueOf(i);
    }

    public static String GetCypherOfRomanNumber(int numb) {
        String RomanResultNumber = "";
        switch (numb){
            case 1:
                RomanResultNumber = "I";
                break;
            case 2:
                RomanResultNumber = "II";
                break;
            case 3:
                RomanResultNumber = "III";
                break;
            case 4:
                RomanResultNumber = "IV";
                break;
            case 5:
                RomanResultNumber = "V";
                break;
            case 6:
                RomanResultNumber = "VI";
                break;
            case 7:
                RomanResultNumber = "VII";
                break;
            case 8:
                RomanResultNumber = "VIII";
                break;
            case 9:
                RomanResultNumber = "IX";
                break;
            case 10:
                RomanResultNumber = "X";
                break;
        }
        return RomanResultNumber;
    }

    public static void CheckExceptions(String[] newString){
        if (newString.length < 3 && CheckArabiсNum(newString[0]) && CheckRomanNum(newString[1]) || CheckRomanNum(newString[0]) && CheckArabiсNum(newString[1])){
            try{
                throw new Exception();
            } catch (Exception e){
                System.out.println("Используются одновременно разные системы счисления");
                System.exit(0);
            }
        } else if (newString.length > 2){
            try{
                throw new Exception();
            } catch (Exception e){
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /,");
                System.exit(0);
            }
        } else if ((!CheckArabiсNum(newString[0]) && !(CheckArabiсNum(newString[1]))) && (!CheckRomanNum(newString[0]) && !CheckRomanNum(newString[1])) || newString[0].contains(".")|| newString[1].contains(".")){
            try{
                throw new Exception();
            } catch (Exception e){
                System.out.println("Введите два целых числа арабскими (1,2,3,4,5...10) или римскими числами (I, II, III, IV, V...X) от 1 до 10 включительно.");
                System.exit(0);
            }
        } else if (CheckArabiсNum(newString[0]) && CheckArabiсNum(newString[1])) {
            int i = Integer.parseInt(newString[0]);
            int k = Integer.parseInt(newString[1]);
            if (i < 1 || i > 10 || k < 1 || k > 10){
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Введите два целых числа арабскими или римскими числами от 1 до 10 .");
                    System.exit(0);
                }
            }
        } else if(CheckArabiсNum(newString[0]) && !CheckRomanNum(newString[1]) && !CheckArabiсNum(newString[1]) || CheckRomanNum(newString[0]) && !CheckArabiсNum(newString[1]) && !CheckRomanNum(newString[1])){
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Введите два целых числа арабскими или римскими числами от 1 до 10 .");
                System.exit(0);
            }
        } else if (!CheckArabiсNum(newString[0]) && !(CheckRomanNum(newString[0])) && CheckArabiсNum(newString[1]) || !CheckArabiсNum(newString[0]) && !CheckRomanNum(newString[0]) && CheckRomanNum(newString[1])){
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Введите два целых числа арабскими или римскими числами от 1 до 10 .");
                System.exit(0);
            }
        }
    }

    public static void CheckRomanExeption(int i){
        if (i <= 0){
            try {
                throw new Exception();
            } catch (Exception e){
                System.out.println("B римской системе нет отрицательных чисел и нуля");
            }
        }
    }
    public static String calc(String input){
        int a = 0;
        int b = 0;
        int result = 0;

        String firstNum, secondNum;
        String RomanResultNumber = "";
        String resultStr = "";

        input = input.replaceAll("\\s", "");
        GetCaseFlagAndSplitString(input);
        CheckExceptions(newString);

        if (CheckArabiсNum(newString[0]) && CheckArabiсNum(newString[1]) && newString.length < 3){
            firstNum = newString[0];
            secondNum = newString[1];
            result = GetResultOfNums(firstNum, secondNum);
            String str = String.valueOf(result);
            return str;
        }
        else if (CheckRomanNum(newString[0]) && CheckRomanNum(newString[1])) {
            firstNum = GetIntForRomanNum(newString[0]);
            secondNum = GetIntForRomanNum(newString[1]);
            result = GetResultOfNums(firstNum, secondNum);
            CheckRomanExeption(result);
            if (result != 0 && result <= 10 && result > 0){
                resultStr+=GetCypherOfRomanNumber(result);
            } else if (result > 10 && result < 40){
                int count = result / 10;
                int reminder = result % 10;
                for (int i = 0; i < count; i++){
                    resultStr += "X";
                }
                resultStr += GetCypherOfRomanNumber(reminder);
            } else if (result >= 40 && result < 50){
                resultStr += "XL";
                int reminder = result - 40;
                resultStr += GetCypherOfRomanNumber(reminder);
            } else if (result >= 50 && result < 90){
                resultStr += "L";
                int count = (result - 50) / 10;
                for (int i = 0; i < count; i++) {
                    resultStr += "X";
                }
                int reminder = (result - 50) % 10;
                resultStr += GetCypherOfRomanNumber(reminder);
            } else if (result >= 90 && result < 100){
                resultStr += "XC";
            } else if (result == 100) {
                resultStr += "C";
            }
        }
        return resultStr;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(calc(str));
    }
}