import java.util.*;

public class P10044 {

public static void main(String[] args) {

    Scanner c = new Scanner(System.in);
    String cero = c.nextLine();
    int v = c.nextInt();
    int e = c.nextInt();
    c.nextLine();

        HashMap<String, ArrayList<String>> graph = new HashMap<>();
        String[] nombrestester = new String[e];
        ArrayList<String> artistas = new ArrayList<>();

        HashMap<String, Integer> distancia = new HashMap<>();

        while (v-- > 0) {

            String[] artista = c.nextLine().split(":")[0].split("\\,");
            for (int i = 0; i < artista.length; i++) {
                artista[i] = artista[i].trim();
            }

            for (String author : artista)
                if (!artistas.contains(author))
                    artistas.add(author);

            for (String name : artista) {

                ArrayList<String> updatedValue;

                if (graph.keySet().contains(name))
                    updatedValue = graph.get(name);
                else
                    updatedValue = new ArrayList<>();

                for (String paperAuthor : artista)
                    if (!paperAuthor.equals(name))
                        updatedValue.add(paperAuthor);

                graph.put(name, updatedValue);

            }
        }


        for (String author : artistas)
            if (!author.equals(cero))
                distancia.put(author, Integer.MAX_VALUE);
            else
                distancia.put(author, 0);


        for (int i = 0; i < e; i++)
            nombrestester[i] = c.nextLine();

        calculardistancia(cero, graph, distancia);


        for (String name : nombrestester)
            if (!distancia.keySet().contains(name) || distancia.get(name) == Integer.MAX_VALUE)
                System.out.println(name + " infinito");
            else
                System.out.println(name + " " + distancia.get(name));

    

}

private static void calculardistancia(String name, HashMap<String, ArrayList<String>> graph,   HashMap<String, Integer> eNums) {

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