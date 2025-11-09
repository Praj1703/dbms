import java.util.*;
import java.io.*;


public class RR {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the Number of Processes : ");
		int n = sc.nextInt();
		int at[] = new int[n];
		int wt[] = new int[n];
		int rt[] = new int[n];
		int ct[] = new int[n];
		int bt[] = new int[n];
		int tat[]= new int[n];
		boolean visited[] = new boolean[n];		
		
		System.out.println("Enter the Arrival Time and Burst Time for All Processes: ");
		
		for (int i = 0; i < n; i++) {
			System.out.print("Enter the Arrival Time of Process P" + ( i + 1) + " ");
			at[i] = sc.nextInt();

			System.out.print("Enter the Brust Time of Process P" + ( i + 1 ) + " ");
			bt[i] = sc.nextInt();
		
			rt[i] = bt[i];
		}
		
		System.out.print("Enter the Time Quantum : ");
		int tq = sc.nextInt();
		
		Queue<Integer> q = new LinkedList<>();
		int time = 0;
 		int completed = 0;

		int first = 0;
		
		for (int i = 0; i < n ; i++) {
			if (at[i] < at[first]) 
				first = i;
		}

		q.add(first); 
		visited[first] = true;

		while (completed < n) {
			int index = q.poll();

			if (rt[index] > tq) {
				time += tq;
				rt[index] -= tq;
			}
			else {
				time += rt[index];
				rt[index] = 0;
				ct[index] = time;
				completed++;
			}

			for (int i = 0; i < n; i++) {
				if(at[i] <= time && rt[i] > 0 && !visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}

			if (rt[index] > 0) {
				q.add(index);			
			}
			
  			if (q.isEmpty() && completed < n) {
				for (int i = 0; i< n; i++) {
					if (rt[i] > 0) {
						q.add(i);
						visited[i] = true;
						break;
					}
				}
			}
		}
		
		System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
		for (int i = 0; i < n; i++) {
			tat[i] = ct[i] - at[i];	
			wt[i] = tat[i] - bt[i];
			
			System.out.printf("p%d\t%d\t%d\t%d\t%d\t%d\n", (i+1), at[i], bt[i], ct[i],tat[i],wt[i]);
		}
		
		sc.close();
	}
}