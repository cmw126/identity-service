package com.identity.common.utils;

public class ThreadLocalWrapper {

	//Using Inheritable so that transactionId can be accessible in child thread also which is start by parent thread
		private static InheritableThreadLocal<String> transactionIdThreadLocal = new InheritableThreadLocal<String>();

		/**
		 * It return the static thread local variable. In which you can set the transcationId.
		 * @return
		 */
		private static ThreadLocal<String> getTransactionIdThreadLocal(){
			return transactionIdThreadLocal;
		}

		/**
		 * Set the transactionId in thread local, which is available till thread and it child thread not dead
		 * @param transactionId
		 */
		public static void setTransactionId(String transactionId){
			getTransactionIdThreadLocal().set(transactionId);
		}

		/**
		 * Get the transactionId for the Thread which is its set
		 * @return
		 */
		public static String getTransactionId(){
			return getTransactionIdThreadLocal().get();
		}

		/**
		 * Its clear the thread local.
		 * You have to call these if the thread is from pool.
		 *Since the thread from the pool is return to pool and the value never clear if you not done manually.
		 * If you not clear Thread local , it will create a memory leak problem.
		 * Always call just before the work is completed by the thread
		 */
		public  static void clearThreadLocal(){
			//Clear the thread to solve the memory leak problem
			ThreadLocalWrapper.getTransactionIdThreadLocal().remove();
		}
}
