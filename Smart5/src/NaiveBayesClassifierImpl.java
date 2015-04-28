import java.util.Map;
import java.util.HashMap;

/**
 * Your implementation of a naive bayes classifier. Please implement all four methods.
 */

public class NaiveBayesClassifierImpl implements NaiveBayesClassifier {
	/**
	 * Trains the classifier with the provided training data and vocabulary size
	 */
	private final double delta = 0.00001;
	
	private Map<Label, Integer> labelCount;
	private Map<Label, Map<String, Integer> > wordTypeCount;
	private Map<Label, Integer> labelTokenCount;
	private int numInstance;
	//private int numTotalToken;
	private int vocabularySize;
	
	
	@Override
	public void train(Instance[] trainingData, int v) {
		//Implement
				
	}

	/**
	 * Returns the prior probability of the label parameter, i.e. P(SPAM) or P(HAM)
	 */
	@Override
	public double p_l(Label label) {				
		//Implement
		return 0.0;
	}

	/**
	 * Returns the smoothed conditional probability of the word given the label,
	 * i.e. P(word|SPAM) or P(word|HAM)
	 */
	@Override
	public double p_w_given_l(String word, Label label) {
		// Implement
		
		return 0.0;
	}
	
	/**
	 * Classifies an array of words as either SPAM or HAM. 
	 */
	@Override
	public ClassifyResult classify(String[] words) {
		// Implement
		
		return null;
	}
	
	/**
	 * Gets the confusion matrix for a test set. 
	 */
	@Override
	public ConfusionMatrix calculateConfusionMatrix(Instance[] testData)
	{
		// Implement
		return null;
	}
}
