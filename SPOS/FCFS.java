import java.util.*;

public class FCFS{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Number of processes : ");
		int n = sc.nextInt();
		
		int at[] = new int[n];
		int wt[] = new int[n];
		int ct[] = new int[n];
		int tat[] = new int[n];
		int bt[] = new int[n];
		int pid[] = new int[n];
		
		for(int i = 0; i < n; i++ ){
			System.out.print("Enter the Arrival Time of Process P" + (i+1) + " ");
			at[i] = sc.nextInt();
			System.out.print("Enter the Burst Time of Process P" + (i+1)+ " ");			
			bt[i] = sc.nextInt();
			pid[i] = i+1;
		}
		for(int i = 0; i < n-1 ; i++){
			for(int j = i+1; j < n; j++){
				if(at[i] > at[j]) {
					int temp = at[i];
					at[i] = at[j];
					at[j] = temp;

					temp = bt[i];
					bt[i]= bt[j];
					bt[j]= temp;
		
					temp = pid[i];
					pid[i]=pid[j];
					pid[j]= temp;
				}
			}
		}	
		ct[0] = at[0] + bt[0]; 
		for(int i = 1; i < n; i++){
			if(at[i] > ct[i-1])
				ct[i] = at[i] + bt[i];
			else
				ct[i] = ct[i - 1] + bt[i];	
		}
		for (int i = 0; i < n; i++){
			tat[i] = ct[i] - at[i];
			wt[i] = tat[i] - bt[i];
		}
		System.out.println("\n\tP\tAT\tCT\tTAT\tWT");
		for(int i = 0; i< n; i++){
			System.out.printf("P%d\t%d\t%d\t%d\t%d\t%d\n", pid[i], at[i], bt[i], ct[i], tat[i], wt[i]);
		}
		System.out.print("\nGantt Chart");
		for(int i = 0; i < n; i++){
			System.out.print("P" + pid[i] + " ");
		}

	
		System.out.println();
		sc.close();
		
	}
}