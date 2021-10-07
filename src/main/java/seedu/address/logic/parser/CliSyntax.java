package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_GRADE = new Prefix("g/");
    public static final Prefix PREFIX_INSTITUTION = new Prefix("i/");
    @SuppressWarnings("SpellCheckingInspection")
    public static final Prefix PREFIX_GRADUATIONYEARMONTH = new Prefix("y/");
    public static final Prefix PREFIX_COURSE = new Prefix("c/");
    public static final Prefix PREFIX_SKILL = new Prefix("s/");
    public static final Prefix PREFIX_STATUS = new Prefix("a/");

}
