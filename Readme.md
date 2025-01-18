# Equipment Management System

This project is an Equipment Management System that allows users to manage equipment and members in a club. The system supports various commands to register members, create equipment, borrow and request equipment, and list the status of equipment and members.

## Project Structure

```
.
├── 

Club.java


├── Cmd
│   ├── 

CmdArrive.java


│   ├── 

CmdBorrow.java


│   ├── 

CmdCreate.java


│   ├── 

CmdListEquipment.java


│   ├── 

CmdListEquipmentStatus.java


│   ├── 

CmdListMembers.java


│   ├── 

CmdListMemberStatus.java


│   ├── 

CmdRegister.java


│   ├── 

CmdRequest.java


│   ├── 

CmdStartNewDay.java


├── 

Command.java


├── 

Day.java


├── 

Equipment.java


├── 

EquipmentSet.java


├── 

ExEquipmentCodeAlreadyInUse.java


├── 

ExEquipmentCodeNotFound.java


├── 

ExEquipmentNotFound.java


├── 

ExInsufficientCommands.java


├── 

ExInvalidFormat.java


├── 

ExMemberAlreadyBorrowedThisEquipment.java


├── 

ExMemberIDinUse.java


├── 

ExMemberNotFound.java


├── 

ExNoEquipmentSetAvailable.java


├── 

ExNoOfDaysMustBeInteger.java


├── 

ExNotLater.java


├── 

ExPeriodIsLessThan.java


├── 

ExPeriodOverLapRequest.java


├── 

ExPeriodOverlapsBorrow.java


├── 

ExUnknownCommand.java


├── 

Main.java


├── 

Member.java


├── 

RecordedCommand.java


├── 

Request.java


├── 

SystemDate.java


├── 

test.txt


├── 

testing.txt


```

## How to Run

1. **Compile the project:**
   ```sh
   javac Main.java
   ```

2. **Run the project:**
   ```sh
   java Main
   ```

3. **Input the file pathname when prompted:**
   ```
   Please input the file pathname: test.txt
   ```

## Commands

- `register <memberID> <memberName>`: Register a new member.
- `create <equipmentCode> <equipmentName>`: Create a new equipment.
- `arrive <equipmentCode>`: Mark the arrival of an equipment set.
- `borrow <memberID> <equipmentCode> [<numberOfDays>]`: Borrow an equipment set.
- `request <memberID> <equipmentCode> <startDate> <numberOfDays>`: Request an equipment set.
- `listMembers`: List all members.
- `listEquipment`: List all equipment.
- `listMemberStatus`: List the status of all members.
- `listEquipmentStatus`: List the status of all equipment.
- `startNewDay <newDate>`: Start a new day.
- `undo`: Undo the last command.
- `redo`: Redo the last undone command.

## Exception Handling

The system handles various exceptions to ensure robust operation:
- 

ExMemberIDinUse


- 

ExInsufficientCommands


- 

ExInvalidFormat


- 

ExNotLater


- 

ExMemberNotFound


- 

ExEquipmentNotFound


- 

ExEquipmentCodeAlreadyInUse


- 

ExEquipmentCodeNotFound


- 

ExNoEquipmentSetAvailable


- 

ExMemberAlreadyBorrowedThisEquipment


- 

ExPeriodOverLapRequest


- 

ExPeriodOverlapsBorrow


- 

ExPeriodIsLessThan


- 

ExNoOfDaysMustBeInteger


- 

ExUnknownCommand
