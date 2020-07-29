import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetherRealms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] demons = scanner.nextLine().split(",\\s+");

        String regexForHealth = "[^0-9+\\-*\\/.]";
        Pattern patternForHealth = Pattern.compile(regexForHealth);

        String regexForDamage = "(?<number>[+-]?[\\d]+[.]?[\\d]*)";
        Pattern patternForDamage = Pattern.compile(regexForDamage);

        Map<String, List<Double>> result = new TreeMap<>();

        for (String demon : demons) {
            double damage = 0.0;
            double health = 0;
            String currentName = demon.trim();
            currentName = currentName.replaceAll(" ","");
            currentName = currentName.replaceAll(",", "");
            result.putIfAbsent(currentName,new ArrayList<>());

            Matcher matcherForHealth = patternForHealth.matcher(currentName);
            while (matcherForHealth.find()){
                double temp = matcherForHealth.group().charAt(0);
                health += temp;
            }
            result.get(currentName).add(health);

            Matcher matcherForDamage = patternForDamage.matcher(currentName);
            while (matcherForDamage.find()){
                damage += Double.parseDouble(matcherForDamage.group());
            }
            for (int i = 0; i < currentName.length(); i++) {
                if (currentName.charAt(i) == '/'){
                    damage = damage / 2;
                }else if (currentName.charAt(i) == '*'){
                    damage = damage * 2;
                }
            }
            result.get(currentName).add(damage);

        }

        result
                .entrySet()
                .stream()
                .forEach(r -> {
                    System.out.println(String.format("%s - %.0f health, %.2f damage",r.getKey(), r.getValue().get(0), r.getValue().get(1)));
                });
    }
}
