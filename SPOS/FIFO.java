import java.util.*;

public class FIFO{
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter the Number of Frames ");
	int frameCount = sc.nextInt();

	System.out.print("Enter the Number of Pages : ");
	int pageCount = sc.nextInt();
	
	System.out.print("Enter the Refernces of the Pages: ");
	int pages[] = new int[pageCount];
	for (int i = 0; i < pageCount; i++){
		pages[i] = sc.nextInt();
	}

	int frames[] = new int[frameCount];
	for (int i = 0; i< frameCount; i++){
		frames[i] = -1;
	}

	int pageFaults = 0;
	int nextToReplace = 0;

	System.out.println("\n Page Replacement Process:  ");
	
	for(int i = 0; i < pageCount; i++){
		int page = pages[i];
		boolean found = false;
		
		for (int j = 0; j < frameCount; j++) {
			if (frames[j] == page) {
				found = true;
				break;
			}
		}
		if (!found) {
			frames[nextToReplace]  = page;
			nextToReplace = (nextToReplace + 1) % frameCount;
			pageFaults++;
		}

		System.out.print("Step " + (i+1) + "Page " + page + " ): ");
		for (int f = 0; f < frameCount; f++){
			if (frames[f] != -1)
				System.out.print(frames[f] + " ");
			else
				System.out.print("- ");
	}
		System.out.println();
		
	}
	System.out.println("\n Total page Faults: "+ pageFaults);
	sc.close();
}
}