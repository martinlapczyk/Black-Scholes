public class BlackScholes{
	//stock price
	double stock=300;
	//strike price
	double strike=250;
	//term of option
	double t=1;
	//dividend yield
	double d=.00;
	//risk-free interest rate
	double r=.03;
	//volatility of the stock
	double vol=.15;
	double d1=(Math.log(stock/strike)+((r-d+((Math.pow(vol,2)/2))*t)))/(vol*Math.sqrt(t));
	double d2=(Math.log(stock/strike)+((r-d-((Math.pow(vol,2)/2))*t)))/(vol*Math.sqrt(t));
	public double CallPrice(){
		double callPrice=(stock*Math.exp(-d*t)*CNDF(d1))-(strike*Math.exp(-r*t)*CNDF(d2));
		return callPrice;
	}
	public double PutPrice(){
		double putPrice=(strike*Math.exp(-r*t)*CNDF(-d2))-(stock*Math.exp(-d*t)*CNDF(-d1));
		return putPrice;
	}
	//this function for the cumulative normal distribution function was found on https://stackoverflow.com/questions/442758/which-java-library-computes-the-cumulative-standard-normal-distribution-function and was written by Erik(https://stackoverflow.com/users/386587/erk)
	public double CNDF(double x)
{
    int neg = (x < 0d) ? 1 : 0;
    if ( neg == 1) 
        x *= -1d;

    double k = (1d / ( 1d + 0.2316419 * x));
    double y = (((( 1.330274429 * k - 1.821255978) * k + 1.781477937) *
                   k - 0.356563782) * k + 0.319381530) * k;
    y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

    return (1d - neg) * y + neg * (1d - y);
}
public static void main(String[] args){
	BlackScholes newObj=new BlackScholes();
	double num=newObj.CallPrice();
	double num2=newObj.PutPrice();
	System.out.println(num);
	System.out.println(num2);
}
}
