import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static String[] allRomansNum = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    public static String caseFlag = null;
    static String[] Arr = new String[0];

    public static void GetCaseFlagAndSplitString(String input) {
        Arr = input.split("[-+*/]");
        if (input.contains("+")) {
            caseFlag = "+";
        } else if (input.contains("-")) {
            caseFlag = "-";
        } else if (input.contains("*")) {
            caseFlag = "*";
        } else if (input.contains("/")) {
            caseFlag = "/";
        } else {
            throw new IllegalArgumentException("Строка не является математической операцией");
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

    public static String RomanToArabic(String str) {
        int i = 0;
        for(int x = 0; x < allRomansNum.length; x++){
            if (allRomansNum[x].equals(str)){
                i = x;
            }
        }
        return String.valueOf(i);
    }

    public static void CheckExceptions(String[] newString) {
        if (CheckArabiсNum(newString[0]) && CheckArabiсNum(newString[1])) {
            int i = Integer.parseInt(newString[0]);
            int k = Integer.parseInt(newString[1]);
            if (i < 1 || i > 10 || k < 1 || k > 10) throw new IllegalArgumentException("Введите два целых числа арабскими или римскими числами от 1 до 10 .");
        }
    }

    public static void CheckRomanException(int i) {
        if (i <= 0) throw new IllegalArgumentException("B римской системе нет отрицательных чисел и нуля");
    }

    public static String calc(String input) {
        int result = 0;
        String firstNum, secondNum;
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
            firstNum = RomanToArabic(Arr[0]);
            secondNum = RomanToArabic(Arr[1]);
            result = GetResultOfNums(firstNum, secondNum);
            CheckRomanException(result);
            int nums[] = new int[]{100, 90, 50, 40, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
            Map<Integer, String> dict = new HashMap<>();
            {
                dict.put(100, "C");dict.put(90, "XC");dict.put(50, "L");dict.put(40, "XL");dict.put(10, "X");dict.put(9, "IX");dict.put(8, "VIII");
                dict.put(7, "VII");dict.put(6, "VI");dict.put(5, "V");dict.put(4, "IV");dict.put(3, "III");dict.put(2, "II");dict.put(1, "I");
            }
            int keyOfMap = 0;
            while (result != 0) {
                for (int i = 0; i < nums.length; i++) {
                    int x = 0;
                    x = result / nums[i];
                    if (x > 0) {
                        keyOfMap = nums[i];
                        resultStr += dict.get(keyOfMap);
                        result -= keyOfMap;
                        break;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Введите два одинаковых числа одной из систем счисления: арабскими (1,2,3,4,5...10) или римскими числами (I, II, III, IV, V...X) от 1 до 10 включительно.");
        }
        return resultStr;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(calc(str));
		}
}