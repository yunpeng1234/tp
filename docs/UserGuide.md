---
layout: page
title: User Guide
---

Intern Watcher (IW) is a **desktop app for Human Resource Managers to manage internship applicants, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, IW can get your applicant management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `*.jar` from [here](https://github.com/AY2122S1-CS2103T-F12-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your InternWatcher.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>

6. Some example commands you can try:

   * **`list`** : Lists all applicants.

   * **`add`**`n/John p/123 e/a@a.com g/4.50 i/NTU c/CS y/06/2025 a/INTERVIEWED` : Adds an applicant named `John` to Intern Watcher.

   * **`find john` : Lists all applicants whose name containing john.

   * **`filter`**`g/4.50 c/CS` : Lists all applicants that have a grade not less than 4.50 and study CS course.

   * **`delete`**`3` : Deletes the 3rd applicant shown in the current list.

   * **`view`**`3`**`T` : Shows the 3rd applicant's skills.

   * **`undo`** : Undo the last command the user has entered.

   * **`redo`** : Redo the last command the user has undoed.

   * **`clear`** : Deletes all applicants.

   * **`exit`** : Exits the app.

7. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command formats:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  This excludes special tags for **`edit`** **`view`** **`delete`** commands. <br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.<br>
  e.g. in `delete ALL`, `ALL` is to be typed verbatim in full capital letters by the user.

* Items in square brackets are optional.<br>
  e.g `n/NAME [s/SKILL]` can be used as `n/John Doe s/friend` or as `n/John Doe`.

* Items with `…`+ after them can be used multiple times including zero times.<br>
  e.g. `[s/SKILL]…+` can be used as ` ` (i.e. 0 times), `s/Java`, `s/Python s/C` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding an applicant: `add`

Adds an applicant to Intern Watcher.

Format: `add n/NAME p/PHONE e/EMAIL g/GRADE i/INSTITUTION c/COURSE y/GRADUATION_YEAR_MONTH [a/APPLICATION_STATUS] [s/SKILL]…+`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
An applicant can have any number of tags (including 0)
</div>

Examples:

* `add n/Alex Yeoh p/98765432 e/johnd@example.com g/4.50 i/NTU c/CS y/06/2025 a/SCHEDULED s/Python s/Java` adds
 an applicant with the specified information.
  ![Add result](images/AddResult.png)

### Listing all applicants : `list`

Displays the list of all applicants in Intern Watcher.

Format: `list`

### Viewing an applicant's details : `view`

Displays the specified applicant's academic records, or skills.

Format: `view INDEX [T]`

* If only `INDEX` is specified, it will show the specified applicant's academic records by default.
  * The index refers to the index number shown in the displayed applicant list.
  * The index must be a positive integer 1, 2, 3, …+
* If `T` is also specified along with `INDEX`, it will toggle to the next tab applicant detail window.

Examples:
* `list` followed by `view 1` Shows the 1st applicant's academic records on the displayed applicants list.
* `view 2 T` Shows the 2nd applicant's skills on the displayed applicant's list, if academic records was previously selected.

### Editing applicants : `edit`

Edits an existing applicant, or all currently displayed applicants in Intern Watcher.

Format:
1. `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [g/GRADE] [i/INSTITUTION] [c/COURSE] [y/GRADUATION_YEAR_MONTH] [a/APPLICATION_STATUS] [s/SKILL]…`
2. `edit ALL [a/APPLICATION_STATUS]`
* If `INDEX` is specified, Edits the applicant at the specified `INDEX`. The index refers to the index number shown in the displayed applicant list. The index **must be a positive integer** 1, 2, 3, …+
* If `ALL` is specified, edits all applicants currently displayed.
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing skills, the existing skills of the applicant will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s skills by typing `s/` without
    specifying any skills after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st applicant to be `91234567` and `johndoe@example.com` respectively.

|Command|Effect|
|---|---|
|<img src="images/EditIndexBefore.png" alt="drawing" />|<img src="images/EditIndexAfter.png" alt="drawing" />|

*  `edit 1 s/` Clears all existing skills of the 1st applicant .

|Command|Effect|
|---|---|
|<img src="images/EditSkillBefore.png" alt="drawing" />|<img src="images/EditSkillAfter.png" alt="drawing" />|

*  `filter a/INTERVIEWED` followed by `edit ALL a/ACCEPTED` Updates all applicants with `INTERVIEWED` application status to have the `ACCEPTED` APPLICATION_STATUS.

|Command|Effect|
|---|---|
|<img src="images/EditAllBefore.png" alt="drawing" />|<img src="images/EditAllAfter.png" alt="drawing" />|

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

### Deleting an applicant : `delete`

Deletes the specified applicant from Inter Watcher.

Format: `delete INDEX` `delete INDEX ALL`

* If `INDEX` is specified, Deletes the applicant at the specified `INDEX` or all currently displayed applicants in the Intern Watcher.
* The index refers to the index number shown in the displayed applicant list.
* The index **must be a positive integer** 1, 2, 3, …+
* If `ALL` is specified, Deletes all applicants currently displayed.


Examples:
* `list` followed by `delete 2` deletes the 2nd applicant in Intern Watcher.
* `list` followed by `delete ALL` deletes the all applicants in Intern Watcher. Effect is similar to `clear` when used in conjunction with `list`.
* `find Betsy` followed by `delete 1` deletes the 1st applicant in the results of the `find` command.

### Clearing all entries : `clear`

Clears all applicant entries from Intern Watcher.

Format: `clear`

### Undoing an action : `undo`

Undoes the last undoable action.

Format: `undo`

* Restores Intern Watcher to a state before the last undoable action.
* If the current state of Intern Watcher is the oldest state, the undo command will not be invoked.
* Undoable actions include: `add`, `edit`, `delete`, `clear`, `redo`.

Examples:
* `undo` (after `delete 2`). The applicant that was removed will be restored in Intern Watcher.
* `undo` (after initial startup of Intern Watcher). As there are no previous states to restore, no undo action will be performed.

### Redoing an action: `redo`

Redoes the last undoable action.

Format: `redo`

* `redo` is the reverse of `undo`. The command restores the state of Intern Watcher to the last undoable action that was undone.
* If the current state of Intern Watcher is the newest state, the redo command will not be invoked.
* If a new undoable action is performed after the last undo command, the current state becomes the newest state.
* Undoable actions include: `add`, `edit`, `delete`, `clear`, `redo`.

Examples:
* `clear` followed by `undo` followed by `redo`. The clear command will be redone.
* `undo` followed by `delete 3`. As the state after `delete` becomes the newest state, there are no undoable actions to be redone.

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

InternWatcher data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

InternWatcher data are saved as a JSON file `[JAR file location]/data/internwatcher.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, InternWatcher will discard all data and start with an empty data file at the next run.
</div>

### Filtering by Fields

Filters the applicants in Intern Watcher by a given field.

Format: `filter [g/GRADE] [i/INSTITUTION]…+ [c/COURSE]…+ [y/GRADUATION_YEAR_MONTH] [a/STATUS] [s/SKILL]…+`


* `filter` will show applicants that matches all fields specified.
* If `[g/GRADE]` is specified, it will show all applicants with grades higher or equal to the `g/Grade` specified.
* Likewise, if `[y/GRADUATION_YEAR_MONTH]` is specified, it will show all applicants with graduation dates that is strictly before the `GRADUATION_YEAR_MONTH` specified.
* If more than 1 of `[s/SKILL]…+` is specified, applicants that matches at least one of the `s/SKILL` specified will be shown.
* This is the same of both of `[i/INSTITUTION]…+` `[c/COURSE]…+` `[a/Status]…+` as well.

Examples:
* `filter s/Python s/Java` will show applicants with skills in either JAVA or PYTHON or both.
* `filter g/4.50` will show applicants with a grade more than or equals 4.50.
* `filter y/06/2022` will show applicants  with graduation date earlier than June 2022.
* `filter s/Java y/06/2022 i/NUS` will show applicants that graduate earlier than June 2022, knows JAVA and is from NUS.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous InternWatcher home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE e/EMAIL g/GRADE i/INSTITUTION c/COURSE y/GRADUATION_YEAR_MONTH [a/APPLICATION_STATUS] [s/SKILL]…++` <br> e.g., `add n/John p/999 e/a@a.com g/4.00 i/NTU c/CS y/06/2025 a/INTERVIEWED s/Python`
**Clear** | `clear`
**Delete** | `delete INDEX/ALL`<br> e.g., `delete 3`, `delete ALL`
**Edit** | `edit INDEX/ALL [n/NAME] [p/PHONE] [e/EMAIL] [g/GRADE] [i/INSTITUTION] [c/COURSE] [y/GRADUATION_YEAR_MONTH] [a/APPLICATION_STATUS] [s/SKILL]…`<br>e.g.,`edit 2 n/James Lee e/jameslee@example.com`, `edit ALL a/REJECTED`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
**Filter** | `filter [g/GRADE] [i/INSTITUTION]…+ [c/COURSE]…+ [y/GRADUATION_YEAR_MONTH] [a/STATUS] [s/SKILL]…+` <br> e.g. , `filter s/JAVA y/06/2022 i/NUS a/REJECTED`
**Undo** | `undo`
**Redo** | `redo`
**View** | `view INDEX [T]` <br> e.g. , `view 2 T`
**Exit** | `exit`
