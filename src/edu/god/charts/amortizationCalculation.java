package edu.god.charts;

import java.text.DecimalFormat;

public class amortizationCalculation {
    //Method used to do the amortization calculation
    //Input: Principal Amount, Interest Rate and Term (in months)
    //Output: Monthly Payment and Total Payment
    public String getpayInfo(String principalAmountTextField, String interestRateTextField, String insuranceRateTextField, String termMonthsTextField) {

        DecimalFormat df = new DecimalFormat("0.00");

        double principalAmount = Double.parseDouble(principalAmountTextField); //parsed principal amount from the principal amount text field
        double interestRate = Double.parseDouble(interestRateTextField); //parsed interest rate from the interest rate text field
        double insussuranceRate = Double.parseDouble(insuranceRateTextField);

        interestRate = interestRate / 100.0;
        insussuranceRate = insussuranceRate / 100.0;

        double monthlyInterestRate = interestRate / 12.0; //calculates the monthly interest rate
        double monthlyInssuranceRate = insussuranceRate / 12.0; //calculates the monthly interest rate

        int termMonths = Integer.parseInt(termMonthsTextField); //parsed term in months from the term (in months) text field
        double monthlyPayment = principalAmount * (monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -termMonths))); //calculates the monthly payment
        double monthlyInssurance = principalAmount * monthlyInssuranceRate;
        double totalPayment = monthlyPayment * termMonths; // calculates the total payment
        double totalInterests = totalPayment - principalAmount;
        double totalInsurance = principalAmount * insussuranceRate;

        //keeping only 2 decimals
        //return monthlyPayment+"####"+totalPayment+"####"+totalInterests; //monthly payment and total payment are returned
        return df.format(monthlyPayment) + "####" + df.format(totalPayment) + "####" + df.format(totalInterests) + "####" + df.format(monthlyInssurance) + "####" + df.format(totalInsurance); //monthly payment and total payment are returned
    }
}
