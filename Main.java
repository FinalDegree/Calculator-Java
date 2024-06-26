import java.util.*;

public class Main {
    public static String caseFlag = null;
    public static String[] elements = new String[0];
    public static int nums[] = new int[]{100, 90, 50, 40, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
    public static Map<Integer, String> dict = new HashMap<>(); static{ dict.put(100, "C"); dict.put(90, "XC"); dict.put(50, "L"); dict.put(40, "XL"); dict.put(10, "X"); dict.put(9, "IX"); dict.put(8, "VIII"); dict.put(7, "VII"); dict.put(6, "VI"); dict.put(5, "V"); dict.put(4, "IV"); dict.put(3, "III"); dict.put(2, "II"); dict.put(1, "I");}
    public static void GetCaseFlagAndSplitString(String input) {
        elements = input.split("[-+*/]");
        if (input.contains("+")) caseFlag = "+";
        else if (input.contains("-")) caseFlag = "-";
        else if (input.contains("*")) caseFlag = "*";
        else if (input.contains("/")) caseFlag = "/";
        else throw new IllegalArgumentException("Строка не является математической операцией");
    }

    public static boolean IsArabicNum(String x){
        try { int i = Integer.parseInt(x); } catch (Exception e) { return false; }
        return true;
    }

    public static boolean IsRomanNum(String y){
        for (int x : nums)
            if (dict.get(x).equals(y)) return true;
        return false;
    }

    public static int GetResultOfNums(String a, String b) {
        int result = 0;
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        if (x < 1 || x > 10 || y < 1 || y > 10) throw new IllegalArgumentException("Введите два целых числа арабскими или римскими числами от 1 до 10 .");
        switch (caseFlag) {
            case "+": return x + y;
            case "-": return x - y;
            case "*": return x * y;
            case "/": return x / y;
        }
        return result;
    }

    public static String GetIntForRomanNum(String str) {
        int i = 0;
        for(int x = 0; x < nums.length; x++)
            if (dict.get(nums[x]).equals(str)) i = nums[x];
        return String.valueOf(i);
    }
    
    public static String calc(String input) {
        int result = 0;
        String resultString = "";
        input = input.replaceAll("\\s", "");
        GetCaseFlagAndSplitString(input);
        if (IsArabicNum(elements[0]) && IsArabicNum(elements[1]) && elements.length < 3) {
            result = GetResultOfNums(elements[0], elements[1]);
            String str = String.valueOf(result);
            return str;
        } else if (IsRomanNum(elements[0]) && IsRomanNum(elements[1])) {
            result = GetResultOfNums(GetIntForRomanNum(elements[0]), GetIntForRomanNum(elements[1]));
            if (result <= 0) throw new IllegalArgumentException("B римской системе нет отрицательных чисел и нуля");
            for ( ; result > 0; )
                for (int i = 0; i < nums.length; i++) {
                    int x = result / nums[i];
                    if (x > 0) {
                        resultString += dict.get(nums[i]);
                        result -= nums[i];
                        break;
                    }
                }
        } else throw new IllegalArgumentException("Введите два одинаковых числа одной из систем счисления: арабскими (1,2,3,4,5...10) или римскими числами (I, II, III, IV, V...X) от 1 до 10 включительно.");
        return resultString;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(calc(str));
    }
}