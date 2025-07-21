package personal.learning.sovf.entity;

import personal.learning.sovf.enums.PostType;

public class Post {
	
	private String text;
	private User author;
	private PostType postType;
	private int noOfUpVotes;
	private int noOfDownVotes;
	
	public Post(String text, User author, PostType postType) {
		this.text = text;
		this.author = author;
		this.postType = postType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public PostType getPostType() {
		return postType;
	}

	public void setPostType(PostType postType) {
		this.postType = postType;
	}

	public int getNoOfUpVotes() {
		return noOfUpVotes;
	}

	public void setNoOfUpVotes(int noOfUpVotes) {
		this.noOfUpVotes = noOfUpVotes;
	}

	public int getNoOfDownVotes() {
		return noOfDownVotes;
	}

	public void setNoOfDownVotes(int noOfDownVotes) {
		this.noOfDownVotes = noOfDownVotes;
	}

	@Override
	public String toString() {
		return "Post [text=" + text + ", author=" + author + ", postType=" + postType + ", noOfUpVotes=" + noOfUpVotes
				+ ", noOfDownVotes=" + noOfDownVotes + "]";
	}
	
}
