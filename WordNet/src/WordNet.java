import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.Bag;

public class WordNet {

    private Digraph wordNet;
    private LinearProbingHashST<Integer, String> vertToSynset;
    private LinearProbingHashST<String, Bag> nounToVert;
    private ShortestCommonAncestor shortestCommon;

    public WordNet(String synsets, String hypernyms) {

        In inSyn = new In(synsets);
        In inHyper = new In(hypernyms);
        this.vertToSynset = new LinearProbingHashST<Integer, String>();
        this.nounToVert = new LinearProbingHashST<String, Bag>();
        int size = 0;

        // read synsets and make hash table as a bag of individual nouns
        while (inSyn.hasNextLine()) {
            // take in line and split line along ","
            String line = inSyn.readLine();
            String[] tokens = line.split(",");

            // Add vert and synset to hash table
            vertToSynset.put(Integer.parseInt(tokens[0]), tokens[1]);

            // split synset along " " add them all to bag and nounToVert hashtable
            String[] synset = tokens[1].split(" ");
            Bag<String> synsetBag = new Bag<String>();
            for (int i = 0; i < synset.length; ++i) {
                synsetBag.add(synset[i]);

                // if this is the first occurance of the word
                // add it to hash table with a bag of verts
                if (!nounToVert.contains(synset[i])) {
                    Bag<Integer> vertBag = new Bag<>();
                    vertBag.add(Integer.parseInt(tokens[0]));
                    nounToVert.put(synset[i], vertBag);
                }
                // if not first occurrence of word add new vert to its bag
                else {
                    nounToVert.get(synset[i]).add(Integer.parseInt(tokens[0]));
                }
            }
            ++size;
        }

        // read in hypernyms and add edges to digraph
        this.wordNet = new Digraph(size);
        while (inHyper.hasNextLine()) {
            // take in line and split along ","
            String line = inHyper.readLine();
            String[] tokens = line.split(",");

            // adds all edges
            for (int i = 1; i < tokens.length; ++i) {
                wordNet.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }

        // create shortest common field
        this.shortestCommon = new ShortestCommonAncestor(wordNet);
    }

    // all WordNet nouns
    public Iterable<String> nouns() {
        return nounToVert.keys();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return nounToVert.contains(word);
    }

    // return a synset (second field of synsets.txt) that is a shortest common ancestor
    // of noun1 and noun2 (defined below)
    public String sca(String noun1, String noun2) {
        int ancestorVert = shortestCommon.ancestor(nounToVert.get(noun1), nounToVert.get(noun2));
        return vertToSynset.get(ancestorVert);
    }

    // distance between noun1 and noun2 (defined below)
    public int distance(String noun1, String noun2) {
        return shortestCommon.length(nounToVert.get(noun1), nounToVert.get(noun2));
    }

    // do unit testing of this class
    public static void main(String[] args) {
        String synset = args[0];
        String hyperset = args[1];

        WordNet wordNet = new  WordNet(synset, hyperset);
        Iterable iter = wordNet.nouns();

        if (wordNet.isNoun("animal")) {
            System.out.println(wordNet.sca("demotion", "variation"));
            System.out.println(wordNet.distance("demotion", "variation"));
            System.out.println(wordNet.sca("zebra", "horse"));
            System.out.println(wordNet.distance("zebra", "horse"));
        }

        if (wordNet.distance("white_marlin", "mileage") == 23) {
            System.out.println("white_marlin\", \"mileage");
        }

        if (wordNet.distance("Black_Plague", "black_marlin") == 33) {
            System.out.println("Black_Plague\", \"black_marlin");
        }

        if (wordNet.distance("American_water_spaniel", "histology") == 27) {
            System.out.println("American_water_spaniel\", \"histology");
        }

        if (wordNet.distance("Brown_Swiss", "barrel_roll") == 29) {
            System.out.println("Brown_Swiss\", \"barrel_roll");
        }



        System.out.println(wordNet.sca("individual", "edible_fruit"));



//        In in = new In(args[0]);
//        Digraph G = new Digraph(in);
//        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
//        int v = 3;
//        int w = 10;
//        int length   = sca.length(v, w);
//        int ancestor = sca.ancestor(v, w);
//        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//
//        v = 8;
//        w = 11;
//        length   = sca.length(v, w);
//        ancestor = sca.ancestor(v, w);
//        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
//
//        v = 6;
//        w = 2;
//        length   = sca.length(v, w);
//        ancestor = sca.ancestor(v, w);
//        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);


    }
}