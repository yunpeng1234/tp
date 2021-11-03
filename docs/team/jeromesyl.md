---
layout: page
title: Jerome Soh's Project Portfolio Page
---

### Project: Intern Watcher

Intern Watcher (IW) is a **desktop app for Human Resource Managers to manage internship applicants, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, IW can get your applicant management tasks done faster than traditional GUI apps.

Given below are my contributions to the project.

* **New Feature**: Undo/Redo feature
  * What it does: Allows the user to undo a previously executed command, reverting the application to the previous state. Undo commands can be reversed by the Redo command.
  * Justification: It is common for users to make mistakes when entering commands. Hence, a redo/undo feature will be really helpful for users who need to rectify those mistakes, and redo them in case they change their mind.
  * Highlights: This enhancement affects existing commands and commands to be added in the future. It was challenging as it required existing commands to be modified and a new class that extends the existing InternWatcher class in order to support the undo/redo functionality. Some analysis was needed for design alternatives, such as the data structure to store a history of commands. 
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=Jeromesyl&tabRepo=AY2122S1-CS2103T-F12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
  * Wrote additional tests for existing features to in (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `undo` and `redo` (Pull requests [\#118](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/118), [\#124](https://github.com/AY2122S1-CS2103T-F12-2/tp/pull/124)) 
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
  
