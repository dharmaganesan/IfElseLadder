package com.imdganesan.ifelseladder;


/**
 * 
 * This class restricts the user to pass only then executable block and also enables to continue the chain by returning OtherWiseRecivberthe 
 * @author imdganesan
 *
 * @param <T>
 */
public interface ThenReceiver<T> {
	
	/**
	 * Accepts the executable for the last if condtion 
	 * @param executable
	 * @return the otherwisereceiver to continue the chain
	 */
	OtherwiseReceiver<T> then(Executable<T> executable);
	
	/**
	 * Accepts the result of then block for the last if condtion 
	 * 
	 * @param thenResult
	 * @return the otherwisereceiver to continue the chain
	 */
	OtherwiseReceiver<T> then(T thenResult);
	
	/**
	 * Accepts the void executable for the last if condtion 
	 * 
	 * @param executable
	 * @return the otherwisereceiver to continue the chain
	 */
	OtherwiseReceiver<T> then(VoidExecutable executable);

}
