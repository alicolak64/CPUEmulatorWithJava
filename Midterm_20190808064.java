import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Ali Çolak
 * @StudentNumber 20190808064
 * 23.05.2022
 */

public class Midterm_20190808064 {

    public static boolean STATUS = false;
    public static int AC, PC, F = 0;
    public static int[] M = new int[256];
    public static ArrayList<String> INSTRUCTIONS ;

    public static void main(String[] args) {

        INSTRUCTIONS = getData(args[0]);  // instructions.txt

        for (int i = 0; i  < INSTRUCTIONS.size(); i++) {

            String[] currentInstruction = INSTRUCTIONS.get(i).split(" ");

            PC = i + 1;

            String instruction = currentInstruction[0];
            int value = 0;

            if (!(instruction.equals("START") || instruction.equals("DISP") || instruction.equals("HALT"))) {
                value = Integer.parseInt(currentInstruction[1]);
            }

            if (STATUS || instruction.equals("START"))
                switch (instruction) {
                    case "START" ->
                            START();
                    case "LOAD" ->
                            LOAD(value);
                    case "LOADM" ->
                            LOADM(value);
                    case "STORE" ->
                            STORE(value);
                    case "CMPM" ->
                            CMPM(value);
                    case "CJMP" -> {
                        CJMP(value);
                        i = PC - 1;
                    }
                    case "JMP" -> {
                        JMP(value);
                        i = PC - 1;
                    }
                    case "ADD" ->
                            ADD(value);
                    case "ADDM" ->
                            ADDM(value);
                    case "SUBM" ->
                            SUBM(value);
                    case "SUB" ->
                            SUB(value);
                    case "MUL" ->
                            MUL(value);
                    case "MULM" ->
                            MULM(value);
                    case "DISP" ->
                            DISP();
                    case "HALT" ->
                            HALT();
                }

        }

    }

    private static void START() {
        STATUS = true;
    }

    private static void LOAD(int value) {
        AC = value;
    }

    private static void LOADM(int index) {
        AC = M[index];
    }

    private static void STORE(int index) {
        M[index] = AC;
    }

    private static void CMPM(int index) {
        if (AC > M[index]) {
            F = 1;
        } else if (AC < M[index]) {
            F = -1;
        } else {
            F = 0;
        }
    }

    private static void CJMP(int go_to) {
        if (F > 0) {
            PC = go_to;
        }
    }

    private static void JMP(int go_to) {
        PC = go_to;
    }

    private static void ADD(int value) {
        AC += value;
    }

    private static void ADDM(int index) {
        AC += M[index];
    }

    private static void SUB(int value) {
        AC -= value;
    }

    private static void SUBM(int index) {
        AC -= M[index];
    }

    private static void MUL(int value) {
        AC *= value;
    }

    private static void MULM(int index) {
        AC *= M[index];
    }

    private static void DISP() {
        System.out.println(AC);
    }

    private static void HALT() {
        STATUS = false;
    }


    private static ArrayList<String> getData(String path)  {

        ArrayList<String> commands = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine().toUpperCase().trim();
                if (!str.contains("%")) {
                    while (str.contains("  "))
                        str = str.replace("  ", " ");
                    str = str.substring(str.indexOf(" ") + 1);
                    if (!str.equals("")) commands.add(str);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return commands;
    }

}
