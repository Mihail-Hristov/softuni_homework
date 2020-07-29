import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regex = ">>(?<product>\\w+)<<(?<price>[\\d][\\d.]+)!(?<quantity>\\d+)";
        Pattern pattern = Pattern.compile(regex);
        List<String> productNames = new ArrayList<>();

        String input = scanner.nextLine();

        double totalCurrentSum = 0;

        while (!"Purchase".equals(input)){
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                String currentProduct = matcher.group("product");
                double price = Double.parseDouble(matcher.group("price"));
                int quantity = Integer.parseInt(matcher.group("quantity"));

                totalCurrentSum += price * quantity;
                productNames.add(currentProduct);
            }

            input = scanner.nextLine();
        }

        System.out.println("Bought furniture:");
        for (String item : productNames) {
            System.out.println(String.format("%s",item));
        }
        System.out.println(String.format("Total money spend: %.2f",totalCurrentSum));

    }
}
