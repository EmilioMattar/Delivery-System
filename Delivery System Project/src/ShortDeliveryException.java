public class ShortDeliveryException extends Exception{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public ShortDeliveryException()
{
	super("This member has short deliveries. \n Delete short deliveries then delete member.");
}
}