public class Over30kmException extends Exception{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public Over30kmException()
{
	super("Distance should be between 0-30 Km. ");
}
}