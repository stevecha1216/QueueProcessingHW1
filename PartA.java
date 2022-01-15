package cha.MAIN;

import java.util.Scanner;
import net.datastructures.SinglyLinkedList;

public class PartA {
	public static void main(String[] args) {
		System.out.println(cyclesToCompletion());
	}
	
	//creates processes list
	public static SinglyLinkedList<String> createProcessesList(){
		//create a singly linked list pL(processesList)
		SinglyLinkedList<String> pL = new SinglyLinkedList<>();
		Scanner sc = new Scanner(System.in);
		String processes = sc.nextLine();
		Scanner scP = new Scanner(processes);
		scP.useDelimiter(";");
		while(scP.hasNext() == true) {
			pL.addLast(scP.next());
		}
		sc.close();
		scP.close();
		return(pL);
	}
	
	//find what resources the process needs
	public static String findReqResources(String process){
		String p = process;
		Scanner sc = new Scanner(p);
		String reqResources;
		reqResources = sc.findInLine("A") + "," + sc.findInLine("B") + "," + sc.findInLine("C");
		sc.close();
		return(reqResources);
	}
	
	//finds how many cycles are needed to complete all the processes
	public static int cyclesToCompletion(){
		//make process list
		SinglyLinkedList<String> pL = createProcessesList();
		//make resources required list in process order
		SinglyLinkedList<String> rL = new SinglyLinkedList<>();
		int numProcesses = pL.size();
		String process;
		String reqResources;
		for(int i = 0; i < numProcesses; i++) {
			process = pL.removeFirst();
			reqResources = findReqResources(process);
			rL.addLast(reqResources);
		}
		//set variables for resources a b and c as unused
		boolean aUsed = false;
		boolean bUsed = false;
		boolean cUsed = false;
		int A;
		int B;
		int C;
		String x = "";
		//starts from cycle 1, not 0
		int cycle = 1;
		boolean cycleAdded = false;
		while(rL.isEmpty() == false) {
			
			//set x as the first node in the list
			x = rL.removeFirst();
			//find if A B and C are in data, set as -1 if not in found
			A = x.indexOf("A");
			B = x.indexOf("B");
			C = x.indexOf("C");
			//runs processes, adds 1 to cycle if resources need to be reset
			if (cycleAdded == false) {
				if (A != -1) {
					if(aUsed == false) {
							aUsed = true;	
					}
					else {
						cycleAdded = true;
						if(A == -1) {
							aUsed = false;
						}
						if(B == -1) {
							bUsed = false;
						}
						if(C == -1) {
							cUsed = false;
						}
					}
				}
				if (B != -1) {
					if(bUsed == false) {
							bUsed = true;	
					}
					else {
						cycleAdded = true;
						if(A == -1) {
							aUsed = false;
						}
						if(B == -1) {
							bUsed = false;
						}
						if(C == -1) {
							cUsed = false;
						}
					}
				}
				if (C != -1) {
					if(cUsed == false) {
							cUsed = true;	
					}
					else {
						cycleAdded = true;
						if(A == -1) {
							aUsed = false;
						}
						if(B == -1) {
							bUsed = false;
						}
						if(C == -1) {
							cUsed = false;
						}
					}
				}
			}
			if (cycleAdded == true) {
				cycleAdded = false;
				cycle += 1;
			}
		}
		return (cycle);
	}
}
