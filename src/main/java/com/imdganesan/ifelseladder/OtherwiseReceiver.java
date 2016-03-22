package com.imdganesan.ifelseladder;


/**
 * This class receives the final else block of the if class or a another condtion (else if) to continue the if else chain.
 * 
 * @author imdganesan
 *
 * @param <T>
 */
public interface OtherwiseReceiver<T> {
	
	/**
	 * Evaluates all the Conditions, then, otherwise blocks that composed until this point and returns the result
	 * 
	 * @return the execution result of the if else ladder
	 */
	T eval();
	
	/**
	 * Returns the completed ladder as a executable. Which can be used in some other if else ladder. And the execution will only complete when the execute method is invoked
	 * 
	 * @return
	 */
	Executable<T> asExecutable(Executable<T> otherwiseBlock);
	
	/**
	 * Returns the completed ladder as a executable. Which can be used in some other if else ladder. And the execution will only complete when the execute method is invoked
	 * 
	 * @return
	 */
	Executable<T> asExecutable();
	
	/**
	 * Receives the final else block result. and calls the eval function to execute and return the result 
	 * 
	 * @param otherWiseResult
	 * @return the execution result of the if else ladder
	 */
	T otherwise(T otherWiseResult);
	
	
	/**
	 * Receives the final else block result. and calls the eval function to execute and return the result 
	 * 
	 * @param otherWiseResult
	 * @return the execution result of the if else ladder
	 */
	T otherwise(Executable<T> elseBlock);
	
	/**
	 * Receives the final else block result. and calls the eval function to execute and return the result 
	 * 
	 * @param otherWiseResult
	 * @return the execution result of the if else ladder
	 */
	T otherwise(VoidExecutable executeBlock);
	
	/**
	 * adds the next condition to the if else ladder and continues the chain
	 * 
	 * @param conditionResult
	 * @return
	 */
	ThenReceiver<T> otherwiseWhen(boolean conditionResult); 
	
	/**
	 * Adds next condition to the if else ladder and continues the chain 
	 * @param condition
	 * @return
	 */
	ThenReceiver<T> otherwiseWhen(Condition condition);
	
	

}
