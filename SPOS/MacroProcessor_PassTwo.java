
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class MacroProcessor_PassTwo {
    static List<String> MDT;
    static Map<String, String> MNT;
    static Map<String, List<String>> ALA;
    static List<String> actualParams;
    static int mdtPtr;

    public static void main(String[] args) {
        try {
            initiallizeTables();
            pass2();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void pass2() throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("./output_pass1.txt")));
        PrintWriter out_pass2 = new PrintWriter(new FileWriter("./output_pass2.txt"), true);

        System.out.println("============= Pass 2 Output ==============");

        String s;
        while ((s = input.readLine()) != null) {
            String[] s_arr = tokenizeString(s, " ");
            if (MNT.containsKey(s_arr[0])) {
                // Macro call detected
                String macroName = s_arr[0];
                actualParams.clear();

                // Collect actual parameters
                String[] actual_params_arr = tokenizeString(s_arr[1], ",");
                for (String param : actual_params_arr) {
                    if (param.contains("=")) {
                        param = param.substring(param.indexOf("=") + 1);// it takes CREG from REG=CREG
                    }
                    actualParams.add(param);//it first add N1,then N2,then CREG
                }

                // Begin macro expansion
                mdtPtr = Integer.parseInt(MNT.get(macroName));
                List<String> formalParamList = ALA.get(macroName);
				//System.out.println(formalParamList);

                String macroLine;
                boolean skipHeader = true;

                while (true) {
                    macroLine = MDT.get(mdtPtr++);
                    String[] tokens = tokenizeString(macroLine, " ");

                    if (tokens[0].equalsIgnoreCase("MEND")) break;
                    if (skipHeader) {
                        skipHeader = false;
                        continue; // skip the macro header (name + args)
                    }

                    String mnemonic = tokens[0];
                    String params = tokens.length > 1 ? tokens[1] : "";

                    String expandedParams = replaceParamsUsingALA(params, formalParamList);//substitute formal parameter with actual parameter
                    String expandedLine = "+" + mnemonic + " " + expandedParams;

                    System.out.println(expandedLine);
                    out_pass2.println(expandedLine);
                }
            } else {
                // Normal assembly line
                System.out.println(s);
                out_pass2.println(s);
            }
        }

        input.close();
        out_pass2.close();
    }

    static String replaceParamsUsingALA(String paramList, List<String> formalParams) {
        if (paramList.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        String[] paramTokens = tokenizeString(paramList.replace("#", ""), ",");

        for (String token : paramTokens) {
            int index = Integer.parseInt(token);
            String value = (index <= actualParams.size())
                ? actualParams.get(index - 1)
                : formalParams.get(index - 1);
            result.append(value).append(",");
        }

        if (result.length() > 0) {
            result.setLength(result.length() - 1); // remove trailing comma
        }

        return result.toString();
    }

    static void initiallizeTables() throws Exception {
        MDT = new ArrayList<>();
        MNT = new LinkedHashMap<>();
        ALA = new LinkedHashMap<>();
        actualParams = new ArrayList<>();

        BufferedReader br;
        String s;

        // Load MNT
        br = new BufferedReader(new InputStreamReader(new FileInputStream("./MNT.txt")));
        while ((s = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(s, " ");
            MNT.put(st.nextToken(), st.nextToken());
        }
        br.close();

        // Load MDT
        br = new BufferedReader(new InputStreamReader(new FileInputStream("./MDT.txt")));
        while ((s = br.readLine()) != null) {
            int index = Integer.parseInt(s.substring(0, s.indexOf(" ")).trim());
            String content = s.substring(s.indexOf(" ") + 1);
            while (MDT.size() <= index) MDT.add(""); // pad with empty entries
            MDT.set(index, content);
        }
        br.close();

        // Load ALA
        br = new BufferedReader(new InputStreamReader(new FileInputStream("./ALA.txt")));
        String macroName = null;
        List<String> paramList = null;

        while ((s = br.readLine()) != null) {
            if (s.trim().isEmpty()) continue;

            if (s.startsWith("Macro:")) {
                macroName = s.substring(s.indexOf(":") + 1).trim();
                paramList = new ArrayList<>();
                ALA.put(macroName, paramList);
            } else if (macroName != null) {
                String[] tokens = s.split("->");
                if (tokens.length == 2) {
                    String param = tokens[0].trim();          // e.g., &X
                    String position = tokens[1].trim();       // e.g., #1
                    int index = Integer.parseInt(position.replace("#", ""));
                    while (paramList.size() < index) paramList.add(""); // pad
                    paramList.set(index - 1, param);
                }
            }
        }
        br.close();
    }

    static String[] tokenizeString(String str, String separator) {
        StringTokenizer st = new StringTokenizer(str, separator, false);
        String[] s_arr = new String[st.countTokens()];
        for (int i = 0; i < s_arr.length; i++) {
            s_arr[i] = st.nextToken();
        }
        return s_arr;
    }
}

