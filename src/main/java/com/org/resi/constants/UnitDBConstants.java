package com.org.resi.constants;


public class UnitDBConstants {

    // Show Dropdown
    public static final String LOCATOR_SHOW_DROPDOWN = "//div[contains(text(),\"Show\")]/../select";
    public static final String LOCATOR_SHOW_TYPE =
            "//select/option[contains(text(),\"replaceText\")]";

    // Unit data Base actions element locators
    public static final String LOCATOR_ACTIONS_DROPDOWN = "//span[contains(text(),\"Actions\")]";
    public static final String LOCATOR_ACTIONS_ADDNEW = "//a[contains(text(),\"Add New\")]";
    public static final String LOCATOR_ACTIONS_ADDNEW_VISITOR =
            "//button[contains(text(),\"Add New Visitor\")]";

    public static final String LOCATOR_ACTIONS_EDIT_UNIT_GROUPS =
            "//a[contains(text(),\"Edit Unit Groups\")]";
    public static final String LOCATOR_ACTIONS_EDIT_OCCUPANT_GROUPS =
            "//a[contains(text(),\"Edit Occupant Groups\")]";
    public static final String LOCATOR_ACTIONS_MAAS_REGISTRATION =
            "//a[contains(text(),\"Mass Registration \")]";

    public static final String LOCATOR_RESIDENT = "//div[contains(text(),\"replaceText\")]";

    public static final String LOCATOR_SEARCH_BOX = "//input[@placeholder=\"Search\"]";

    // General Tab Elements
    public static final String LOCATOR_GENERAL_TAB = "//span[contains(text(),\"General\")]";

    public static final String LOCATOR_UNIT_NUMBER_TEXTBOX =
            "//div[contains(text(),\"Unit #\")]/following-sibling::div/div/div/input";
    public static final String LOCATOR_UNIT_NUMBER_TEXTBOX_VISITOR =
            "//div[contains(text(),\"Unit\")]/following-sibling::div/div/div/input";

    public static final String LOCATOR_UNIT_AUTO_SUGGEST = "//div[contains(text(),\"Automation\")]";

    public static final String LOCATOR_FIRST_NAME_TEXTBOX =
            "//div[contains(text(),\"First Name:\")]/following-sibling::div/div/input";
    public static final String LOCATOR_VISITOR_FIRST_NAME_TEXTBOX =
            "//div[contains(text(),\"First Name\")]/following-sibling::div/input";

    public static final String LOCATOR_LAST_NAME_TEXTBOX =
            "//div[contains(text(),\"Last Name:\")]/following-sibling::div/div/input";
    public static final String LOCATOR_VISITOR_LAST_NAME_TEXTBOX =
            "//div[contains(text(),\"Last Name\")]/following-sibling::div/input";

    public static final String LOCATOR_LANGUAGE_DROPDOWN =
            "//div[contains(text(),\"Language\")]/following-sibling::div//div[@class=\"Select-control\"]";

    public static final String LOCATOR_GENDER_DROPDOWN =
            "//div[contains(text(),\"Gender:\")]/following-sibling::select";

    public static final String LOCATOR_MOVEIN =
            "//div[contains(text(),\"Move In:\")]/following-sibling::div/div";
    public static final String LOCATOR_MOVEOUT =
            "//div[contains(text(),\"Move Out:\")]/following-sibling::div/div";
    public static final String LOCATOR_DATE_SELECTION = "//li[@data-date=\"ReplaceText\"]";

    public static final String LOCATOR_VISITOR_STARTDATE =
            "//div[contains(text(),\"Start Date\")]/following-sibling::div/div";
    public static final String LOCATOR_VISITOR_ENDDATE =
            "//div[contains(text(),\"End Date\")]/following-sibling::div/div";

    // Phone
    public static final String LOCATOR_PHONE_TAB = "//span[contains(text(),\"Phone\")]";
    public static final String LOCATOR_ADD_PHONE_BUTTON = "//a[contains(text(),\"Add Phone\")]";
    public static final String LOCATOR_PHONE_TEXT_BOX =
            "//a[contains(text(),\"Add Phone\")]/..//input[@type=\"text\"]";

    public static final String LOCATOR_VISITOR_PHONE_TEXTBOX =
            "//div[contains(text(),\"Primary Phone\")]/following-sibling::div/input";

    public static final String LOCATOR_RESIDENT_DELETE_PHONE = "//i[@class=\"fa fa-times-circle\"]";
    public static final String LOCATOR_ACCESS_ERROR_MESSAGE = "//span[contains(text(),\"All door access must be removed before deleting a phone number.\")]";
    public static final String LOCATOR_ACCESS_ERROR_MESSAGE_2 = "//span[contains(text(),\"A phone number is required if door access is to be added.\")]";

    public static final String LOCATOR_PHOTO_TAB = "//span[contains(text(),\"Photo\")]";
    public static final String LOCATOR_EMAIL_TAB = "//span[contains(text(),\"Email\")]";
    public static final String LOCATOR_ADDRESS_TAB = "//span[contains(text(),\"Address\")]";

    // ACCESS TAB
    public static final String LOCATOR_ACCESS_TAB = "//span[contains(text(),\"Access\")]";
    public static final String LOCATOR_ADD_ACCESS =
            "//i[@class=\"fa fa-plus-circle fa-plus-circle-access\"]";
    public static final String LOCATOR_REMOVE_ACCESS =
            "//i[@class=\"fa fa-minus-circle fa-minus-circle-access\"]";
    public static final String LOCATOR_SELECT_ACCESS_POINT =
            "//div[contains(text(),\"Select an Access Point...\")]";
    public static final String LOCATOR_RESTRICT_ACCESS_CHECKBOX =
            "//span[contains(text(),\"Restrict Access\")]";
    public static final String LOCATOR_SELECT_DATES_CHECKBOX =
            "//span[contains(text(),\"Select Dates\")]";
    public static final String LOCATOR_SELECT_TIME_TABLE =
            "//div[contains(text(),\"Select a Time Table...\")]";

    public static final String LOCATOR_UAL_OUTPUTS_ONLY =
            "//span[contains(text(),\"UAL - Outputs Only - User Access Level\")]";
    public static final String LOCATOR_UAL_DOORS_ZONES_ONLY =
            "//span[contains(text(),\"UAL - Doors & Zones only - User Access Level\")]";
    public static final String LOCATOR_SD_ONLINE_DOORS_ONLY =
            "//span[contains(text(),\"UAL - SD Online Doorz - User Access Level\")]";
    public static final String LOCATOR_UAL_DOORS_ZONES_OUTPUTS =
            "//span[contains(text(),\"UAL - Doors,Zones & Outputs - User Access Level\")]";

    public static final String LOCATOR_REMOVE_UAL_OUTPUTS_ONLY =
            "//span[contains(text(),\"UAL - Outputs Only - User Access Level\")]/../../../../../../div/span/i";

    public static final String LOCATOR_FALSE_DOOR = "//span[contains(text(),\"False Door - Door\")]";

    // Save & Cancel Buttons
    public static final String LOCATOR_SAVE_BUTTON = "//div[contains(text(),\"Save\")]";
    public static final String LOCATOR_CANCEL_BUTTON = "//div[contains(text(),\"Cancel\")]";
    public static final String LOCATOR_DELETE_BUTTON = "//div/div[contains(text(),\"Delete\")]";
    public static final String LOCATOR_OK_BUTTON = "//button[contains(text(),\"OK\")]";
    public static final String LOCATOR_EDIT_OCCUPANT_TEXT =
            "//h4[contains(text(),\"Edit Occupant\")]";
}
