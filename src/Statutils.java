package	ahp.org.Statistics;

import java.util.Arrays;

public class	Statutils {
	private final static double MADSCALEFACTOR = 1.4826;

	// returns median calculate fastly
	public	static double	median(double sample[]){
		return QuickSelect.median(sample);
	}
	// returns an array of MAD and median in this order
	public	static double[] medianAbsoluteDeviation(
		double sample[]
	){
		double ret[] = new double[2];
		Statutils.medianAbsoluteDeviation(sample, ret);
		return ret;
	}
	public	static	void	medianAbsoluteDeviation(
		double sample[],
		double returned_values[]
	){
		// aka MAD
		int L = sample.length, i;
		double amedian = Statutils.median(sample);
		double sample_minus_median[] = new double[L];
		for(i=L;i-->0;){ sample_minus_median[i] = Math.abs(sample[i] - amedian); }
		double median_of_medians = Statutils.median(sample_minus_median);
		returned_values[0] = median_of_medians * Statutils.MADSCALEFACTOR;
		returned_values[1] = amedian;
	}
	// given the statistics of two samples, find the stats of the combined sample without re-doing stats
	public static double[] statistics_of_two_samples(
		// N : number of samples, mean and variance (which is stdev^2)
		int N1, double mean1, double variance1,
		int N2, double mean2, double variance2
	){
		double ret[] = new double[2];
		Statutils.statistics_of_two_samples(
			N1, mean1, variance1,
			N2, mean2, variance2,
			ret
		);
		return ret;
	}
	public static void statistics_of_two_samples(
		// N : number of samples, mean and variance (which is stdev^2)
		int N1, double mean1, double variance1,
		int N2, double mean2, double variance2,
		double returned_values[]
	){
		// the combined mean
		int Nt = N1 + N2;
		double combined_mean = (N1 * mean1 + N2 * mean2) / Nt;

		double diff_mean1 = mean1 - combined_mean,
			diff_mean2 = mean2 - combined_mean;

		returned_values[0] = combined_mean;
		// the combined variance (which is stdev^2)
		returned_values[1] = ((N1-1) * variance1 + (N2-1) * variance2 + N1 * diff_mean1 * diff_mean1 + N2 * diff_mean2 * diff_mean2) / (Nt-1);
	}
	public	static double[] statistics_of_sample(
		double sample[]
	){
		double ret[] = new double[6];
		Statutils.statistics_of_sample(sample, ret);
		return ret;
	}
	public	static void	statistics_of_sample(
		double sample[],
		double returned_values[]
	){
		int L = sample.length, i;
		double amin = sample[0];
		double amax = amin;
		double sum = 0.0, asample;
		for(i=L;i-->0;){
			asample = sample[i];
			sum += asample;
			if( asample < amin ){ amin = asample; }
			if( asample > amax ){ amax = asample; }
		}
		double mean = sum / L;
		double stdev = 0.0, atmp;
		for(i=L;i-->0;){
			atmp = mean - sample[i];
			stdev += atmp * atmp;
		}
		i = 0;
		returned_values[i++] = L;
		returned_values[i++] = sum;
		returned_values[i++] = amin;
		returned_values[i++] = amax;
		returned_values[i++] = mean;
		returned_values[i++] = Math.sqrt(stdev/(L-1));
	}
	public	static void	statistics_of_sample(
		double sample[][], // will ignore the second dim
		double returned_values[]
	){
		int L = sample.length, i;
		double amin = sample[0][0];
		double amax = amin;
		double sum = 0.0, asample;
		for(i=L;i-->0;){
			asample = sample[i][0];
			sum += asample;
			if( asample < amin ){ amin = asample; }
			if( asample > amax ){ amax = asample; }
		}
		double mean = sum / L;
		double stdev = 0.0, atmp;
		for(i=L;i-->0;){
			atmp = mean - sample[i][0];
			stdev += atmp * atmp;
		}
		i = 0;
		returned_values[i++] = L;
		returned_values[i++] = sum;
		returned_values[i++] = amin;
		returned_values[i++] = amax;
		returned_values[i++] = mean;
		returned_values[i++] = Math.sqrt(stdev/(L-1));
	}
}
