package com.imdganesan.ifelseladder;

/**
 * 
 * Executable may contain the block of code which can be executable seperately. 
 * This is to give opportunity to the user to write lampda expression. 
 *  
 * @author imdganesan
 *
 * @param <T>
 */
@FunctionalInterface
public interface Executable<T> {
	
	/**
	 * Must be implemented by those who wants to give a behaviour to there then or else block of the if statement
	 * @return the result of the execution
	 */
	T execute();
}
