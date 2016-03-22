package com.imdganesan.ifelseladder;

/**
 * 
 * This inerface represents the lampda expression to return a boolean result for if else conditions  
 * @author imdganesan
 *
 */
@FunctionalInterface
public interface Condition {
	/**
	 * a very generic method to decide the boolean result of a condition. can be implemented using lampda expression
	 * @return
	 */
	boolean is();
}
