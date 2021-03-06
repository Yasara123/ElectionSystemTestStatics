import java.util.Arrays;

public class Statistics
{
    int[] data;
    int size;   

    public Statistics(int[] data) 
    {   
        
        
        this.data = data;
        size = data.length;
        
    }   

    double getMean()
    {
        double sum = 0.0;
        for(double a : data)
            sum += a;
        return sum/size;
    }

    double getVariance()
    {
        double mean = getMean();
        double temp = 0;
        for(double a :data)
            temp += (mean-a)*(mean-a);
        return temp/size;
    }
    double getMax()
    {
        
        double temp = 0;
        for(double a :data){
            if(temp<a){
                temp=a;
            }
        }
           
        return temp;
    }

    double getStdDev()
    {
        return Math.sqrt(getVariance());
    }

    public double median() 
    {
       Arrays.sort(data);

       if (data.length % 2 == 0) 
       {
          return (data[(data.length / 2) - 1] + data[data.length / 2]) / 2.0;
       } 
       else 
       {
          return data[data.length / 2];
       }
    }
}
