package org.aivan.microevolution.tmp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class CpuTest extends Thread {

  static final Logger log = Logger.getLogger(CpuTest.class);

  public static void main(String[] args) {
    int THREAD_COUNT = 1;

    long startTime = System.currentTimeMillis();
    
    Set<CpuTest> tests = new HashSet<CpuTest>();
    for (int i = 0; i < THREAD_COUNT; i++) {
      CpuTest test = new CpuTest();
      test.start();
      tests.add(test);
    }
    
    for(CpuTest test : tests) {
      try {
        test.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    
    long endTime = System.currentTimeMillis();
    log.info("test time: "+(endTime - startTime)+ " ms");
  }

  @Override
  public void run() {
    
    long LOOP_COUNT = 50000000;
    
    for (long i = 0; i < LOOP_COUNT; i++) {
      Date d = new Date(System.currentTimeMillis());
      if(d.after(new Date(System.currentTimeMillis()))) {
        System.out.println("neee");
      }
    }
  }

}
