package com.eti.pg.questions.checker.comparator;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.ops.transforms.Transforms;

import java.io.File;

public class Word2vecComparator implements BaseAnswerComparator {

    private Word2Vec word2Vec;

    public Word2vecComparator() {
        word2Vec = loadWord2VecModel("C:\\Users\\theKonfyrm\\Desktop\\iui\\nkjp+wiki-forms-all-100-cbow-hs.txt");
    }

    @Override
    public double compare(String text1, String text2) {
        // Tokenize and preprocess the texts
        TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        String[] words1 = tokenizerFactory.create(text1).getTokens().toArray(new String[0]);
        String[] words2 = tokenizerFactory.create(text2).getTokens().toArray(new String[0]);

        // Calculate vectors for each text
        INDArray vector1 = averageWordVectors(word2Vec, words1);
        INDArray vector2 = averageWordVectors(word2Vec, words2);

        // Calculate the cosine similarity between the vectors
        return calculateCosineSimilarity(vector1, vector2);
    }

    private Word2Vec loadWord2VecModel(String modelFilePath) {
        WordVectors wordVectors = WordVectorSerializer.readWord2VecModel(new File(modelFilePath));
        return (Word2Vec) wordVectors;
    }

    private INDArray averageWordVectors(Word2Vec word2Vec, String[] words) {
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

    private double calculateCosineSimilarity(INDArray vector1, INDArray vector2) {
        return Transforms.cosineSim(vector1, vector2);
    }

}
