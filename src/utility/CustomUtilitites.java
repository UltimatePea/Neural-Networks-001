package utility;

public class CustomUtilitites {
	
	public static int getLargestIndex(double[] array){
		int maxIndex = 0;
		for (int i = 1; i < array.length; i++){
		   double newnumber = array[i];
		   if ((newnumber > array[maxIndex])){
		   maxIndex = i;
		  }
		}
		return maxIndex;
	}

}
