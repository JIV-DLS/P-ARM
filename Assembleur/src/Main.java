
import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private static final String FILE_NAME = "ual.txt";
    private static final String OUT_FILE_NAME = "out.txt";
    private static ArrayList<String> file_lines = new ArrayList<>();

    public static void main(String[] args) {
        String hexa_code = ""; //Where to store each hexa code to be written on the output file
        String binary_code = ""; //Where to store each binary code to be converted in hexa
        String instruction = ""; //Where to store the name of the instruction
        String line = ""; //The line we'll be working with
        String[] splited_line = new String[10]; //Where to store each word of the line
        int position = 0;

        try {
            // Opening a scanner to read the file ual.txt, it must be in the folder ressources !
            Scanner scanner = new Scanner(new File(Main.class.getClassLoader().getResource(FILE_NAME)
                    .getPath()));
            //We read each line of the text file
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                line = line.replace(",","");
                line = line.replace("[","");
                line = line.replace("]","");
                line = line.replaceAll(" {2,}"," ");
                if (line.length() != 0)
                if(line_not_commentary(line)) {
                    file_lines.add(line.trim()); //We store the line we'll work with to remove unnecessary
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Main.class.getClassLoader().getResource(OUT_FILE_NAME)
                    .getPath()));
            writer.write("v2.0 raw\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


            for (String file_line : file_lines) {
                line = file_line;
                List<Object> start = Main.initialize(splited_line, hexa_code, binary_code, line);
                splited_line = (String[]) start.get(0);
                hexa_code = (String) start.get(1);
                binary_code = (String) start.get(2);
                line = (String) start.get(3); // Getting back everything initialized
                instruction = splited_line[0];

                for (String string : splited_line){
                //System.out.println(string);
                //System.out.println("");
            }


                //LSL - immediate
                if (instruction.equals("lsls") && splited_line.length == 4){
                    binary_code = "00000";
                    binary_code += decimal_to_binary (splited_line[3].substring(1)+"",5);
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //LSR - immediate
                if (instruction.equals("lsrs") && splited_line.length == 4){
                    binary_code = "00001";
                    binary_code += decimal_to_binary (splited_line[3].substring(1)+"",5);
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //ASR
                if (instruction.equals("asrs") && splited_line.length == 4){
                    binary_code = "00010";
                    binary_code += decimal_to_binary (splited_line[3].substring(1)+"",5);
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //ADD
                if (instruction.equals("adds")){
                    if (is_immediate(splited_line)){
                        binary_code = "0001110";
                    }else{
                        binary_code = "0001100";
                    }

                    binary_code += decimal_to_binary (splited_line[3].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //SUB
                if (instruction.equals("subs")){
                    if (is_immediate(splited_line)){
                        binary_code = "0001111";
                    }else{
                        binary_code = "0001101";
                    }
                    binary_code += decimal_to_binary (splited_line[3].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //MOV
                if (instruction.equals("movs") || instruction.equals("mov")){
                    binary_code = "00100";
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",8);
                }

                //AND
                if (instruction.equals("ands")){
                    binary_code = "0100000000";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //EOR
                if (instruction.equals("eors")){
                    binary_code = "0100000001";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //LSL
                if (instruction.equals("lsls") && splited_line.length == 3){
                    binary_code = "0100000010";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //LSR
                if (instruction.equals("lsrs") && splited_line.length == 3){
                    binary_code = "0100000011";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //ASR
                if (instruction.equals("asrs") && splited_line.length == 3){
                    binary_code = "0100000100";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //ADC
                if (instruction.equals("adcs")){
                    binary_code = "0100000101";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //SBC
                if (instruction.equals("sbcs")){
                    binary_code = "0100000110";
                    binary_code += decimal_to_binary (splited_line[2].charAt(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].charAt(1)+"",3);
                }

                //ROR
                if (instruction.equals("rors")){
                    binary_code = "0100000111";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //TST
                if (instruction.equals("tst")){
                    binary_code = "0100001000";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //RSB
                if (instruction.equals("rsbs")){
                    binary_code = "0100001001";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //CMP
                if (instruction.equals("cmp")){
                    binary_code = "0100001010";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //CMN
                if (instruction.equals("cmn")){
                    binary_code = "0100001011";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //ORR
                if (instruction.equals("orrs")){
                    binary_code = "0100001100";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //MUL
                if (instruction.equals("muls") || instruction.equals("mul")){
                    binary_code = "0100001101";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //BIC
                if (instruction.equals("bics")){
                    binary_code = "0100001110";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //MVN
                if (instruction.equals("mvns")){
                    binary_code = "0100001111";
                    binary_code += decimal_to_binary (splited_line[2].substring(1)+"",3);
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                }

                //STR
                if (instruction.equals("str")){
                    binary_code = "10010";
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                    if (is_immediate(splited_line)){
                        binary_code += decimal_to_binary (splited_line[splited_line.length-1].substring(1)+"",8);
                    }else{
                        binary_code += "00000000";
                    }
                }

                //LDR
                if (instruction.equals("ldr")){
                    binary_code = "10011";
                    binary_code += decimal_to_binary (splited_line[1].substring(1)+"",3);
                    if (is_immediate(splited_line)){
                        binary_code += decimal_to_binary (splited_line[splited_line.length-1].substring(1)+"",8);
                    }else{
                        binary_code += "00000000";
                    }
                }

                //ADD
                if (instruction.equals("add")){
                    binary_code = "101100000";
                    binary_code += decimal_to_binary (splited_line[splited_line.length - 1].substring(1)+"",7);
                }

                //SUB
                if (instruction.equals("sub")){
                    binary_code = "101100001";
                    binary_code += decimal_to_binary (splited_line[splited_line.length - 1].substring(1)+"",7);
                }

                //SUB
                if (!instruction.equals("bics") && instruction.charAt(0) == 'b'){
                    binary_code = "1101";
                    String condition = null;
                    if(instruction.length() == 1){
                        binary_code += "1110";
                    }else{
                        condition = instruction.substring(1);
                    }
                    if (condition != null)
                    switch (condition){
                        case "eq":
                            binary_code += "0000";

                        case "ne":
                            binary_code += "0001";
                            break;

                        case "cs":
                            binary_code += "0010";
                            break;

                        case "cc":
                            binary_code += "0011";
                            break;

                        case "mi":
                            binary_code += "0100";
                            break;

                        case "pl":
                            binary_code += "0101";
                            break;

                        case "vs":
                            binary_code += "0110";
                            break;

                        case "vc":
                            binary_code += "0111";
                            break;

                        case "hi":
                            binary_code += "1000";
                            break;

                        case "ls":
                            binary_code += "1001";
                            break;

                        case "ge":
                            binary_code += "1010";
                            break;

                        case "lt":
                            binary_code += "1011";
                            break;

                        case "gt":
                            binary_code += "1100";
                            break;

                        case "le":
                            binary_code += "1101";
                            break;

                        case "al":
                            binary_code += "1110";
                            break;
                    }
                    binary_code += distance_to_label(position,splited_line[1]);

                }
                try {
                    if (binary_code.length() !=0 ) {
                        int decimal = Integer.parseInt(binary_code, 2);
                        String hexStr = Integer.toString(decimal, 16);
                        write_in_file(hexStr);
                        System.out.println(file_line+" "+hexStr);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                position++;
            }

    }

    private static void write_in_file(String binary_code) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(Main.class.getClassLoader().getResource(OUT_FILE_NAME)
                .getPath(),true));
        writer.append(binary_code+ " ");
        writer.close();
    }

    private static boolean line_not_commentary(String line) {
        return !line.contains("@");
    }

    private static String distance_to_label(int position, String s) {
        int result = 0; String result_distance; boolean negative;
        s += ":";
        int to_position = file_lines.indexOf(s);
        negative = (to_position - position < 0);
        if (!negative){

            for (int i = position; i < to_position;i++){
                if (!file_lines.get(i).contains("@")&& !(file_lines.get(i).charAt(0) == '.') ){
                   result ++;
                }
            }
        }else{
            for (int i = to_position; i < position;i++){
                if (!file_lines.get(i).contains("@")&& !(file_lines.get(i).charAt(0) == '.') ){
                    result++;
                }
            }
        }
        result_distance = decimal_to_binary(Integer.toString(result),8);
        return result_distance;
    }

    private static boolean is_immediate(String[] array) {
        for (String s : array){
            if (s.charAt(0) == '#') return true;
        }
        return false;
    }

    private static String decimal_to_binary(String argument, int length) {
        if (argument.charAt(0) == '#') argument = argument.substring(1);
        String result;
        int argument_int = Integer.parseInt(argument);


        //!!!
        if (argument_int >= 0){
            result = Integer.toBinaryString(Integer.parseInt(argument));
        }else{
            result = Integer.toBinaryString(Integer.parseInt(argument));
            while (result.charAt(0) == '1' && result.length() >= length){
                result = result.substring(1);
            }
            result = '1'+result;
        }

        while (result.length() < length){
            result = "0"+result;
        }
        return result;
    }

    public static List<Object> initialize (String[] splited_line, String hexa_code, String binary_code, String line){
        splited_line = new String[10];
        hexa_code = ""; binary_code = ""; //Initializing the code back to nothing
        line = line.replace(",","");
        line = line.replace("[","");
        line = line.replace("]","");
        line = line.replaceAll(" {2,}"," ");
        line = line.trim();
        splited_line = line.split(" ");
        return Arrays.asList(splited_line,hexa_code,binary_code,line);
    }

    public static int getTwosComplement(String binaryInt) {
        //Check if the number is negative.
        //We know it's negative if it starts with a 1
        if (binaryInt.charAt(0) == '1') {
            //Call our invert digits method
            String invertedInt = invertDigits(binaryInt);
            //Change this to decimal format.
            int decimalValue = Integer.parseInt(invertedInt, 2);
            //Add 1 to the curernt decimal and multiply it by -1
            //because we know it's a negative number
            decimalValue = (decimalValue + 1) * -1;
            //return the final result
            return decimalValue;
        } else {
            //Else we know it's a positive number, so just convert
            //the number to decimal base.
            return Integer.parseInt(binaryInt, 2);
        }
    }

    public static String invertDigits(String binaryInt) {
        String result = binaryInt;
        result = result.replace("0", " "); //temp replace 0s
        result = result.replace("1", "0"); //replace 1s with 0s
        result = result.replace(" ", "1"); //put the 1s back in
        return result;
    }

}