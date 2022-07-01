import java.util.*;

public class P10044 {

public static void main(String[] args) {

    Scanner c = new Scanner(System.in);
    int cases = c.nextInt();
    String cero = "MF DOOM";
    for (int currentCase = 1; currentCase<=cases; currentCase++) {

        int p = c.nextInt();
        int n = c.nextInt();

        c.nextLine();

        HashMap<String, ArrayList<String>> graph = new HashMap<>();
        String[] testingNames = new String[n];
        ArrayList<String> authors = new ArrayList<>();

        HashMap<String, Integer> eNums = new HashMap<>();

        while (p-- > 0) {

            String[] paperAuthors = c.nextLine().split(":")[0].split("\\,");
            for (int i = 0; i < paperAuthors.length; i++) {
                paperAuthors[i] = paperAuthors[i].trim();
            }

            for (String author : paperAuthors)
                if (!authors.contains(author))
                    authors.add(author);

            // create and update the graph
            for (String name : paperAuthors) {

                ArrayList<String> updatedValue;

                if (graph.keySet().contains(name))
                    updatedValue = graph.get(name);
                else
                    updatedValue = new ArrayList<>();

                for (String paperAuthor : paperAuthors)
                    if (!paperAuthor.equals(name))
                        updatedValue.add(paperAuthor);

                graph.put(name, updatedValue);

            }
        }


        //initialize the eNums map:
        for (String author : authors)
            if (!author.equals(cero))
                eNums.put(author, Integer.MAX_VALUE);
            else
                eNums.put(author, 0);


        for (int i = 0; i < n; i++)
            testingNames[i] = c.nextLine();

        calculateEnums(cero, graph, eNums);


        System.out.println("Scenario " + currentCase);
        for (String name : testingNames)
            if (!eNums.keySet().contains(name) || eNums.get(name) == Integer.MAX_VALUE)
                System.out.println(name + " infinity");
            else
                System.out.println(name + " " + eNums.get(name));

    }

}

private static void calculateEnums(String name, HashMap<String, ArrayList<String>> graph,   HashMap<String, Integer> eNums) {

    ArrayList<String> notCalculated = new ArrayList<>();
    notCalculated.add(name);

    while (notCalculated.size() > 0) {
        String currentName = notCalculated.get(0);
        for (String connectedName : graph.get(currentName)) {
            if (eNums.get(connectedName) > eNums.get(currentName)) {
                eNums.put(connectedName, eNums.get(currentName) + 1);
                if(!notCalculated.contains(connectedName))
                    notCalculated.add(connectedName);
            }
        }
        notCalculated.remove(0);
    }

}
}