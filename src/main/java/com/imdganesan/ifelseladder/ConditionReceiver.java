package com.imdganesan.ifelseladder;


/**
 * This class receives the condition and saves it and returns ThenReceiver
 * 
 * @author imdganesan
 *
 * @param <T>
 */
public interface ConditionReceiver<T> {
	
	/**
	 * Accepts the condition lampda expression and returns a Then Receiver
	 * 
	 * @param condition
	 * @return a new ThenReceiver in which the user can say the then execution block
	 */
	ThenReceiver<T> when(Condition condition);
	
	/**
	 * Receives the boolean variable which will be used as a condition for IF
	 * @param condition
	 * @return
	 */
	ThenReceiver<T> when(boolean condition);

}
