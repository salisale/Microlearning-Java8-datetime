package helper;

public class Printer {
    public static void print(Object... params) {
        newLine();
        for (Object obj: params) {
            print(obj.toString());
        }
        newLine();
    }
    public static void newLine() {
        System.out.println('\n');
    }
    public static void print(String str) {
        System.out.println("\n> "+str+"\n");
    }
    
}
