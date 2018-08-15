package home.database;

public class DatabaseFields {
    /* TABLES */
    public static final String USERS_TABLE = "users";
    public static final String PLANS_TABLE = "plans";
    public static final String INPROGRESS_TABLE = "inprogress";
    public static final String DONE_TABLE = "done";

    /* FIELDS IN EACH TABLE */

    //users table
    public static final String USERS_ID = "id";
    public static final String USERS_USERNAME = "username";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_EMAIL = "email";
    public static final String USERS_DATE = "dateOfAccountCreate";

    //plans table
    public static final String PLANS_ID = "id";
    public static final String PLANS_NAME = "planName";
    public static final String PLANS_DATE_ = "dateOfPlanCreate";
    public static final String PLANS_USER_ID = "user_id";

    //in progress table
    public static final String INPROGRESS_ID = "id";
    public static final String INPROGRESS_NAME = "planName";
    public static final String INPROGRESS_DATE = "dateOfPlanBeginInProgress";
    public static final String INPROGRESS_USER_ID = "user_id";

    //done table
    public static final String DONE_ID = "id";
    public static final String DONE_NAME = "planName";
    public static final String DONE_DATE = "dateOfEndTheProject";
    public static final String DONE_USER_ID = "user_id";
}
