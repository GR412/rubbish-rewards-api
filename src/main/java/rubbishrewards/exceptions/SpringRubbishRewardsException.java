package rubbishrewards.exceptions;

public class SpringRubbishRewardsException extends RuntimeException
{
    public SpringRubbishRewardsException(String exMessage, Exception exception)
    {
        super(exMessage, exception);
    }

    public SpringRubbishRewardsException(String exMessage)
    {
        super(exMessage);
    }
}
