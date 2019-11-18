
public class Outcast {

    WordNet outCastNet;

    public Outcast(WordNet wordnet) {
        this.outCastNet = wordnet;
    }


    public String outcast(String[] nouns) {
        int maxDistance = 0;
        String maxNoun = nouns[0];

        for (int i = 0; i < nouns.length; ++i) {
            int totalDistance = 0;
            for (int j = 0; j < nouns.length; ++j) {
                totalDistance += outCastNet.distance(nouns[i], nouns[j]);
            }
            if (totalDistance > maxDistance) {
                maxDistance = totalDistance;
                maxNoun = nouns[i];
            }
        }
        return maxNoun;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}