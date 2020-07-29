import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarEnigma {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> result = new TreeMap<>();
        result.putIfAbsent("A", new ArrayList<>());
        result.putIfAbsent("D", new ArrayList<>());

        int numberOfLine = Integer.parseInt(scanner.nextLine());
        String regex = "[SsTtAaRr]";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < numberOfLine; i++) {
            String input = scanner.nextLine();
            Matcher matcher = pattern.matcher(input);
            int countStar = 0;
            while (matcher.find()) {
                countStar++;
            }
            StringBuilder currentMassage = new StringBuilder();

            for (int j = 0; j < input.length(); j++) {
                char currentChar = (char) (input.charAt(j) - countStar);
                currentMassage.append(currentChar);
            }
            String regexForCommand = "(?:[^@\\-!:>])*@(?<planet>[A-Z][a-z]+)(?:[^@\\-!:>])*(:)(?<population>[\\d]+)(?:[^@\\-!:>])*!(?<attackType>[AD])!(?:[^@\\-!:>])*->(?<soldier>[\\d]+)(?:[^@\\-!:>])*";
            Pattern pattern1 = Pattern.compile(regexForCommand);
            Matcher matcher1 = pattern1.matcher(currentMassage);
            if (matcher1.find()) {
                String planet = matcher1.group("planet");
                String attackType = matcher1.group("attackType");
                result.get(attackType).add(planet);
                Collections.sort(result.get(attackType));

            }
        }

        result
                .forEach((key, value) -> {
                    if ("A".equals(key)) {
                        System.out.println(String.format("Attacked planets: %d", value.size()));
                    }else if ("D".equals(key)){
                        System.out.println(String.format("Destroyed planets: %d", value.size()));
                    }
                    for (String item : value) {
                        System.out.println(String.format("-> %s", item));
                    };
                });
    }
}
