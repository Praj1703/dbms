import java.util.*;

public class LRU {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the Numnber of Frames: ");
		int framesCount = sc.nextInt();
		System.out.print("Enter the Number of pages: ");
		int pagesCount = sc.nextInt();

		int pages[] = new int[pagesCount];
		System.out.println("Enter Page Reference Strings: ");
		for(int i = 0; i < pagesCount; i++ ) {
			pages[i] = sc.nextInt();
		}
		
		int[] frames = new int[framesCount];
		int[] lastUsedTime = new int[framesCount];

		Arrays.fill(frames, -1);
		Arrays.fill(lastUsedTime, -1);
		int pageFaults = 0;

		System.out.print("\nPage Replacement Process: ");
		
		for(int i = 0; i < pagesCount; i++){
			int page = pages[i];
			boolean found = false;
			int foundIndex = -1;

			for(int j = 0; j < framesCount; j++){
				if(frames[j] == page) {
					found = true;
					foundIndex = j;
					break;
				}
			}
		if(found) {
			lastUsedTime[foundIndex] = i;
		}
		else {
			pageFaults++;
			int replaceIndex = -1;
		
			for(int j = 0; j < framesCount; j++){
				if(frames[j] == -1){
					replaceIndex = j;
					break;
				}				
			}
		
			if(replaceIndex == -1){
				int lruIndex = 0;
				int minTime = lastUsedTime[0];
				
				for(int j = 0; j < framesCount; j++){
					if (lastUsedTime[j] < minTime){
						minTime = lastUsedTime[j];
						lruIndex = j;
					}
				}
				replaceIndex = lruIndex;
			}
			
			frames[replaceIndex] = page;
			lastUsedTime[replaceIndex] = i;
		}
		
 System.out.print("Step " + (i + 1) + " (Page " + page + "): ");
            for (int f : frames) {
                if (f != -1) System.out.print(f + " ");
            }
            System.out.println(found ? "(Hit)" : "(Fault)");
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}