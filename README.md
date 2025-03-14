# Sparkle User Guide

![Sparkle User Guide Image](https://act-upload.hoyoverse.com/event-ugc-hoyowiki/2024/02/25/127094529/00a75fdab753a244ab93b56d7a53819e_1128020071342605456.png?x-oss-process=image%2Fformat%2Cwebp)

## Introduction
Sparkle is a task management chatbot that helps you keep track of your tasks in a fun and interactive way!

## Features

### Adding ToDos
You can add a todo task using the following command:

**Command:** `todo TASK_NAME`

**Example:**
```
todo Read a book
```

**Expected Output:**
```
    Let's make it fun! I've added this task:
      [T][ ] Read a book
    Looks like you've got X tasks in your list~ Better get moving!
```

### Adding Deadlines
You can add a deadline task using the following command:

**Command:** `deadline TASK_NAME /by DEADLINE`

**Example:**
```
deadline Submit assignment /by Sunday 11:59PM
```

**Expected Output:**
```
    Let's make it fun! I've added this task:
      [D][ ] Submit assignment (by: Sunday 11:59PM)
    Looks like you've got X tasks in your list~ Better get moving!
```

### Adding Events
You can add an event task using the following command:

**Command:** `event TASK_NAME /from START_TIME /to END_TIME`

**Example:**
```
event Team meeting /from Monday 2PM /to Monday 4PM
```

**Expected Output:**
```
    Let's make it fun! I've added this task:
      [E][ ] Team meeting (from: Monday 2PM to: Monday 4PM)
    Looks like you've got X tasks in your list~ Better get moving!
```

### Listing Tasks
You can view all your tasks by using:

**Command:** `list`

**Expected Output:**
```
    Here are the tasks in your list~
    1. [T][ ] Read a book
    2. [D][ ] Submit assignment (by: Sunday 11:59PM)
    3. [E][ ] Team meeting (from: Monday 2PM to: Monday 4PM)
```

### Marking a Task as Done
You can mark a task as done using:

**Command:** `mark TASK_NUMBER`

**Example:**
```
mark 1
```

**Expected Output:**
```
    Boom! Task's done and dusted~
      [T][X] Read a book
```

### Unmarking a Task
You can unmark a task using:

**Command:** `unmark TASK_NUMBER`

**Example:**
```
unmark 1
```

**Expected Output:**
```
    Not done yet, but it's still on the radar!
      [T][ ] Read a book
```

### Finding Tasks
You can search for tasks that match a specific keyword using the following command:

**Command:** `find KEYWORD`

**Example:**
```
find assignment
```
**Expected Output:**
```
    Here are the tasks that match your search~
    1. [D][ ] Submit assignment (by: Sunday 11:59PM)

```


### Deleting a Task
You can delete a task using:

**Command:** `delete TASK_NUMBER`

**Example:**
```
delete 2
```

**Expected Output:**
```
    Got it! Poof! This task is gone:
      [D][ ] Submit assignment (by: Sunday 11:59PM)
    Look at that! You've got X tasks left to juggle!
```

### Exiting Sparkle
You can exit the program using:

**Command:** `bye`

**Expected Output:**
```
    See you around, Stelle~ Try to stay out of trouble next time!
```

**Enjoy using Sparkle!**