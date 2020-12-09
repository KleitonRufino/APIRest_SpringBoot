package br.com.apirest_springboot.vo;

import java.io.Serializable;

//import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.ResourceSupport;

public class UploadFileResponseVO extends ResourceSupport implements Serializable {
//	public class PersonVO extends RepresentationModel<PersonVO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;

	public UploadFileResponseVO() {
	}

	public UploadFileResponseVO(String fileName, String fileDownloadUri, String fileType, long size) {
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
