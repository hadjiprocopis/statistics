package	ahp.org.Statistics;

import	java.util.Random;
import	java.io.FileNotFoundException;
import	java.io.Serializable;
import	java.io.PrintWriter;
import	java.util.Comparator;
// so not thread-safe:
import	java.util.HashMap;
import	java.util.Arrays;
import	java.util.List;
import	java.util.ArrayList;

public class	StatisticsContainer implements Serializable {
	private static final long serialVersionUID = 1797271487L+14L;
	private	double	myMean, myStdev, myMin, myMax, mySum;
	private	int	myNumSamples;

	public	StatisticsContainer(){
		this.reset();
	}
	public	void	reset(){
		this.myMean =
		this.myStdev =
		this.mySum =
		this.myMin =
		this.myMax = 0.0;
		this.myNumSamples = 0;
	}
	public	void	set(
		double amean, double astdev,
		double asum,
		double amin, double amax,
		int anumsamples
	){
		this.myMean = amean;
		this.myStdev = astdev;
		this.mySum = asum;
		this.myMin = amin;
		this.myMax = amax;
		this.myNumSamples = anumsamples;
	}		
	public	double	mean(){ return this.myMean; }
	public	double	max(){ return this.myMax; }
	public	double	min(){ return this.myMin; }
	public	double	stdev(){ return this.myStdev; }
	public	double	sum(){ return this.mySum; }
	public	int	num_samples(){ return this.myNumSamples; }

	public  String  toString(){
		return new String(
			"[of "+this.myNumSamples+" samples: "
			+"me: "+this.myMean
			+", sd: "+this.myStdev
			+", sum: "+this.mySum
			+", range: ("+this.myMin+" to "+this.myMax+")"
			+"]"
		);
	}
}
