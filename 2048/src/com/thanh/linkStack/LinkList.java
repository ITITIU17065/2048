package com.thanh.linkStack;

public class LinkList {
	private Link first;

	public LinkList() {
		first = null;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertFirst(String dd, int score) {
		Link newLink = new Link(dd, score);
		newLink.next = first;
		first = newLink;
	}

	public String deleteFirst() {
		Link temp = first;
		first = first.next;
		return temp.dData+temp.score;
	}
	
}