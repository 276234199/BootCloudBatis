package com.casit.thread.MyBatchTaskFrame;

/**
 * 任务结果实体类
 * @param <R> 任务结果
 */
public class TaskResult<R> {
	
	//任务结果标记
	private final TaskResultType resultType;
	//任务结果数据
	private final R returnValue;
	private final String reason;
	
	public TaskResult(TaskResultType resultType, R returnValue, String reason) {
		super();
		this.resultType = resultType;
		this.returnValue = returnValue;
		this.reason = reason;
	}
	
	/**
	 * @param resultType
	 * @param returnValue
	 * 成功的结果
	 */
	public TaskResult(R returnValue) {
		super();
		this.resultType = TaskResultType.Success;
		this.returnValue = returnValue;
		this.reason = null;
	}
	
	/**
	 * @param resultType
	 * @param returnValue
	 * 失败/EXCEPTION的结果
	 */
	public TaskResult(TaskResultType resultType, String reason) {
		super();
		this.resultType = resultType;
		this.returnValue = null;
		this.reason = reason;
	}

	public TaskResultType getResultType() {
		return resultType;
	}

	public R getReturnValue() {
		return returnValue;
	}

	public String getReason() {
		return reason;
	}

	@Override
	public String toString() {
		return "TaskResult [resultType=" + resultType + ", returnValue=" + returnValue + ", reason=" + reason + "]";
	}
	

	
	

}
