import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Race {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regexForLetters = "[A-Za-z]";
        String regexForDigits = "[0-9]";
        Pattern patternForLetters = Pattern.compile(regexForLetters);
        Pattern patternForDigits = Pattern.compile(regexForDigits);
        String[] participants = scanner.nextLine().split(", ");
        Map<String, Integer> results = new LinkedHashMap<>();

        String input = scanner.nextLine();

        while (!"end of race".equals(input)){
            StringBuilder name = new StringBuilder();
            String nameToString ="";
            int distance = 0;

            for (int i = 0; i < input.length(); i++) {
                String currentChar = String.valueOf(input.charAt(i));
                Matcher matcherForLetters = patternForLetters.matcher(currentChar);
                Matcher matcherForDigits = patternForDigits.matcher(currentChar);
                if (matcherForLetters.find()){
                    name.append(currentChar);
                }else if (matcherForDigits.find()){
                    distance += Integer.parseInt(currentChar);
                }
            }
            nameToString = String.valueOf(name);

            for (String item : participants) {
                if (item.equals(nameToString)){
                    results.putIfAbsent(nameToString, 0);
                    results.put(nameToString, results.get(nameToString) + distance);
                    break;
                }
            }

            input = scanner.nextLine();
        }

        List<String> output = new ArrayList<>() {{
            add("1st place: ");
            add("2nd place: ");
            add("3rd place: ");
        }};
        results
                .entrySet()
                .stream()
                .sorted((r1, r2) -> r2.getValue().compareTo(r1.getValue()))
                .limit(3)
                .forEach(r -> System.out.println(output.remove(0) + r.getKey()));
    }
}
