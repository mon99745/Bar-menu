package com.example.bmc.exception;

import com.example.bmc.util.CommonUtil;
import lombok.Getter;

/**
 * BMM 예외
 */

@Getter
public abstract class DefaultBmException extends DefaultException{

	protected final String tid;

	protected DefaultBmException(Error error, String message, String tid, Throwable cause) {
		super(error, CommonUtil.joiningBySpace(message, tid == null ? null : "tid: " + tid), cause);
		this.tid = tid;
	}

}
