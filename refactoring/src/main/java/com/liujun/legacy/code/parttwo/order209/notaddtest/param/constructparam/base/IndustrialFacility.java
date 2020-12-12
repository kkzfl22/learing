package com.liujun.legacy.code.parttwo.order209.notaddtest.param.constructparam.base;

/**
 * @author liujun
 * @version 0.0.1
 */
public class IndustrialFacility extends Facility {

  Permit basePermit;

  public IndustrialFacility(int facilityCode, String owner, Permit permit) throws PermitViolation {
    Permit associatedPermit =
        PermitRepository.GetInstance().findAssociatedFromOrigination(permit);
    if (associatedPermit.isValid() && !permit.isValid()) {
      basePermit = associatedPermit;
    } else if (!permit.isValid()) {
      permit.validate();
      basePermit = permit;
    } else {
      throw new PermitViolation("error !!");
    }
  }

  public void run() {
    basePermit.dataRun();
  }
}
