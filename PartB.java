package cha.MAIN;

import net.datastructures.SinglyLinkedList;

public class PartB {

	public static void main(String[] args) {
		runCycleAddProcess();
	}
	//generate a process, random resources req. input x, process number.
	public static String generateRandomProcessString(int x) {
		String p = "";
		int A;
		int B;
		int C;
		int ABC;
		p = "P" + x + "(";
		//randomly determines whether the resource is used or not
		A = (int)(Math.random()*2);
		B = (int)(Math.random()*2);
		C = (int)(Math.random()*2);
		ABC = A + B + C;
		//makes sure there are no empty processes
		while(ABC == 0) {
			A = (int)(Math.random()*2);
			B = (int)(Math.random()*2);
			C = (int)(Math.random()*2);
			ABC = A + B + C;
		}
		//builds string of the process
		if(A == 1) {
			p += "A";
		}
		if(B == 1) {
			if(p.indexOf("A") != -1) {
				p += ",";
			}
			p += "B";
		}
		if(C == 1) {
			if(ABC > 1) {
				p += ",";
			}
			p += "C";
		}
		p += ")";
		return(p);
	}
	
	//generate list of 20 processes randomly
	public static SinglyLinkedList<String> generateProcessesList(){
		SinglyLinkedList<String> pL = new SinglyLinkedList<>();
		int x = 1;
		String p;
		while(x < 21) {
			p = generateRandomProcessString(x);
			//add string to singly linked list
			pL.addLast(p);
			p = "";
			x++;
		}
		return(pL);
	}

	public static int runCycleAddProcess() {
		//make process list
		SinglyLinkedList<String> pL = generateProcessesList();
		//make resources required list in process order
		//set variables for resources a b and c as unused
		boolean aUsed = false;
		boolean bUsed = false;
		boolean cUsed = false;
		int A;
		int B;
		int C;
		int x = 21;
		String z = "";
		String p = "";
		int returnValue = 0;
		//starts from cycle 1, not 0
		int cycle = 1;
		boolean cycleAdded = false;
		while(cycle <= 1000) {
			if(pL.isEmpty() == true) {
				returnValue = cycle;
				System.out.println(returnValue + " cycles needed to empty the list of processes");
				break;
			}
			if(pL.isEmpty() == false) {
				//set x as the first node in the list
				z = pL.removeFirst();
				//find if A B and C are in data, set as -1 if not in found
				A = z.indexOf("A");
				B = z.indexOf("B");
				C = z.indexOf("C");
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
					p = generateRandomProcessString(x);
					pL.addLast(p);
					x++;
				}
			}
			//every 100th cycle outputs size of process list
			if(cycle % 100 == 0) {
				System.out.println("Length of processes at cycle " + cycle + ": " + pL.size());
			}
		}
		if(cycle > 1000) {
			returnValue = pL.size();
			System.out.println(returnValue + " processes left in process list");
		}
		return(returnValue);
	}
}
