package model.neural;

public interface TrainingSuccessfulCallback {
	
	public void successful(int expected, int produced);
	public void noNeedToTrain(int expected);
	
}
