import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PiJ day 20 - Java Streams API
 *
 * @author lmignot
 */
public class StringExercises {


    public static void main(String... args) {
        StringExercises exerciser = new StringExercises();

        List<String> words = Arrays.asList("act", "alpha", "boo", "bee", "bravo", "charlie", "doe", "delta", "echo",
                "foxtrot", "fox", "flee", "golf", "how", "hotel", "india", "juliet", "kilo", "lima", "mae", "mike",
                "november", "oscar", "papa", "quebec", "romeo", "sierra", "sam", "see", "tango", "tar", "tea",
                "uniform", "wee", "whisky", "ex", "xray", "yankee", "zulu", "zit");

        exerciser.printOnSeparateLinesWithDoubleSpacePrefix(words);
        exerciser.printOnSeparateLines(words);
        exerciser.excitingWords(words);
        exerciser.replacedWords(words);
        exerciser.uppercasedWords(words);
        exerciser.shortWords(words);
        exerciser.wordsWithB(words);
        exerciser.evenLengthWords(words);
        exerciser.upperCaseLessThanFourContains(words, "e");
        exerciser.upperCaseLessThanFourContains(words, "q");
        exerciser.proveLaziness(words, "e");
        exerciser.concatUpper(words);
        exerciser.concatUpperWithMap(words);
        exerciser.concatWithComma(words);
    }

    private void printOnSeparateLinesWithDoubleSpacePrefix (List<String> list) {
        StreamUtils.printHeader("Words on separate Lines with 2-space prefix");
        list.parallelStream().map(s -> "  " + s).forEach(System.out::println);
    }

    private void printOnSeparateLines (List<String> list) {
        StreamUtils.printHeader("Words on separate Lines");
        list.stream().forEach(System.out::println);
    }

    private void excitingWords(List<String> list) {
        StreamUtils.printHeader("Exciting Words");
        list.stream().map(s -> s + "!").forEach(System.out::println);
    }

    private void replacedWords(List<String> list) {
        StreamUtils.printHeader("Replaced Words");
        list.stream().map(s -> s.replace("i", "eye")).forEach(System.out::println);
    }

    private void uppercasedWords(List<String> list) {
        StreamUtils.printHeader("Uppercase Words");
        list.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    private void shortWords(List<String> list) {
        StreamUtils.printHeader("Short Words");
        list.stream().filter(s -> s.length() < 4).forEach(System.out::println);
    }

    private void wordsWithB(List<String> list) {
        StreamUtils.printHeader("Words with b");
        list.stream().filter(s -> s.contains("b")).forEach(System.out::println);
    }

    private void evenLengthWords(List<String> list) {
        StreamUtils.printHeader("Even length Words");
        list.stream().filter(s -> s.length() %2 == 0).forEach(System.out::println);
    }

    private void upperCaseLessThanFourContains(List<String> list, String check) {
        StreamUtils.printHeader("Uppercase, Less than 4, Contains " + check);
        String out = list.stream()
            .map(String::toUpperCase)
            .filter(s -> s.length() < 4)
            .filter(s -> s.contains(check.toUpperCase()))
            .findFirst()
            .orElse("Not found!");

        System.out.println(out);
    }

    private void proveLaziness(List<String> list, String check) {
        StreamUtils.printHeader("Proving laziness");
        List<String> tmp = new ArrayList<>();
        String out = list.stream()
            .map(s -> {
                String o = s.toUpperCase();
                tmp.add(o);
                return o;
            })
            .filter(s -> s.length() < 4)
            .filter(s -> s.contains(check.toUpperCase()))
            .findFirst()
            .orElse("Not found!");

        System.out.println(out);
        System.out.println("Uppercased: ");
        tmp.stream().forEach(System.out::println);
    }

    private void concatUpper(List<String> list) {
        StreamUtils.printHeader("Concatenating to uppercase with reduce");
        String result = list.stream().reduce("", (sA, sB) ->
            sA.toUpperCase().concat(sB.toUpperCase())
        );
        System.out.println(result);
    }

    private void concatUpperWithMap(List<String> list) {
        StreamUtils.printHeader("Concatenating to uppercase with map->reduce");
        String result = list.stream().map(String::toUpperCase).reduce("", String::concat);
        System.out.println(result);
    }

    private void concatWithComma(List<String> list) {
        StreamUtils.printHeader("Concatenating delimited by comma");
        String result = list.stream().reduce("", (sA, sB) -> sA.length() > 0 ? sA + "," + sB : sA + sB);
        System.out.println(result);
    }

}
