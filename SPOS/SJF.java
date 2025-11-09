import java.util.*;

public class SJF {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Number Of Process: ");
		int n = sc.nextInt();
		int at[] = new int[n];
		int wt[] = new int[n];
		int bt[] = new int[n];
		int ct[] = new int[n];
		int tat[] = new int[n];
		int rt[] = new int[n];
		
		boolean[] completed = new boolean[n];

		for (int i = 0; i < n ; i++){
			System.out.print("Arrival Time of Process P" + (i+1) + " ");
			at[i] = sc.nextInt();
			System.out.print("Burst Time of Process P" + (i+1) + " ");
			bt[i] = sc.nextInt();
			rt[i] = bt[i];
		}
		int completedCount = 0;
		int currentTime = 0;
		
		while(completedCount < n){
			int idx = -1;
			int minRT = Integer.MAX_VALUE;
			for(int i = 0; i< n;i++){
				if (!completed[i] && at[i] <= currentTime && rt[i] < minRT && rt[i] > 0){	
			minRT = rt[i];
			idx = i;
			}
			}
			if(idx == -1){
			 	currentTime++;
				continue;
			}
			rt[idx]--;
			currentTime++;
			if (rt[idx] == 0) {
				completed[idx] = true;
				completedCount++;
				ct[idx] = currentTime;
                tat[idx] = ct[idx] - at[idx];
                wt[idx] = tat[idx] - bt[idx];
			}
		}

		System.out.println("\nP\tAT\tBT\tCT\tTAT\tWT");
        for (int i = 0; i < n; i++) 
        {
            System.out.printf("%d\t%d\t%d\t%d\t%d\t%d\n", i + 1, at[i], bt[i], ct[i], tat[i], wt[i]);
	}	
sc.close();
}
}