package com.rest.api.payloads;
import java.util.List;

public class PostResponse {

	private List<PostDto> postDtoList;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
	
	public PostResponse() {
		super();
	}
	public PostResponse(List<PostDto> postDtoList, int pageNumber, int pageSize, int totalElements, boolean lastPage) {
		super();
		this.postDtoList = postDtoList;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.lastPage = lastPage;
	}
	public List<PostDto> getPostDtoList() {
		return postDtoList;
	}
	public void setPostDtoList(List<PostDto> postDtoList) {
		this.postDtoList = postDtoList;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	private boolean lastPage;
	
}
