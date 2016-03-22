package com.imdganesan.ifelseladder;


import java.util.ArrayList;
import java.util.List;

/**
 * This class the starting class of the If statment and will be Chained untill the execution of all the if else ladder
 * 
 * @author imdganesan
 *
 * @param <T>
 */
public class If<T> {
	
   private If() {}
	
	/**
	 * Creates the new chain of If else ladder with the passed Return type.
	 * @param type
	 * @return Returns the condition receiver to accept the condtion and start the chain
	 */
	public static <T> ConditionReceiver<T> of(Class<T> type) {
		return new ConditionReceiverImpl<T>(new ArrayList<ConditionReceiverImpl<T>>()); 
	}
	
	/**
	 * @return the Void Condition Receiver
	 */
	public static ConditionReceiver<Void> of() {
		return of(Void.class);
	}
	
	/**
	 * private impl for conditoin receiver. 
	 * @author imdganesan
	 *
	 * @param <T>
	 */
	private static class ConditionReceiverImpl<T> implements ConditionReceiver<T> {
		
		private Condition condition;
		private Executable<T> thenBlock;
		private Executable<T> elseBlock;
		private List<ConditionReceiverImpl<T>> ifElseLadderList;
		
		private ConditionReceiverImpl(List<ConditionReceiverImpl<T>> linkedList) {
			this.ifElseLadderList = linkedList;
			this.ifElseLadderList.add(this);
		}
		
		public ThenReceiver<T> when(Condition condition) {
			this.condition = condition;
			return new ThenReceiverImpl<T>(this);
		}
		
		public ThenReceiver<T> when(boolean condition) {
			return when(() -> condition);
		}
		
		private T execute() {
			T result = null;
			for(ConditionReceiverImpl<T> conditionReceiver : ifElseLadderList) {
				if(conditionReceiver.condition.is()) {
					result = conditionReceiver.thenBlock.execute();
					break;
				} else if(conditionReceiver.elseBlock != null) {
					result = conditionReceiver.elseBlock.execute();
					break;
				} else {
					continue;
				}
			}
			return result;
		}		
	}		
	
	
	/**
	 * private impl of ThenReceiver.
	 * @author imdganesan
	 *
	 * @param <T>
	 */
	private static class ThenReceiverImpl<T> implements ThenReceiver<T> {
		
		private ConditionReceiverImpl<T> conditionReceiver;
		
		private ThenReceiverImpl(ConditionReceiverImpl<T> conditionReceiver) {
			this.conditionReceiver = conditionReceiver;
		}
		
		public OtherwiseReceiver<T> then(Executable<T> executeBlock) {
			this.conditionReceiver.thenBlock = executeBlock;
			return new ElseReceiverImpl<T>(conditionReceiver);
		}
		
		public OtherwiseReceiver<T> then(T thenResult) {
			return then(() -> thenResult);
		}
		
		public OtherwiseReceiver<T> then(VoidExecutable executeBlock) {
			return then(() -> { executeBlock.execute(); return null; });
		}		
	}
	
	/**
	 * private impl of Else receiver impl
	 *  
	 * @author imdganesan
	 *
	 * @param <T>
	 */
	private static class ElseReceiverImpl<T> implements OtherwiseReceiver<T> {
		
		private ConditionReceiverImpl<T> conditionReceiver;
		
		public ElseReceiverImpl(ConditionReceiverImpl<T> conditionReceiver) {
			this.conditionReceiver = conditionReceiver;
		}
		
		public T eval() {
			return conditionReceiver.execute();
		}
		
		public T otherwise(T otherWiseResult) {
			return otherwise(()-> otherWiseResult);
		}
		
		public T otherwise(Executable<T> elseBlock) {
			this.conditionReceiver.elseBlock = elseBlock;
			return eval();
		}
		
		public ThenReceiver<T> otherwiseWhen(boolean conditionResult) {
			return otherwiseWhen(()-> conditionResult);
		}
		
		public ThenReceiver<T> otherwiseWhen(Condition condition) {
			return new ConditionReceiverImpl<T>(conditionReceiver.ifElseLadderList).when(condition) ;
		}
		
		public T otherwise(VoidExecutable executeBlock) {
			return otherwise(() -> { executeBlock.execute(); return null; });
		}

		@Override
		public Executable<T> asExecutable() {
			return this::eval;
		}

		@Override
		public Executable<T> asExecutable(Executable<T> otherwiseBlock) {
			this.conditionReceiver.elseBlock = otherwiseBlock;
			return asExecutable();
		}
			
	}

}
