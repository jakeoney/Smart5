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
	
	/*Currently not doing anything with labelTokenCount*/
	@Override
	public void train(Instance[] trainingData, int v) {
		//Implement
		this.numInstance = 0;
		this.vocabularySize = v;
		this.labelCount = new HashMap<Label, Integer>();
		this.wordTypeCount = new HashMap<Label, Map<String,Integer>>();
		this.labelTokenCount = new HashMap<Label, Integer>();
		int tmp = 0;
		
		for(Instance inst : trainingData){
			this.numInstance++;
			//update the label count
			if(labelCount.containsKey(inst.label))
				labelCount.put(inst.label, labelCount.get(inst.label)+1);
			else
				labelCount.put(inst.label, 1);
			
			//update the wordTypeCount
			for(String word : inst.words){
				tmp++;
				if(wordTypeCount.containsKey(inst.label)){
					Map<String, Integer> innerMap = wordTypeCount.get(inst.label);
					if(innerMap.containsKey(word)){
						innerMap.put(word, innerMap.get(word) + 1);
					}
					//if inner hashmap word hasn't been initialized
					else{
						innerMap.put(word, 1);
					}
						
				}
				//if outer hashmap label hasn't yet been initialized
				else{
					wordTypeCount.put(inst.label, new HashMap<String, Integer>());
					Map<String, Integer> innerMap = wordTypeCount.get(inst.label);
					innerMap.put(word, 1);
				}
			}
		}
		/*System.out.println(labelCount.get(Label.HAM));
		System.out.println(labelCount.get(Label.SPAM));
		System.out.println(wordTypeCount.get(Label.HAM).entrySet().size() + " ham words");
		System.out.println(wordTypeCount.get(Label.SPAM).entrySet().size() + " spam words");
		System.out.println(wordTypeCount.get(Label.HAM).entrySet().size() + wordTypeCount.get(Label.SPAM).entrySet().size() + " total unique words");
		System.out.println("Vocab size is " + (this.vocabularySize - (wordTypeCount.get(Label.HAM).entrySet().size() + wordTypeCount.get(Label.SPAM).entrySet().size()))+" greater that num words above.\n" + this.vocabularySize + " vocab size");
		
		System.out.println(tmp + " total words");
		System.exit(0);
		*/
	}

	/**
	 * Returns the prior probability of the label parameter, i.e. P(SPAM) or P(HAM)
	 */
	@Override
	public double p_l(Label label) {				
		//Implement
		return (double)this.labelCount.get(label) / (double)this.numInstance;
	}

	/**
	 * Returns the smoothed conditional probability of the word given the label,
	 * i.e. P(word|SPAM) or P(word|HAM)
	 */
	@Override
	public double p_w_given_l(String word, Label label) {
		// Implement
		//word not in dictionary
		if(wordTypeCount.get(word) == null){
			
		}
		return 0.0;
	}
	
	/**
	 * Classifies an array of words as either SPAM or HAM. 
	 */
	@Override
	public ClassifyResult classify(String[] words) {
		// Implement
		//for(String word : words){
			
		//}
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
