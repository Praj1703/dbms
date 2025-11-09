import java.util.*;

public class Optimal{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Number of Frames : ");
		int framesCount = sc.nextInt();

		System.out.print("Enter the number of pages : ");
		int pagesCount = sc.nextInt();

		int pages[] = new int[pagesCount];

		System.out.print("Enter the Page Reference String : ");
		for (int i = 0; i < pagesCount; i++) {
            pages[i] = sc.nextInt();
        }

		List<Integer> frames = new ArrayList<>();

		int pageFaults = 0;

		System.out.print("Page Replacement Process : ");

		for(int i = 0; i < pagesCount; i++){
			int page = pages[i];

			if(frames.contains(page)) {
				System.out.println("Step " + (i+1) + " -> Page " +page+ " : HIT");
				printFrames(frames);
				continue;
			}
			pageFaults++;

			if (frames.size() < framesCount) {
				frames.add(page);
			} else {
				int replaceIndex = findPageToReplace(frames, pages, i+1);
				frames.set(replaceIndex, page);
			}
			System.out.println("Step " + (i+1) + " -> Page "+ page + " : Fault");
			printFrames(frames);
		}
		System.out.println("\n Total Page Faults : " + pageFaults);
		System.out.println("\n Total Page Hit: " + (pagesCount - pageFaults));
		sc.close();
	}
	private static int findPageToReplace(List<Integer> frames, int[] pages, int startIndex) {
		int farthestIndex = -1;
		int indexToReplace = -1;

		for (int i = 0; i < frames.size(); i++){
			int currentPage = frames.get(i);
			int j;

			for(j = startIndex; j< pages.length; j++){
				if (pages[j] == currentPage) {
					if(j > farthestIndex) {
						farthestIndex = j;
						indexToReplace = i;
					}
					break;
				}
			}
			if(j == pages.length){
				return i;
			}
		}
		return (indexToReplace == -1) ? 0 : indexToReplace;		
	}
	private static void printFrames(List<Integer> frames) {
		System.out.print("Frames : ");
		for(int page : frames) {
			System.out.print(page + " ");
		}
		System.out.println();
	}
}