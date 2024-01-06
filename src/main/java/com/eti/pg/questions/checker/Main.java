package com.eti.pg.questions.checker;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        // Load your pre-trained Word2Vec model
        Word2Vec word2Vec = loadWord2VecModel("C:\\Users\\theKonfyrm\\Desktop\\iui\\nkjp+wiki-forms-all-100-cbow-hs.txt");

        // Two example texts for comparison
        String text1 = "Przygotowania przed rozpoczęciem pracy z udziałowcami obejmują kilka kluczowych kroków:\n" +
                "\n" +
                "Identyfikacja udziałowców: To pierwszy krok, w którym określamy, kto jest zainteresowany projektem i ma wpływ na jego realizację. To może obejmować zarówno wewnętrznych jak i zewnętrznych interesariuszy.\n" +
                "\n" +
                "Wybór reprezentantów udziałowców: Po zidentyfikowaniu udziałowców ważne jest wybranie tych, którzy będą reprezentować pozostałych. Często pracuje się z osobami, które mają najlepszą wiedzę lub wpływ na dane obszary projektu.\n" +
                "\n" +
                "Kontakt z wybranymi reprezentantami: Następnie należy nawiązać kontakt z wybranymi reprezentantami udziałowców, aby rozpocząć proces wydobywania wymagań.\n" +
                "\n" +
                "W kontekście poszczególnych problemów:\n" +
                "\n" +
                "A: Gdy udziałowcy zgłaszają wzajemnie sprzeczne wymagania, można zastosować obserwacje. Obserwowanie prac przyszłych użytkowników pomoże zrozumieć, które z wymagań są rzeczywiście istotne i adekwatne.\n" +
                "\n" +
                "B: Jeśli zespół nie ma wiedzy o dziedzinie biznesowej klienta, to warto przeprowadzić studia dziedzinowe. To oznacza pozyskiwanie informacji z różnych źródeł, takich jak dokumenty czy literatura branżowa.\n" +
                "\n" +
                "C: Kiedy udziałowcy nie potrafią konkretyzować swoich wymagań, dobrym podejściem jest prototypowanie. Tworzenie wstępnych wersji systemu lub interfejsów pozwala udziałowcom zobaczyć coś namacalnego i wyrazić swoje preferencje.\n" +
                "\n" +
                "D: Jeśli brakuje pomysłów na cele biznesowe, można zorganizować sesje burz mózgów z grupą interesariuszy. To spotkania skupione na generowaniu nowych pomysłów i rozwiązań, które pomogą osiągnąć cele biznesowe.\n";
//        String text2 = "politechnika gdańska to najlepsza uczelnia znajdująca się w trójmieście, szanuje ona studentów i wszyscy mają o niej bardzo dobre zdanie";

        String text2 = "Działania poprzedzające pracę z udziałowcami:\n" +
                "-identyfikacja udziałowców\n" +
                "-określenie reprezentantów udziałowców od których będziemy wydobywać te wymagania\n" +
                "-dotarcie do wybranych reprezentantów\n" +
                "\n" +
                "A: Obserwacje - dzięki obserwowaniu pracy przyszłych użytkowników będziemy w stanie dotrzeć do tego które zgłaszane wymagania są tymi właściwymi\n" +
                "\n" +
                "D: Burze mózgów - są to spotkania grupy interesariuszy wg określonego scenariusza ukierunkowane właśnie na generowanie nowych pomysłów.";

        // Tokenize and preprocess the texts
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        String[] words1 = tokenizerFactory.create(text1).getTokens().toArray(new String[0]);
        String[] words2 = tokenizerFactory.create(text2).getTokens().toArray(new String[0]);

        // Calculate vectors for each text
        INDArray vector1 = averageWordVectors(word2Vec, words1);
        INDArray vector2 = averageWordVectors(word2Vec, words2);

        // Calculate the cosine similarity between the vectors
        double similarity = calculateCosineSimilarity(vector1, vector2);

        System.out.println("Cosine Similarity: " + similarity);
    }

    private static Word2Vec loadWord2VecModel(String modelFilePath) {
        WordVectors wordVectors = WordVectorSerializer.readWord2VecModel(new File(modelFilePath));
        return (Word2Vec) wordVectors;
    }

    private static INDArray averageWordVectors(Word2Vec word2Vec, String[] words) {
        INDArray sum = null;
        for (String word : words) {
            if (word2Vec.hasWord(word)) {
                INDArray vector = word2Vec.getWordVectorMatrix(word);
                if (sum == null) {
                    sum = vector.dup();
                } else {
                    sum.addi(vector);
                }
            }
        }
        if (sum != null) {
            sum.divi(words.length);
        }
        return sum;
    }

    private static double calculateCosineSimilarity(INDArray vector1, INDArray vector2) {
        return Transforms.cosineSim(vector1, vector2);
    }
}
