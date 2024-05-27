import java.util.Scanner;

public class Main {
    public static String[] allRomansNum = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static String caseFlag = null;
    public static char plus = '+';
    static char minus = '-';
    static char mul = '*';
    static char div = '/';
    static String[] Arr = new String[0];
    
    public static void GetCaseFlagAndSplitString(String input) throws Exception{
        Arr = input.split("[-+*/]");
        if (input.contains(String.valueOf(plus))) {
            caseFlag = "+";
        } else if (input.contains(String.valueOf(minus))) {
            caseFlag = "-";
        } else if (input.contains(String.valueOf(mul))) {
            caseFlag = "*";
        } else if (input.contains(String.valueOf(div))) {
            caseFlag = "/";
        } else {
            throw new Exception("Строка не является математической операцией");
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
        for (String x:allRomansNum){
            if (x.equals(y)){
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
        for(int x = 0; x < allRomansNum.length; x++){
            if (allRomansNum[x].equals(str)){
                i = x;
            }
        }
       return String.valueOf(i);
    }

    public static String GetCypherOfRomanNum(int numb) {
        return allRomansNum[numb];
    }

    public static void CheckExceptions(String[] newString) throws Exception {
         if (CheckArabiсNum(newString[0]) && CheckArabiсNum(newString[1])) {
            int i = Integer.parseInt(newString[0]);
            int k = Integer.parseInt(newString[1]);
            if (i < 1 || i > 10 || k < 1 || k > 10) throw new Exception("Введите два целых числа арабскими или римскими числами от 1 до 10 .");
            }
         }

    public static void CheckRomanExeption(int i) throws Exception{
        if (i <= 0) throw new Exception("B римской системе нет отрицательных чисел и нуля");
    }
    
    public static String calc(String input) throws Exception {
        int a = 0;
        int b = 0;
        int result = 0;

        String firstNum, secondNum;
        String RomanResultNumber = "";
        String resultStr = "";

        input = input.replaceAll("\\s", "");
        GetCaseFlagAndSplitString(input);
        CheckExceptions(Arr);

        if (CheckArabiсNum(Arr[0]) && CheckArabiсNum(Arr[1]) && Arr.length < 3){
            firstNum = Arr[0];
            secondNum = Arr[1];
            result = GetResultOfNums(firstNum, secondNum);
            String str = String.valueOf(result);
            return str;
        }
        else if (CheckRomanNum(Arr[0]) && CheckRomanNum(Arr[1])) {
            firstNum = GetIntForRomanNum(Arr[0]);
            secondNum = GetIntForRomanNum(Arr[1]);
            result = GetResultOfNums(firstNum, secondNum);
            CheckRomanExeption(result);
            if (result != 0 && result <= 10 && result > 0){
                resultStr+= GetCypherOfRomanNum(result);
            } else if (result > 10 && result < 40){
                int count = result / 10;
                int reminder = result % 10;
                for (int i = 0; i < count; i++){
                    resultStr += "X";
                }
                resultStr += GetCypherOfRomanNum(reminder);
            } else if (result >= 40 && result < 50){
                resultStr += "XL";
                int reminder = result - 40;
                resultStr += GetCypherOfRomanNum(reminder);
            } else if (result >= 50 && result < 90){
                resultStr += "L";
                int count = (result - 50) / 10;
                for (int i = 0; i < count; i++) {
                    resultStr += "X";
                }
                int reminder = (result - 50) % 10;
                resultStr += GetCypherOfRomanNum(reminder);
            } else if (result >= 90 && result < 100){
                resultStr += "XC";
            } else if (result == 100) {
                resultStr += "C";
            }
        } else {
            throw new Exception("Введите два одинаковых числа одной из систем счисления: арабскими (1,2,3,4,5...10) или римскими числами (I, II, III, IV, V...X) от 1 до 10 включительно.");
        }
        return resultStr;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(calc(str));
    }
}