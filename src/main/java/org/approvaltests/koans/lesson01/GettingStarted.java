package org.approvaltests.koans.lesson01;

import java.awt.Rectangle;

import org.approvaltests.Approvals;
import org.approvaltests.koans.helpers.FileAssert;
import org.approvaltests.koans.helpers.Koans;
import org.approvaltests.koans.helpers.Person;
import org.approvaltests.namer.ApprovalNamer;
import org.approvaltests.reporters.DiffReporter;
import org.approvaltests.reporters.JunitReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * How to do Koans:
 * Step 1: Press the Run Button (Place cursor on the Method name to run a single method)
 * PC: Ctrl+F11
 * Mac: Command+fn+F11
 * Step 2: Read the name of the Method that Failed
 * Step 3: Fill in the blank (___) to make it pass
 * Step 4: Repeat Until Enlightenment
 * Do not change anything except the blank (___)
 */
@UseReporter(JunitReporter.class)
public class GettingStarted extends Koans
{
  @Test
  public void normalJunitAsserts()
  {
    assertEquals("Small String", "Small String");
  }

  @Test
  public void assertAgainstFileContents()
  {
    FileAssert.verifyContentsIsEqual("expected.txt", "Small String");
  }

  @Test
  public void usingAutomaticFileNames()
  {
    ApprovalNamer namer = Approvals.createApprovalNamer();
    FileAssert.verifyContentsIsEqual(namer.getApprovalName() + ".txt", "Prefer Convention over Configuration");
  }

  @Test
  public void automaticallyGeneratedNames()
  {
    ApprovalNamer namer = Approvals.createApprovalNamer();
    assertEquals(namer.getApprovalName(), "GettingStarted.automaticallyGeneratedNames");
  }

  @Test
  public void usesMethodName()
  {
    ApprovalNamer namer = Approvals.createApprovalNamer();
    assertEquals("GettingStarted.usesMethodName", namer.getApprovalName());
  }

  @Test
  public void fileNames()
  {
    ApprovalNamer namer = Approvals.createApprovalNamer();
    String className = "GettingStarted";
    String methodName = "fileNames";
    String approvalName = className + "." + methodName;
    assertEquals(namer.getApprovalName(), approvalName);
  }

  @Test
  public void verifyBiggerText()
  {
    Rectangle r = new Rectangle();
    r.width = 40;
    r.height = 189;
    r.x = 136;
    r.y = 200;
    ApprovalNamer namer = Approvals.createApprovalNamer();
    FileAssert.verifyContentsIsEqual(namer.getApprovalName() + ".txt", r.toString());
  }

  @Test
  public void approvalsUsesThisFileNameConvention()
  {
    Approvals.verify("This is in the approved file");
    // Hint: If you double-click the 1st line of the Failure Trace a diff tool will open
  }

  @Test
  @UseReporter(DiffReporter.class)
  public void seeingFilesSideBySide()
  {
    ApprovalNamer namer = Approvals.createApprovalNamer();
    Approvals.verify("This file is called" + "\r\n" + namer.getApprovalName());
  }

  @Test
  public void changingTheGoldenMaster()
  {
    Approvals.verify("This is the golden master");
    //Hint: What is the name of the file where the blank is?
  }

  @Test
  public void verifyObjects()
  {
    Rectangle r = new Rectangle();
    r.width = 40;
    r.height = 100;
    r.x = 150;
    r.y = 200;
    Approvals.verify(r);
  }

  @Test
  public void sometimeYouNeedABetterToString()
  {
    Person p = new Person("jayne", "cobb", true, 38);
    String format = "Person\n  FirstName:%s\n  LastName:%s\n  Sex:%s\n  Age:%s\n";
    String custom = String.format(format, p.getFirstName(), p.getLastName(), p.isMale() ? "Male" : "Female", p.getAge());
    Approvals.verify(custom);
  }
}
