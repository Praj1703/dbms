import java.util.*; 

//This is for non PreEmptive 

public class priorityPreemptive {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Number Of Processes : ");
		int n = sc.nextInt();
		
		int at[] = new int[n];
		int wt[] = new int[n];
		int bt[] = new int[n];
		int ct[] = new int[n];
		int tat[] = new int[n];
		int[] priority = new int[n];
		boolean completed[] = new boolean[n];
		
		for(int i = 0; i < n; i++){
			System.out.print("Enter The Arrival Time of Process P" + (i+1) + " ");
			at[i] = sc.nextInt();
			System.out.print("Enter the Burst Time of Process P" + (i+1) + " ");
			bt[i] = sc.nextInt();
			System.out.print("Enter the Priority of Process P" + (i + 1) + " ");
			priority[i] = sc.nextInt(); 
		}
		int completedCount = 0;
		int currentTime = 0;
		
		while (completedCount < n){
			int idx = -1;
			int highestPriority = Integer.MAX_VALUE;
			for(int i = 0; i < n; i++){
				if(!completed[i] && at[i] <= currentTime && priority[i] < highestPriority) {
					highestPriority = priority[i];
					idx = i;
				}
			}
			if(idx ==-1) {
				currentTime++;
				continue;
			}
			
			currentTime += bt[idx];
			ct[idx] = currentTime;
			tat[idx] = ct[idx] - at[idx];
			wt[idx] = tat[idx] - bt[idx];
			completed[idx] = true;
			completedCount++;
		}
			
		System.out.println("\nP\tAT\tBT\tPriority\tCT\tTAT\tWT");
			for(int i = 0; i < n; i++){
			System.out.printf("%d\t%d\t%d\t%d\t\t%d\t%d\t%d\t\n", i+1, at[i], bt[i], priority[i], ct[i],tat[i], wt[i]);
		}
		sc.close();
	}
}