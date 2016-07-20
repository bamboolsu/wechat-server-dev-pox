package org.leo.weixin.main;

import java.math.BigDecimal;

public class CommonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BigDecimal bigDec=new BigDecimal(0.01119);
		float ff=bigDec.setScale(2 , BigDecimal.ROUND_UP).floatValue();
		int myInt = (int) (ff * 100);
		System.out.println("ff is " + ff + "  int myInt is: " + myInt);
		
		BigDecimal amount = new BigDecimal(0.01119);
		BigDecimal refund_amount = new BigDecimal(0.01119);		
		BigDecimal tmpBig = new BigDecimal(amount.doubleValue());
		float tmpFloat = tmpBig.setScale(2 , BigDecimal.ROUND_UP).floatValue();
		String total_fee = "" + (int) (tmpFloat * 100);

		tmpBig = new BigDecimal(refund_amount.doubleValue());
		tmpFloat = tmpBig.setScale(2 , BigDecimal.ROUND_DOWN).floatValue();
		String refunds_fee = "" + (int) (tmpFloat * 100);
		System.out.println("total_fee is " + total_fee + "  refunds_fee is: " + refunds_fee);
		
		
	}

}
