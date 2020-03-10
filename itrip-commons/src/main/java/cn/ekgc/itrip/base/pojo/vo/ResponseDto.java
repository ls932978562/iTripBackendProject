package cn.ekgc.itrip.base.pojo.vo;

import java.io.Serializable;

/**
 * <b>系统响应传输数据对象</b>
 * @author ls
 * @version 1.0.0
 * @since 1.0.0
 */
public class ResponseDto<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private T data;                 //相应结果数据
	private String errorCode;       //错误代码
	private String msg;             //相应结果
	private String success;         //是否成功

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	/**
	 * <b>获得响应数据传输对象-响应成功  不携带相映成功参数</b>
	 * @return
	 */
	public static ResponseDto<Object> success(){
		ResponseDto<Object> responseDto = new ResponseDto<Object>();
		responseDto.setSuccess("true");
		return responseDto;
	}

	/**
	 * <b>获得响应数据传输对象-响应成功  携带响应成功参数</b>
	 * @param data
	 * @return
	 */
	public static ResponseDto<Object> success(Object data){
		ResponseDto<Object> responseDto = new ResponseDto<Object>();
		responseDto.setSuccess("true");
		responseDto.setData(data);
		return responseDto;
	}

	/**
	 * <b>获得响应数据传输对象-响应失败  不携带响应失败参数</b>
	 * @return
	 */
	public static ResponseDto<Object> failure(){
		ResponseDto<Object> responseDto = new ResponseDto<Object>();
		responseDto.setSuccess("false");
		return responseDto;
	}

	/**
	 * <b>获得响应数据传输对象-响应失败  携带响应失败参数</b>
	 * @return
	 */
	public static ResponseDto<Object> failure(String msg){
		ResponseDto<Object> responseDto = new ResponseDto<Object>();
		responseDto.setSuccess("false");
		responseDto.setMsg(msg);
		return responseDto;
	}
}
