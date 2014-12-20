package metrics.processor;

import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * VO containing measurement info
 * @author arrizabalaga
 *
 */
public class Measurement{

	/**
	 * Default mongo id
	 */
	@Id private String id;

	/**
	 * Key that identifies the metric (Ex: 'jvm-usage')
	 */
	private String key;
	/**
	 * Host that sent the measurement (Ex: 'http://localhost')
	 */
	private String host;
	/**
	 * When was the measurement captured
	 */
	private Date timeStamp=new Date();
	
	/**
	 * The value of the measurement (Ex: 0.10)
	 */
	private Double value;
	
	/**
	 * A description of a potential error that might have happened while capturing the measurement 
	 */
	private String error;

	public String getKey() {
		return key;
	}

	public void setKey(String firstName) {
		this.key = firstName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String lastName) {
		this.host = lastName;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}	

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}