package ahp.org.Statistics;

import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.DenseInstance;

import ahp.org.Clusterator.ClusteringResult;

public class JavaMLKMeans {
	private Dataset myDataset;
	private int myNumDims;
	private int myDataLength;

	public JavaMLKMeans(
		double dat[]
	){
		this.data(dat);
	}
	public JavaMLKMeans(
		double dat[][]
	){
		this.data(dat);
	}
	public	ClusteringResult clustering(
		int num_clusters,
		int num_iterations
	){
		long time_started = System.nanoTime();
		double	centroids[][] = new double[num_clusters][this.myNumDims],
			left_border[][] = new double[num_clusters][this.myNumDims],
			right_border[][] = new double[num_clusters][this.myNumDims]
		;
		System.out.println("JavaMLKMeans.java : clustering() : starting with "+num_clusters+" clusters, "+num_iterations+" max iters, "+this.myDataLength+" items in "+this.myNumDims+" dimensions...");
		KMeans KM = new KMeans(num_clusters, num_iterations);
		Dataset result[] = KM.cluster(this.myDataset);
		System.out.println("JavaMLKMeans.java : clustering() : done for "+num_clusters+" clusters, "+num_iterations+" max iters, "+this.myDataLength+" items in "+this.myNumDims+" dimensions in "+((System.nanoTime()-time_started)/1E06)+" milli seconds total.");
		return new ClusteringResult(result);
	}
	public void data(double dat[]){
		// 1D data
		this.myNumDims = 1;
		this.myDataLength = dat.length;
		this.myDataset = new DefaultDataset();
		final int L = dat.length;
		int i;
		for(i=0;i<L;i++){
			this.myDataset.add(new DenseInstance(new double[]{dat[i]}));
		}
		System.out.println("JavaMLKMeans.java : data() : added "+L+" items (1D).");
	}
	public void data(double dat[][]){
		// ND data
		final int L = dat.length;
		final int N = dat[0].length;
		this.myNumDims = N;
		this.myDataLength = L;
		this.myDataset = new DefaultDataset();
		int i;
		for(i=0;i<L;i++){
			this.myDataset.add(new DenseInstance(dat[i]));
		}
		System.out.println("JavaMLKMeans.java : data() : added "+L+" items of dimension "+N+".");
	}
	
}
