import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static final String FILE_PATH = "C:\\Users\\Yersinia\\Programozas\\AKAkademia\\Lambda\\NamesInFile\\names.txt";

    public static void main(String[] args) throws IOException {
        List<Name> listOfNames = getListOfNames();
        List<String> stringList = Files.lines(Paths.get(FILE_PATH)).toList();

        System.out.println("Who has the longest last name?");

        List<Name> lastNamesWithMaximumLength = getNamesWithMaxLength(listOfNames);

        lastNamesWithMaximumLength.forEach(System.out::println);

        System.out.printf("%n--------------------------------------------%n");

        System.out.println("Who has two first names?");

        List<Name> namesWithSecontFirstName = getNamesWithSecondFirstName(listOfNames);

        namesWithSecontFirstName.forEach(System.out::println);


        System.out.printf("%n--------------------------------------------%n");

        System.out.println("Which line/lines has/have the longest name in the txt file?");

        List<Integer> listOfIndexes = getPositionsOfMostLongestNamesFromFile(stringList);

        listOfIndexes.forEach(System.out::println);

        System.out.printf("%n--------------------------------------------%n");

        System.out.println("Which name contains the most 'n' character?");
        char targetCharacter = 'n';
        List<Map.Entry<String, Long>> nameWithMaxOccurencesOfNs = getNameWithMaxOccurenceOfASpecificCharacter(stringList, targetCharacter);

        nameWithMaxOccurencesOfNs.forEach(System.out::println);

        System.out.printf("%n--------------------------------------------%n");

        System.out.println("Are there people with the same last name?");

        List<Map.Entry<String, List<Name>>> mapOfPeopleWithSameLastNames = getPeopleWithSameLastNames(listOfNames);

        System.out.println(mapOfPeopleWithSameLastNames.isEmpty() ? "There are no people with  the same last name." : "There are people with the same last name.");

        System.out.printf("%n--------------------------------------------%n");

        System.out.println("Are there people with the same first name? (first and second first names should be match)");

        List<Map.Entry<String, List<Name>>> mapOfPeopleWithTheSameFirstNames = getMapOfPeopleWithTheSameFirstNames(listOfNames);
        List<Map.Entry<String, List<Name>>> mapOfPeopleWithTheSameSecondFirstNames = getPeopleWithTheSameSecondFirstNames(listOfNames);

        System.out.println(mapOfPeopleWithTheSameSecondFirstNames.isEmpty() ? "There are no people with the same first name and second first name." : "There are people with the same first name and second first name.");

        System.out.printf("%n--------------------------------------------%n");

        System.out.println("Are there people with the same first name or second first name?");

        List<Map.Entry<?, List<Name>>> isPeopeWithTheSameFirstNameOrSecondFirstName = Stream.concat(mapOfPeopleWithTheSameFirstNames.stream(), mapOfPeopleWithTheSameSecondFirstNames.stream()).collect(Collectors.toList());
        System.out.println(isPeopeWithTheSameFirstNameOrSecondFirstName.isEmpty() ? "There are no people with the same first name or second first name." : "There are people with the same first name or second first name.");
    }

    private static List<Map.Entry<String, List<Name>>> getPeopleWithTheSameSecondFirstNames(List<Name> listOfNames) {
        List<Map.Entry<String, List<Name>>> mapOfPeopleWithTheSameSecondFirstNames =
                listOfNames.stream()
                        .filter(name -> name.getSecondFirstName() != null)
                        .filter(name -> !name.getSecondFirstName().equals(""))
                        .collect(Collectors.groupingBy(Name::getSecondFirstName))
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().size() > 1).toList();
        return mapOfPeopleWithTheSameSecondFirstNames;
    }

    private static List<Map.Entry<String, List<Name>>> getMapOfPeopleWithTheSameFirstNames(List<Name> listOfNames) {
        List<Map.Entry<String, List<Name>>> mapOfPeopleWithTheSameFirstNames =
                listOfNames.stream()
                        .collect(Collectors.groupingBy(Name::getFirstName))
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().size() > 1).toList();
        return mapOfPeopleWithTheSameFirstNames;
    }

    private static List<Map.Entry<String, List<Name>>> getPeopleWithSameLastNames(List<Name> listOfNames) {
        List<Map.Entry<String, List<Name>>> mapOfPeopleWithSameLastNames =
                listOfNames.stream()
                        .collect(Collectors.groupingBy(Name::getLastName))
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().size() > 1).toList();
        return mapOfPeopleWithSameLastNames;
    }

    private static List<Map.Entry<String, Long>> getNameWithMaxOccurenceOfASpecificCharacter(List<String> stringList, char targetCharacter) {
        List<List<Character>> listOfTheCharacters =
                stringList.stream()
                        .map(str -> str.chars()
                                .mapToObj(c -> Character.toLowerCase((char) c))
                                .collect(Collectors.toList())).toList();

        List<Long> numberOfNChars =
                listOfTheCharacters.stream()
                        .map(innerList -> innerList.stream()
                                .filter(c -> c == targetCharacter)
                                .count()).toList();

        Optional<Long> maxNumberOfNs =
                numberOfNChars
                        .stream()
                        .max(Comparator.comparingInt(Long::intValue));

        Map<String, Long> stringWithOccurences =
                IntStream
                        .range(0, stringList.size())
                        .boxed()
                        .collect(Collectors.toMap(stringList::get, numberOfNChars::get));

        List<Map.Entry<String, Long>> nameWithMaxOccurencesOfNs =
                stringWithOccurences.entrySet()
                        .stream()
                        .filter(value -> Objects.equals(value.getValue(), maxNumberOfNs.get())).collect(Collectors.toList());
        return nameWithMaxOccurencesOfNs;
    }

    private static List<Integer> getPositionsOfMostLongestNamesFromFile(List<String> stringList) {
        OptionalInt maxLineLength =
                stringList.stream()
                        .mapToInt(line -> line.length())
                        .max();

        List<Integer> listOfIndexes =
                stringList.stream()
                        .filter(n -> n.length() == maxLineLength.getAsInt())
                        .map(stringList::indexOf)
                        .map(index -> index + 1)
                        .toList();
        return listOfIndexes;
    }

    private static List<Name> getNamesWithSecondFirstName(List<Name> listOfNames) {
        List<Name> namesWithSecontFirstName =
                listOfNames.stream()
                        .filter(n -> n.getSecondFirstName() != null).toList();
        return namesWithSecontFirstName;
    }

    private static List<Name> getNamesWithMaxLength(List<Name> listOfNames) {
        OptionalInt maxLengthOfTheLastName =
                listOfNames.stream()
                        .mapToInt(n -> n.getLastName().length())
                        .max();

        List<Name> lastNamesWithMaximumLength =
                listOfNames.stream()
                        .filter(n -> n.getLastName().length() == maxLengthOfTheLastName.getAsInt())
                        .toList();
        return lastNamesWithMaximumLength;
    }

    private static List<Name> getListOfNames() {
        List<Name> listOfNames = new ArrayList<>();
        String[] names;
        try (BufferedReader reader = new BufferedReader(new FileReader("names.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                names = line.split(" ");
                if (names.length == 3) {
                    listOfNames.add(new Name(names[0], names[1], names[2]));
                } else {
                    listOfNames.add(new Name(names[0], names[1],""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfNames;
    }
}