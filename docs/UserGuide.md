---
layout: page
title: User Guide
---

Intern Watcher (IW) is a **desktop app for managing internship applicants, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, IW can get your applicant management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `*.jar` from [here](https://github.com/AY2122S1-CS2103T-F12-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your InterWatcher.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all applicants.

   * **`add`**`n/John p/123 e/a@a.com addr/Singapore g/4.50 i/NTU c/CS y/06/2025 a/INTERVIEWED` : Adds a applicant named `John` to Intern Watcher.

   * **`delete`**`3` : Deletes the 3rd applicant shown in the current list.

   * **`clear`** : Deletes all applicants.

   * **`exit`** : Exits the app.

2. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command formats:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`+ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…+` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a applicant: `add`

Adds a applicant to Inter Watcher.

Format: `add n/NAME p/PHONE e/EMAIL g/GRADE i/INSTITUTION c/COURSE y/GRADUATION_YEAR_MONTH [a/STATUS] [s/SKILL]…+`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A applicant can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com addr/311, Clementi Ave 2 g/4.50 i/NTU c/CS y/06/2025 a/INTERVIEWED s/Python s/Java`

### Listing all applicants : `list`

Shows a list of all applicants in Inter Watcher.

Format: `list`

### Editing applicants : `edit`

Edits an existing applicant, or all currently displayed applicants in Inter Watcher. 

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [g/GRADE] [i/INSTITUTION] [c/COURSE] [y/GRADUATION_YEAR_MONTH] [a/STATUS] [t/SKILL]…`
`edit ALL [a/STATUS]`
* If `INDEX` is specified, Edits the applicant at the specified `INDEX`. The index refers to the index number shown in the displayed applicant list. The index **must be a positive integer** 1, 2, 3, …+
* If `ALL` is specified, edits all applicants currently displayed.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the applicant will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s skills by typing `s/` without
    specifying any skills after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st applicant to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower s/` Edits the name of the 2nd applicant to be `Betsy Crower` and clears all existing skills.

### Locating applicants by name: `find`

Finds applicants whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Applicants matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find John` returns `john` and `John Doe`
* `find alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a applicant : `delete`

Deletes the specified applicant from Inter Watcher.

Format: `delete INDEX`

* Deletes the applicant at the specified `INDEX`.
* The index refers to the index number shown in the displayed applicant list.
* The index **must be a positive integer** 1, 2, 3, …+

Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in Inter Watcher.
* `find Betsy` followed by `delete 1` deletes the 1st applicant in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from Inter Watcher.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InterWatcher data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

InterWatcher data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InterWatcher will discard all data and start with an empty data file at the next run.
</div>

### Finding by Status/Skills `[coming soon]`

_Details coming soon ..._

### Undoing a command `[coming soon]`

_Details coming soon..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InterWatcher home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE e/EMAIL addr/ADDRESS g/GRADE i/INSTITUTION c/COURSE y/GRADUATION_YEAR_MONTH [a/STATUS] [t/TAG]…++` <br> e.g., `add n/John p/999 e/a@a.com addr/Singapore 2 g/4.00 i/NTU c/CS y/06/2025 a/INTERVIEWED t/friends`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [addr/ADDRESS] [g/GRADE] [i/INSTITUTION] [c/COURSE] [y/GRADUATION_YEAR_MONTH] [a/STATUS] [t/TAG]…`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
