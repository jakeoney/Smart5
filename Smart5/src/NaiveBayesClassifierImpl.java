import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

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
	private int vocabularySize;
	
	@Override
	public void train(Instance[] trainingData, int v) {
		//Implement
		this.numInstance = 0;
		this.vocabularySize = v;
		this.labelCount = new HashMap<Label, Integer>();
		this.wordTypeCount = new HashMap<Label, Map<String,Integer>>();
		this.labelTokenCount = new HashMap<Label, Integer>();
		this.labelTokenCount.put(Label.HAM, 0);
		this.labelTokenCount.put(Label.SPAM, 0);
		
		for(Instance inst : trainingData){
			this.numInstance++;
			//update the label count
			if(labelCount.containsKey(inst.label))
				labelCount.put(inst.label, labelCount.get(inst.label)+1);
			else
				labelCount.put(inst.label, 1);
			
			//update the wordTypeCount
			for(String word : inst.words){
				this.labelTokenCount.put(inst.label, this.labelTokenCount.get(inst.label)+1);
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
	//CHANGED HOW I WAS DOING THIS PREVIOUSLY. SHOULD BE WORKING NOW. PREVIOUSLY WASN'T USING LABELTOKENCOUNT
	@Override
	public double p_w_given_l(String word, Label label) {
		// Implement
		int labelWordCount = 0;
		
		//count total occurrences
		for(Entry<Label, Map<String, Integer>> lab : wordTypeCount.entrySet()){
			if(lab.getValue().containsKey(word)){
				if(lab.getKey().equals(label)){
					labelWordCount += lab.getValue().get(word);
				}
			}
		}
		//return (double) ((labelWordCount + delta) / (double)((delta*vocabularySize) + totalCount));
		return (double) ((labelWordCount + delta) / (double)((delta*vocabularySize) + this.labelTokenCount.get(label)));
	}
	
	/**
	 * Classifies an array of words as either SPAM or HAM. 
	 */
	@Override
	public ClassifyResult classify(String[] words) {
		// Implement
		ClassifyResult result = new ClassifyResult();
		Label currLabel = Label.HAM;
		double prob = 0.0;
		for(String word : words){
			prob += Math.log(p_w_given_l(word, currLabel));
		}
		prob += Math.log(this.p_l(currLabel));
		result.log_prob_ham = prob;
		
		prob = 0.0;
		currLabel = Label.SPAM;
		for(String word : words){
			prob += Math.log(p_w_given_l(word, currLabel));
		}
		prob += Math.log(this.p_l(currLabel));
		result.log_prob_spam = prob;
		
		if(result.log_prob_ham > result.log_prob_spam)
			result.label = Label.HAM;
		else
			result.label = Label.SPAM;
		return result;
	}
	
	/**
	 * Gets the confusion matrix for a test set. 
	 */
	@Override
	public ConfusionMatrix calculateConfusionMatrix(Instance[] testData)
	{
		// Implement
		ConfusionMatrix mat = new ConfusionMatrix(0,0,0,0);
		for(Instance inst : testData){
			ClassifyResult r = this.classify(inst.words);
			if(inst.label.equals(Label.HAM)){
				if(r.label.equals(Label.HAM)){
					mat.trueNegatives += 1;
				}
				else{
					mat.falsePositives += 1;
				}
			}
			else{
				if(r.label.equals(Label.SPAM)){
					mat.truePositives += 1;
				}
				else{
					mat.falseNegatives += 1;
				}
			}
		}
		return mat;
	}
}
