package com.thanh.linkStack;


public class LinkStack {
	private LinkList theList;

	public LinkStack() {
		theList = new LinkList();
	}

	public void push(String j, int score) {
		theList.insertFirst(j, score);
	}

	public String pop() {
		return theList.deleteFirst();
	}

	public boolean isEmpty() {
		return (theList.isEmpty());
	}

}
