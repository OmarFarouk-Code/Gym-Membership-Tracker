public enum MembershipPlan 
{
    MONTHLY(50.0, 1),
    QUARTERLY(135.0, 3),
    ANNUAL(500.0, 12);

    private final double price;
    private final int durationInMonths;

    private MembershipPlan ( double price , int durationInMonths )
    {
        this.price = price;
        this.durationInMonths = durationInMonths;
    }

    public double getPrice() 
    {
        return price;
    }

    public int getDurationInMonths() 
    {
        return durationInMonths;
    }
}
