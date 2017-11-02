package ahp.org.Statistics;

import java.util.Arrays;

public class Discretiser {
	private double myData[];
	private int myDataLength;

	public Discretiser(
		double dat[]
	){
		this.data(dat);
	}
	public	void	data(double dat[]){
		Arrays.sort(dat);
		this.myData = dat;
		this.myDataLength = dat.length;
		System.out.println("Discretiser.java : data() : min="+this.myData[0]+", max="+this.myData[this.myDataLength-1]+", length="+this.myDataLength);
	}
	public double[][] equal_bin_width(
		int N /* number of levels/bins */
	){
		final int L = this.myDataLength;
		double breaks[][] = new double[N][2];

		double borders[] = new double[N+1];
		borders[0] = this.myData[0];
		double width_of_bin = (this.myData[this.myDataLength-1]-this.myData[0]) / N;
		System.out.println("Discretiser.java : equal_bin_width() : bin width is "+width_of_bin);
		int i;
		for(i=1;i<N;i++){
			borders[i] = this.myData[0] + width_of_bin * i;
			breaks[i-1][0] = borders[i-1];
			breaks[i-1][1] = borders[i];
		}
		borders[N] = this.myData[this.myDataLength-1];
		breaks[N-1][0] = borders[N-1];
		breaks[N-1][1] = borders[N];

		return breaks;
	}
}
