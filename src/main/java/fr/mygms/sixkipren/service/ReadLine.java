package fr.mygms.sixkipren.service;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class ReadLine {
	
	public String toString() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}
	
	public int toInteger() throws NumberFormatException {
		return Integer.parseInt(toString());
	}
}
